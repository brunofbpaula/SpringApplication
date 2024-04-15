package com.blackout.university.repository;

import com.blackout.university.models.Course;
import com.blackout.university.models.Student;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT E.student FROM Enrollment E WHERE E.course.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);

}
