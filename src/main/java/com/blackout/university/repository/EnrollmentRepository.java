package com.blackout.university.repository;

import com.blackout.university.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("""
        SELECT count(E.id) FROM Enrollment E WHERE E.course.id = :courseId
        """)
    Integer getEnrollmentsByCourse(Long courseId);

}
