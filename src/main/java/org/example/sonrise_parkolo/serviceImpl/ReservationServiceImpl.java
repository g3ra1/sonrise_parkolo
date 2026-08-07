package org.example.sonrise_parkolo.serviceImpl;

import org.example.sonrise_parkolo.dto.output.ReservationOutputDTO;
import org.example.sonrise_parkolo.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.example.sonrise_parkolo.dto.input.ReservationRequest;
import org.example.sonrise_parkolo.entity.ParkingSpot;
import org.example.sonrise_parkolo.entity.Reservation;
import org.example.sonrise_parkolo.repository.ParkingSpotRepository;
import org.example.sonrise_parkolo.repository.ReservationRepository;
import org.example.sonrise_parkolo.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;


    @Override
    @Transactional
    public String autoBookFirstAvailableSpot(ReservationRequest request) {

        LocalDateTime startTime = (request.getStartTime() != null) ? request.getStartTime() : LocalDateTime.now();
        LocalDateTime endTime = request.getEndTime();

        ParkingSpot freeSpot = parkingSpotRepository.findFirstByOccupiedFalseOrderByIdAsc()
                .orElseThrow(() -> new IllegalStateException("Sajnos jelenleg egyetlen parkolóhely sem szabad!"));
        freeSpot.setOccupied(true);

        Reservation currentParking = new Reservation();
        currentParking.setNumberPlate(request.getNumberPlate());
        currentParking.setStartTime(startTime);
        currentParking.setEndTime(endTime);
        currentParking.setParkingSpot(freeSpot);
        String parkingCode = parkingCodeGenerator();
        currentParking.setParkingCode(parkingCode);

        parkingSpotRepository.save(freeSpot);
        reservationRepository.save(currentParking);

        return "Sikeres foglalás, a parkolókódja: " + parkingCode;
    }



    @Override
    @Transactional
    public void setParkingCancellation(String parkingCode) {
        Reservation reservation = reservationRepository.findByParkingCode(parkingCode)
                .orElseThrow(() -> new IllegalArgumentException("Nem található parkolás ezzel a kóddal: " + parkingCode));

        // Parkolóhely felszabadítása
        ParkingSpot spot = reservation.getParkingSpot();
        if (spot != null) {
            spot.setOccupied(false);
            parkingSpotRepository.save(spot);
        }

        // Foglalás törlése
        reservationRepository.delete(reservation);
    }

    @Override
    @Transactional
    public String extendReservation(String parkingCode, LocalDateTime newEndTime) {

        Reservation reservation = reservationRepository.findByParkingCode(parkingCode)
                .orElseThrow(() -> new IllegalArgumentException("Érvénytelen lemondási kód: " + parkingCode));

        if (reservation.getEndTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Ez a foglalás már lejárt, nem hosszabbítható meg!");
        }

        if (!newEndTime.isAfter(reservation.getEndTime())) {
            throw new IllegalArgumentException("Az új befejező időpontnak későbbre kell esnie, mint a jelenlegi: " + reservation.getEndTime());
        }

        reservation.setEndTime(newEndTime);
        reservationRepository.save(reservation);

        return "Sikeres meghosszabbítás, a parkolókódja és új ideje: " + parkingCode + " " + newEndTime;
    }

    @Override
    public ReservationOutputDTO getParking(String parkingCode) {
        Reservation reservation = reservationRepository.findByParkingCode(parkingCode)
                .orElseThrow(() -> new IllegalArgumentException("Nem található parkolás ezzel a kóddal: " + parkingCode));
        reservation.getParkingSpot().getId();
        return reservationMapper.toDto(reservation);
    }

    @Override
    public List<ReservationOutputDTO> getAllReservation(){
        return  reservationRepository.findAll().stream()
                .map(reservationMapper::toDto)
                .toList();
    }
    public String parkingCodeGenerator() {
        String CHARACTER_STOCK = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        String parkingCode;

        do {
            StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                sb.append(CHARACTER_STOCK.charAt(random.nextInt(CHARACTER_STOCK.length())));
            }
            parkingCode = sb.toString();
        } while (reservationRepository.existsByParkingCode(parkingCode));

        return parkingCode;
    }
}