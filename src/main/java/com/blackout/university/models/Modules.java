package com.blackout.university.models;

import com.blackout.university.dto.modules.ModulesRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 75)
    private String title;

    @NotBlank
    @Size(max = 350, min = 15)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    @JsonIgnore
    private Course course;

    public Modules() {
    }

    public Modules(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Modules(Long id, String title, String description, Course course) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.course = course;
    }

    public Modules(ModulesRegistrationDTO modulesRegistrationDTO){
        this.title = modulesRegistrationDTO.title();
        this.description = modulesRegistrationDTO.description();
        this.course = modulesRegistrationDTO.course();
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
