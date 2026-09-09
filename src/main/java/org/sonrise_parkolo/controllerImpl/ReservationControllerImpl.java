package org.sonrise_parkolo.controllerImpl;

import lombok.RequiredArgsConstructor;
import org.sonrise_parkolo.controller.ReservationController;
import org.sonrise_parkolo.dto.input.ReservationRequest;
import org.sonrise_parkolo.dto.output.ReservationOutputDTO;
import org.sonrise_parkolo.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService;

    @Override
    public ResponseEntity<String> autoBook(ReservationRequest request) {
        return ResponseEntity.ok(reservationService.autoBookFirstAvailableSpot(request));
    }

    @Override
    public ResponseEntity<String> parkingCancellation(String code) {
        reservationService.setParkingCancellation(code);
        return ResponseEntity.ok("A foglalás sikeresen meg lett szakítva!");
    }

    @Override
    public ResponseEntity<String> extendReservation(String code, LocalDateTime newEndTime) {
        return ResponseEntity.ok(reservationService.extendReservation(code, newEndTime));
    }

    @Override
    public ResponseEntity<ReservationOutputDTO> getCode(String parkingCode) {
        return ResponseEntity.ok(reservationService.getParking(parkingCode));
    }

    @Override
    public ResponseEntity<List<ReservationOutputDTO>> getAllReservation() {
        return ResponseEntity.ok(reservationService.getAllReservation());
    }
}