package org.example.sonrise_parkolo.mapper;

import org.example.sonrise_parkolo.dto.input.ReservationRequest;
import org.example.sonrise_parkolo.dto.output.ReservationOutputDTO;
import org.example.sonrise_parkolo.entity.Reservation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ReservationMapper {

    public abstract Reservation toEntity(
            ReservationRequest reservationRequest
    );

    public abstract ReservationOutputDTO toDto(
            Reservation reservation
    );

    @AfterMapping
    protected void setParkingId(
            Reservation reservation,
            @MappingTarget ReservationOutputDTO reservationOutputDTO
    ) {
        if (reservation.getParkingSpot() != null) {
            reservationOutputDTO.setParkingSpotId(
                    reservation.getParkingSpot().getId()
            );
        }
    }
}