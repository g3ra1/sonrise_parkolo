package org.sonrise_parkolo.controller;

import org.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/spots")
public interface ParkingSpotController {

    @GetMapping
    ResponseEntity<List<ParkingSpotOutputDTO>> getAllSpots();

    @GetMapping("/{id}")
    ResponseEntity<ParkingSpotOutputDTO> getSpotById(@PathVariable Long id);
}
