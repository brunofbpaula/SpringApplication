package com.blackout.university.dto.professors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfessorsRegistrationDTO(
    @NotBlank
    @Size(max = 120, min = 5)
    String name,
    @NotBlank
    String specialization
) {
}
