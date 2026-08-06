package org.example.sonrise_parkolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;
import org.example.sonrise_parkolo.service.ParkingSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    //GET /api/spots
    @GetMapping
    public ResponseEntity<List<ParkingSpotOutputDTO>> getAllSpots() {
        return ResponseEntity.ok(parkingSpotService.getAllSpots());
    }

    //GET /api/spots/1
    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotOutputDTO> getSpotById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingSpotService.getSpotById(id));
    }
}
