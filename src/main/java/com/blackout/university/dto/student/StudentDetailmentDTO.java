package com.blackout.university.dto.student;

import com.blackout.university.models.Address;
import com.blackout.university.models.Languages;
import com.blackout.university.models.Student;

import java.time.LocalDate;
import java.util.List;

public record StudentDetailmentDTO(
        Long id,
        String name,
        String email,
        String cpf,
        LocalDate birthDate,
        List<Languages> languages,
        Address address
) {

    public StudentDetailmentDTO(Student student) {
        this(
            student.getId(),
                student.getName(),
                student.getEmail(),
                student.getCpf(),
                student.getBirthDate(),
                student.getLanguages(),
                student.getAddress()
        );
    }

}
