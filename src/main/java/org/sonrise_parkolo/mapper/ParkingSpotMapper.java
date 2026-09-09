package org.sonrise_parkolo.mapper;

import org.sonrise_parkolo.dto.output.ParkingSpotOutputDTO;
import org.sonrise_parkolo.entity.ParkingSpot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper {
    ParkingSpotOutputDTO toDto(ParkingSpot entity);
}
