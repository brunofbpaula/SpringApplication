package com.blackout.university.dto.course;

import com.blackout.university.models.Course;
import com.blackout.university.models.Modules;
import com.blackout.university.models.Professors;

import java.time.LocalDate;
import java.util.List;

public record CourseDetailmentDTO(
    Long id,
    String title,
    String description,
    LocalDate startDate,
    List<Modules> modules,
    List<Professors> professors
) {

    public CourseDetailmentDTO(Course course) {
        this(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate(),
                course.getModules(),
                course.getProfessors()
        );
    }

}
