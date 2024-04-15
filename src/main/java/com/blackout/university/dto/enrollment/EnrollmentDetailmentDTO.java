package com.blackout.university.dto.enrollment;

import com.blackout.university.models.Enrollment;
import com.blackout.university.models.Student;

import java.time.LocalDate;

public record EnrollmentDetailmentDTO(
        Long id,
        Student student,
        CourseDetailmentDTO course,
        LocalDate enrollmentDate

        ) {

    public EnrollmentDetailmentDTO(Enrollment enrollment) {
        this(
                enrollment.getId(),
                enrollment.getStudent(),
                new CourseDetailmentDTO(enrollment.getCourse()), enrollment.getEnrollmentDate()
        );
    }
}
