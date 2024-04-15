package com.blackout.university.dto.student;

import com.blackout.university.models.Address;
import com.blackout.university.models.Languages;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

public record StudentSignUpDTO (
        @NotBlank
        @Size(max = 120)
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        @Past
        LocalDate birthDate,
        @NotNull
        List<Languages> languages,
        @NotNull
        Address address
) {
}
