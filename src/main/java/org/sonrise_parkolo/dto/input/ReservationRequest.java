package org.sonrise_parkolo.dto.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    @NotEmpty
    private String numberPlate;

    private LocalDateTime startTime;

    @NotEmpty
    private LocalDateTime endTime;
}
