package org.sonrise_parkolo.controller;

import org.sonrise_parkolo.dto.input.ReservationRequest;
import org.sonrise_parkolo.dto.output.ReservationOutputDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/reservations")
public interface ReservationController {

    @PostMapping("/auto-book")
    ResponseEntity<String> autoBook(@RequestBody ReservationRequest request);

    @DeleteMapping("/cancel")
    ResponseEntity<String> parkingCancellation(@RequestParam String code);

    @PutMapping("/extend")
    ResponseEntity<String> extendReservation(@RequestParam String code,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newEndTime);

    @GetMapping("/getCode/{parkingCode}")
    ResponseEntity<ReservationOutputDTO> getCode(@PathVariable String parkingCode);

    @GetMapping("/getAll")
    ResponseEntity<List<ReservationOutputDTO>> getAllReservation();
}
