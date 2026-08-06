package org.example.sonrise_parkolo.service;

import org.example.sonrise_parkolo.dto.input.ParkingSpotInputDTO;
import org.example.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;

public interface ParkingSpotService {

    String createParkingSpot(ParkingSpotInputDTO parkingSpotInputDTO);

    ParkingSpotOutputDTO updateParkingSpot(Long id, ParkingSpotInputDTO parkingSpotInputDTO);

    String deleteParkingSpot(Long id);

    ParkingSpotOutputDTO getParkingSpot(Long id);

}
