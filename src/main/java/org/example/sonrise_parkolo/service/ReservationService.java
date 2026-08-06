package org.example.sonrise_parkolo.service;

import org.example.sonrise_parkolo.dto.input.ReservationInputDTO;
import org.example.sonrise_parkolo.entity.Reservation;

import java.time.LocalDateTime;

public interface ReservationService {
    Reservation autoBookFirstAvailableSpot(ReservationInputDTO request);
    void cancelReservation(String cancellationCode);
    Reservation extendReservation(String cancellationCode, LocalDateTime newEndTime);
}
