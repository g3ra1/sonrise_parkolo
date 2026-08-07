package org.example.sonrise_parkolo.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotOutputDTO {
    private Long id;
    private boolean occupied;
}
