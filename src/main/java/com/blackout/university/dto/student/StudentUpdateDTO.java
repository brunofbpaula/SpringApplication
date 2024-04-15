package com.blackout.university.dto.student;

import com.blackout.university.models.Languages;

import java.time.LocalDate;
import java.util.List;

public record StudentUpdateDTO(

    String name,
    String email,
    String cpf,
    LocalDate birthDate,
    List<Languages> languages

) {

}
