package com.blackout.university.business;

import com.blackout.university.dto.enrollment.EnrollmentApplicationDTO;
import com.blackout.university.dto.enrollment.EnrollmentDetailmentDTO;
import com.blackout.university.exceptions.CourseFullException;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Course;
import com.blackout.university.models.Enrollment;
import com.blackout.university.models.Student;
import com.blackout.university.repository.CourseRepository;
import com.blackout.university.repository.EnrollmentRepository;
import com.blackout.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentBusiness {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Enrollment enrollStudent(EnrollmentApplicationDTO enrollmentApplicationDTO) throws NotFoundResourceException, CourseFullException {

        Optional<Student> student = studentRepository.findById(enrollmentApplicationDTO.studentId());
        Optional<Course> course = courseRepository.findById(enrollmentApplicationDTO.courseId());

        if(student.isEmpty()) throw new NotFoundResourceException("Cannot find student with ID: " + enrollmentApplicationDTO.studentId());
        if(course.isEmpty()) throw new NotFoundResourceException("Cannot find course with ID: " + enrollmentApplicationDTO.courseId());

        Enrollment enrollment = new Enrollment(course.get(), student.get(), LocalDate.now());

        Integer enrollmentsByCourse = enrollmentRepository.getEnrollmentsByCourse(course.get().getId());

        if(enrollmentsByCourse > 30){
            throw new CourseFullException("This course has reached its maximum allowed number of enrollments");
        }

        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public List<EnrollmentDetailmentDTO> searchEnrollments(){
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        List<EnrollmentDetailmentDTO> enrollmentDetailmentDTOS = new ArrayList<>();
        for (Enrollment enrollment : enrollments){
            EnrollmentDetailmentDTO enrollmentDetailmentDTO = new EnrollmentDetailmentDTO(enrollment);
            enrollmentDetailmentDTOS.add(enrollmentDetailmentDTO);
        }

        return enrollmentDetailmentDTOS;
    }


}
