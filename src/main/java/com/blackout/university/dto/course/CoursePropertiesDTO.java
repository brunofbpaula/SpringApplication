package com.blackout.university.dto.course;

import com.blackout.university.models.Course;

import java.time.LocalDate;

public record CoursePropertiesDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate
) {

    public CoursePropertiesDTO(Course course) {
        this(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate()
        );
    }

}
