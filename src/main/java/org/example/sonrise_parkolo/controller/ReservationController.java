package org.example.sonrise_parkolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sonrise_parkolo.dto.input.ReservationInputDTO;
import org.example.sonrise_parkolo.entity.Reservation;
import org.example.sonrise_parkolo.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // POST /api/reservations/auto-book
    @PostMapping("/auto-book")
    public ResponseEntity<Reservation> autoBook(@RequestBody ReservationInputDTO request) {
        return ResponseEntity.ok(reservationService.autoBookFirstAvailableSpot(request));
    }

    // DELETE /api/reservations/cancel?code=AB12CD34
    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelReservation(@RequestParam String code) {
        reservationService.cancelReservation(code);
        return ResponseEntity.ok("A foglalás sikeresen meg lett szakítva!");
    }

    // PUT /api/reservations/extend?code=AB12CD34&newEndTime=2026-08-10T14:00:00
    @PutMapping("/extend")
    public ResponseEntity<Reservation> extendReservation(
            @RequestParam String code,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newEndTime) {

        return ResponseEntity.ok(reservationService.extendReservation(code, newEndTime));
    }
}