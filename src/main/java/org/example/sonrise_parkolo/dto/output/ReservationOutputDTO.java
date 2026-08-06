package org.example.sonrise_parkolo.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationOutputDTO {
    private Long parkingSpotId;
    private LocalDateTime kezdo_ido;
    private LocalDateTime vegso_ido;
}
