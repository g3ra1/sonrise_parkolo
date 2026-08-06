package org.example.sonrise_parkolo.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String rendszam;
}
