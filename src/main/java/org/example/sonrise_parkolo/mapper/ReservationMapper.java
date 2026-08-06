package org.example.sonrise_parkolo.mapper;

import org.example.sonrise_parkolo.dto.input.ReservationInputDTO;
import org.example.sonrise_parkolo.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationInputDTO reservationInputDTO);
}
