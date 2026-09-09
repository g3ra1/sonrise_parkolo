package org.sonrise_parkolo.service;

import org.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;

import java.util.List;

public interface ParkingSpotService {
    List<ParkingSpotOutputDTO> getAllSpots();
    ParkingSpotOutputDTO getSpotById(Long id);
}
