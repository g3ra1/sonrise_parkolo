package org.example.sonrise_parkolo.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationInputDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long parkingspot_id;

    private LocalDateTime kezdo_ido;

    private LocalDateTime vegso_ido;
}
