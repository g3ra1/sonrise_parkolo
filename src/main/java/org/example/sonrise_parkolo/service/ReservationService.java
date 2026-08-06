package org.example.sonrise_parkolo.service;

import org.example.sonrise_parkolo.dto.input.ReservationInputDTO;
import org.example.sonrise_parkolo.dto.output.ReservationOutputDTO;

public interface ReservationService {

    String createReservation(ReservationInputDTO reservationInputDTO);

    ReservationOutputDTO updateReservation(Long id, ReservationInputDTO reservationInputDTO);

    String deleteReservation(Long id);

    ReservationOutputDTO getReservation(Long id);
}
