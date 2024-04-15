package com.blackout.university.business;

import com.blackout.university.dto.course.CourseRegistrationDTO;
import com.blackout.university.dto.course.CourseUpdateDTO;
import com.blackout.university.dto.modules.ModulesRegistrationDTO;
import com.blackout.university.dto.professors.ProfessorsRegistrationDTO;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Course;
import com.blackout.university.models.Modules;
import com.blackout.university.models.Professors;
import com.blackout.university.models.Student;
import com.blackout.university.repository.CourseRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseBusiness {

    @Autowired
    private CourseRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Course storeCourse(CourseRegistrationDTO courseRegistrationDTO) {

        Course newCourse = new Course(courseRegistrationDTO);

        List<Modules> modules = new ArrayList<>();
        for (ModulesRegistrationDTO modulesRegistrationDTO : courseRegistrationDTO.modules()) {
            Modules module = new Modules();
            module.setTitle(modulesRegistrationDTO.title());
            module.setDescription(modulesRegistrationDTO.description());
            module.setCourse(newCourse);
            modules.add(module);
        }

        newCourse.setModules(modules);

        List<Professors> professors = new ArrayList<>();
        for (ProfessorsRegistrationDTO professorsRegistrationDTO : courseRegistrationDTO.professors()) {
            Professors professor = new Professors();
            professor.setName(professorsRegistrationDTO.name());
            professor.setSpecialization(professorsRegistrationDTO.specialization());
            professors.add(professor);
        }

        newCourse.setProfessors(professors);

        repository.save(newCourse);
        return newCourse;
    }

    public List<Course> searchForAllCourses(){
        return repository.findAll();
    }

    public Course searchCourseById(Long id) throws NotFoundResourceException {
        Optional<Course> course = repository.findById(id);
        if(course.isPresent()){
            return course.get();
        } else {
            throw new NotFoundResourceException("Course has not been found with ID: " + id);
        }
    }

    public Course updateCourse(Long id, CourseUpdateDTO courseUpdateDTO) throws NotFoundResourceException {
        Optional<Course> course = repository.findById(id);

        if(course.isPresent()){
            course.get().updateCourse(courseUpdateDTO);
            repository.save(course.get());
            return course.get();
        } else {
            throw new NotFoundResourceException("Course has not been found with ID: " + id);
        }
    }

    public void deleteCourse(Long id) throws NotFoundResourceException {
        Optional<Course> course = repository.findById(id);
        if(course.isPresent()){
            repository.deleteById(id);
        }else{
            throw new NotFoundResourceException("Course has not been found with ID: " + id);
        }
    }

    public List<Professors> searchForCourseProfessors(Long courseId) throws NotFoundResourceException {
        Optional<Course> course = repository.findById(courseId);
        if(course.isPresent()){
            return course.get().getProfessors();
        } else {
            throw new NotFoundResourceException("Course has not been found with ID: " + courseId);
        }
    }

    public List<Student> searchForCourseStudents(Long courseId) throws NotFoundResourceException {
        return repository.findStudentsByCourseId(courseId);
    }

}
