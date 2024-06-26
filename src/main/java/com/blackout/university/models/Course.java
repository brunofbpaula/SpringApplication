package com.blackout.university.models;

import com.blackout.university.dto.course.CourseRegistrationDTO;
import com.blackout.university.dto.course.CourseUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "courseId")
    private Long id;

    @NotBlank
    @Size(max = 120, min = 5)
    private String title;

    @NotNull
    @Size(max = 350, min = 15)
    private String description;

    @NotNull
    private LocalDate startDate;

    @ManyToMany
    @JoinTable(
            name = "courseProfessor",
            joinColumns = @JoinColumn(name = "professorId"),
            inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    private List<Professors> professors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "courseStudent",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "courseSubject",
            joinColumns = @JoinColumn(name = "subjectId"),
            inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    private List<Modules> modules;

    public Course() {
    }

    public Course(Long id, String title, String description, LocalDate startDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
    }

    public Course(Long id, String title, String description, LocalDate startDate, List<Professors> professors,
                  List<Student> students, List<Modules> modules) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.professors = professors;
        this.students = students;
        this.modules = modules;
    }

    public Course(CourseRegistrationDTO courseRegistrationDTO){
        this.title = courseRegistrationDTO.title();
        this.description = courseRegistrationDTO.description();
        this.startDate = courseRegistrationDTO.startDate();
    }

    public void updateCourse(CourseUpdateDTO courseUpdateDTO){
        if(courseUpdateDTO.title() != null) this.title = courseUpdateDTO.title();
        if(courseUpdateDTO.description() != null) this.description = courseUpdateDTO.description();
        if(courseUpdateDTO.startDate() != null) this.startDate = courseUpdateDTO.startDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Professors> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professors> professors) {
        this.professors = professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Modules> getModules() {
        return modules;
    }

    public void setModules(List<Modules> modules) {
        this.modules = modules;
    }
}
