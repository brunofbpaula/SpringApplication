package com.blackout.university.models;

import com.blackout.university.dto.student.StudentSignUpDTO;
import com.blackout.university.dto.student.StudentUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Student {

    @Id
    @Column(name = "studentId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    private List<Languages> languages;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
    private Address address;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Enrollment> enrollments;

    public Student() {
    }

    public Student(Long id, String name, String email, String cpf, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public Student(Long id, String name, String email, String cpf, LocalDate birthDate, Address address,
                   List<Languages> languages, List<Enrollment> enrollments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.languages = languages;
        this.enrollments = enrollments;
    }

    public Student(StudentSignUpDTO studentSignUpDTO) {
        this.name = studentSignUpDTO.name();
        this.email = studentSignUpDTO.email();
        this.cpf = studentSignUpDTO.cpf();
        this.birthDate = studentSignUpDTO.birthDate();
        this.languages = studentSignUpDTO.languages();
        this.address = studentSignUpDTO.address();
    }

    public void updateStudent(StudentUpdateDTO studentUpdateDTO) {
        if(studentUpdateDTO.name() != null) this.name = studentUpdateDTO.name();
        if(studentUpdateDTO.email() != null) this.email = studentUpdateDTO.email();
        if(studentUpdateDTO.cpf() != null) this.cpf = studentUpdateDTO.cpf();
        if(studentUpdateDTO.birthDate() != null) this.birthDate = studentUpdateDTO.birthDate();
        if(studentUpdateDTO.languages() != null) this.languages = studentUpdateDTO.languages();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
