package com.blackout.university.repository;

import com.blackout.university.models.Professors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorsRepository extends JpaRepository<Professors, Long> {

    @Query("SELECT DISTINCT P FROM Professors P INNER JOIN P.courses C WHERE C.id = :courseId")
    List<Professors> findAllByCourse(@Param("courseId") Long courseId);


}
