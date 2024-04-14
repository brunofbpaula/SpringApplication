package com.blackout.university.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table
public class Professors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 120, min = 5)
    private String name;

    @NotBlank
    private String specialization;

    @ManyToMany(mappedBy = "professors", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Course> courses;

    public Professors() {
    }

    public Professors(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public Professors(Long id, String name, String specialization, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.courses = courses;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
