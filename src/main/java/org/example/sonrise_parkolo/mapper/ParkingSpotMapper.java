package org.example.sonrise_parkolo.mapper;

import org.example.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;
import org.example.sonrise_parkolo.entity.ParkingSpot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper {
    ParkingSpotOutputDTO toDto(ParkingSpot entity);
}
