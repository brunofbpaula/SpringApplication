package com.blackout.university.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    private String name;

    @NotNull
    @Size(max = 350, min = 15)
    private String description;

    @NotNull
    private Date startDate;

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

    public Course(Long id, String name, String description, Date startDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    public Course(Long id, String name, String description, Date startDate, List<Professors> professors,
                  List<Student> students, List<Modules> modules) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.professors = professors;
        this.students = students;
        this.modules = modules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
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
