package org.sonrise_parkolo.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;
import org.sonrise_parkolo.entity.ParkingSpot;
import org.sonrise_parkolo.mapper.ParkingSpotMapper;
import org.sonrise_parkolo.repository.ParkingSpotRepository;
import org.sonrise_parkolo.service.ParkingSpotService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ParkingSpotMapper parkingSpotMapper;

    @Override
    public List<ParkingSpotOutputDTO> getAllSpots() {
        return parkingSpotRepository.findAll().stream()
                .map(parkingSpotMapper::toDto)
                .toList();
    }

    @Override
    public ParkingSpotOutputDTO getSpotById(Long id) {
        ParkingSpot spot = parkingSpotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nem található parkolóhely ezzel az ID-val: " + id));
        return parkingSpotMapper.toDto(spot);
    }
}