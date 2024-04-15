package com.blackout.university.dto.enrollment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EnrollmentApplicationDTO(
    @NotNull
    Long studentId,
    @NotNull
    Long courseId,
    @PastOrPresent
    LocalDate enrollmentDate
) {
}
