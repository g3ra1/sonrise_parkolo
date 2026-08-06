package org.example.sonrise_parkolo.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.sonrise_parkolo.dto.input.ReservationInputDTO;
import org.example.sonrise_parkolo.entity.ParkingSpot;
import org.example.sonrise_parkolo.entity.Reservation;
import org.example.sonrise_parkolo.repository.ParkingSpotRepository;
import org.example.sonrise_parkolo.repository.ReservationRepository;
import org.example.sonrise_parkolo.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation autoBookFirstAvailableSpot(ReservationInputDTO request) {
        List<ParkingSpot> allSpots = parkingSpotRepository.findAll();

        for (ParkingSpot spot : allSpots) {
            boolean isOccupied = reservationRepository.existsByParkingSpotIdAndKezdoIdoBeforeAndVegsoIdoAfter(
                    spot.getId(),
                    request.getVegsoIdo(),
                    request.getKezdoIdo()
            );

            if (!isOccupied) {
                Reservation newReservation = new Reservation();
                newReservation.setParkingSpotId(spot.getId());
                newReservation.setKezdoIdo(request.getKezdoIdo());
                newReservation.setVegsoIdo(request.getVegsoIdo());

                String randomCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                newReservation.setCancellationCode(randomCode);

                return reservationRepository.save(newReservation);
            }
        }

        throw new IllegalStateException("Sajnos a megadott időintervallumban egyetlen parkolóhely sem szabad!");
    }

    @Override
    @Transactional
    public void cancelReservation(String cancellationCode) {
        Reservation reservation = reservationRepository.findByCancellationCode(cancellationCode)
                .orElseThrow(() -> new IllegalArgumentException("Érvénytelen lemondási kód: " + cancellationCode));

        reservationRepository.delete(reservation);
    }

    @Override
    @Transactional
    public Reservation extendReservation(String cancellationCode, LocalDateTime newVegsoIdo) {
        Reservation reservation = reservationRepository.findByCancellationCode(cancellationCode)
                .orElseThrow(() -> new IllegalArgumentException("Érvénytelen lemondási kód: " + cancellationCode));

        if (reservation.getVegsoIdo().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Ez a foglalás már lejárt, nem hosszabbítható meg!");
        }

        if (!newVegsoIdo.isAfter(reservation.getVegsoIdo())) {
            throw new IllegalArgumentException("Az új befejező időpontnak későbbre kell esnie, mint a jelenlegi: " + reservation.getVegsoIdo());
        }

        boolean isOccupied = reservationRepository.existsByParkingSpotIdAndKezdoIdoBeforeAndVegsoIdoAfter(
                reservation.getParkingSpotId(),
                newVegsoIdo,
                reservation.getVegsoIdo()
        );

        if (isOccupied) {
            throw new IllegalStateException("A meghosszabbítani kívánt időszakban a parkolóhely már foglalt!");
        }

        reservation.setVegsoIdo(newVegsoIdo);
        return reservationRepository.save(reservation);
    }
}
