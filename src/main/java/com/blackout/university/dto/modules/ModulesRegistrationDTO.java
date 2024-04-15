package com.blackout.university.dto.modules;

import com.blackout.university.models.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModulesRegistrationDTO(
    @NotBlank
    @Size(max = 75)
    String title,
    @NotBlank
    @Size(max = 350, min = 15)
    String description,
    Course course
) {
}
