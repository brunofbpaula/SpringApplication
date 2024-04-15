package com.blackout.university.dto.course;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CourseUpdateDTO(
        @Size(max = 120)
        String title,
        @Size(max = 350)
        String description,
        @FutureOrPresent
        LocalDate startDate
) {
}
