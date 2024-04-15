package com.blackout.university.business;

import com.blackout.university.dto.student.StudentDetailmentDTO;
import com.blackout.university.dto.student.StudentSignUpDTO;
import com.blackout.university.dto.student.StudentUpdateDTO;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Languages;
import com.blackout.university.models.Student;
import com.blackout.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentBusiness {

    @Autowired
    private StudentRepository repository;

    public StudentDetailmentDTO storeStudent(StudentSignUpDTO studentSignUpDTO) throws Exception {
        Student newStudent = new Student(studentSignUpDTO);

        // Checking if student's a minor
        int studentAge = Period.between(newStudent.getBirthDate(), LocalDate.now()).getYears();
        if (studentAge < 18) {
            throw new Exception("Student must be 18 years old or older");
        }

        // Checking if they reside in Brazil
        String country = newStudent.getAddress().getCountry();
        if (!country.equals("Brazil") && !country.equals("Brasil")){
            throw new Exception("Student must reside in Brazil");
        }

        // Checking fluency
        if(!newStudent.getLanguages().contains(Languages.PORTUGUESE) || !newStudent.getLanguages().contains(Languages.ENGLISH)){
            throw new Exception("Student must be fluent in Portuguese and English");
        }

        repository.save(newStudent);
        return new StudentDetailmentDTO(newStudent);

    }

    public List<Student> searchForAllStudents(){
        return repository.findAll();
    }

    public Student searchForStudent(Long id) throws NotFoundResourceException {
        Optional<Student> student = repository.findById(id);
        if(student.isPresent()){
            return student.get();
        } else {
            throw new NotFoundResourceException("Cannot find student with ID: " + id);
        }
    }

    public Student updateStudent(Long id, StudentUpdateDTO studentUpdateDTO) throws NotFoundResourceException, Exception {
        Optional<Student> student = repository.findById(id);
        if(student.isEmpty()){
            throw new NotFoundResourceException("Cannot find student with ID: " + id);
        }

        // Checking if student's a minor
        int studentAge = Period.between(student.get().getBirthDate(), LocalDate.now()).getYears();
        if (studentAge < 18) {
            throw new Exception("Student must be 18 years old or older");
        }


        // Checking if they reside in Brazil
        String country = student.get().getAddress().getCountry();
        if (!country.equals("Brazil") && !country.equals("Brasil")){
            throw new Exception("Student must reside in Brazil");
        }

        // Checking fluency
        if(!student.get().getLanguages().contains(Languages.PORTUGUESE) || !student.get().getLanguages().contains(Languages.ENGLISH)){
            throw new Exception("Student must be fluent in Portuguese and English");
        }

        student.get().updateStudent(studentUpdateDTO);
        repository.save(student.get());
        return student.get();
    }

    public void deleteStudent(Long id) throws NotFoundResourceException {
        Optional<Student> student = repository.findById(id);
        if(student.isPresent()){
            repository.deleteById(id);
        } else {
            throw new NotFoundResourceException("Cannot find student with ID: " + id);
        }
    }

}

