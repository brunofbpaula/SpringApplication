package com.blackout.university.dto.course;

import com.blackout.university.dto.modules.ModulesRegistrationDTO;
import com.blackout.university.dto.professors.ProfessorsRegistrationDTO;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CourseRegistrationDTO(
        @NotBlank
        @Size(max = 120)
        String title,
        @Size(max = 1000)
        String description,
        @NotNull
        @FutureOrPresent
        LocalDate startDate,
        List<ModulesRegistrationDTO> modules,
        List<ProfessorsRegistrationDTO> professors
) {

}
