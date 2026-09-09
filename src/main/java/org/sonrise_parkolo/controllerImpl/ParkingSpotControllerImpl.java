package org.sonrise_parkolo.controllerImpl;

import lombok.RequiredArgsConstructor;
import org.sonrise_parkolo.controller.ParkingSpotController;
import org.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;
import org.sonrise_parkolo.service.ParkingSpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkingSpotControllerImpl implements ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    public ResponseEntity<List<ParkingSpotOutputDTO>> getAllSpots() {
        return ResponseEntity.ok(parkingSpotService.getAllSpots());
    }

    public ResponseEntity<ParkingSpotOutputDTO> getSpotById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingSpotService.getSpotById(id));
    }
}
