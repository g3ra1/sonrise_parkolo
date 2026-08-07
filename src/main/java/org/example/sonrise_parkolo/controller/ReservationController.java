package org.example.sonrise_parkolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sonrise_parkolo.dto.input.ReservationRequest;
import org.example.sonrise_parkolo.dto.output.ReservationOutputDTO;
import org.example.sonrise_parkolo.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // POST /api/reservations/auto-book
    @PostMapping("/auto-book")
    public ResponseEntity<String> autoBook(@RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.autoBookFirstAvailableSpot(request));
    }

    // DELETE /api/reservations/cancel?code=AB12CD34
    @DeleteMapping("/cancel")
    public ResponseEntity<String> parkingCancellation(@RequestParam String code) {
        reservationService.setParkingCancellation(code);
        return ResponseEntity.ok("A foglalás sikeresen meg lett szakítva!");
    }

    // PUT /api/reservations/extend?code=AB12CD34&newEndTime=2026-08-10T14:00:00
    @PutMapping("/extend")
    public ResponseEntity<String> extendReservation(
            @RequestParam String code,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newEndTime) {

        return ResponseEntity.ok(reservationService.extendReservation(code, newEndTime));
    }
    @GetMapping("/getCode/{parkingCode}")
    public ResponseEntity<ReservationOutputDTO> getCode(
            @PathVariable String parkingCode) {
        return ResponseEntity.ok(reservationService.getParking(parkingCode));
    }
    // /api/reservations/getAllReservation
    @GetMapping("/getAllReservation")
    public ResponseEntity<List<ReservationOutputDTO>> getAllReservation() {
        return ResponseEntity.ok(reservationService.getAllReservation());
    }
}