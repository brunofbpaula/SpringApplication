package com.blackout.university.dto.professors;

import com.blackout.university.models.Course;
import com.blackout.university.models.Professors;

import java.util.List;

public record ProfessorsDetailmentDTO(
        Long id,
        String name,
        String specialization,
        List<Course> courses
) {

    public ProfessorsDetailmentDTO(Professors professor){
        this(
                professor.getId(),
                professor.getName(),
                professor.getSpecialization(),
                professor.getCourses()
        );
    }

}
