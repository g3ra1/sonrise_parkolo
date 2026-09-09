package org.sonrise_parkolo.service;

import org.sonrise_parkolo.dto.input.ReservationRequest;
import org.sonrise_parkolo.dto.output.ReservationOutputDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    String autoBookFirstAvailableSpot(ReservationRequest request);

    @Transactional
    void setParkingCancellation(String parkingCode);

    String extendReservation(String parkingCode, LocalDateTime newEndTime);

    ReservationOutputDTO getParking(String parkingCode);

    List<ReservationOutputDTO> getAllReservation();
}
