package com.blackout.university.controller;

import com.blackout.university.business.StudentBusiness;
import com.blackout.university.dto.student.StudentDetailmentDTO;
import com.blackout.university.dto.student.StudentSignUpDTO;
import com.blackout.university.dto.student.StudentUpdateDTO;
import com.blackout.university.exceptions.ErrorMessage;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentBusiness studentBusiness;
    @Autowired
    private ErrorMessage error;

    @Operation(summary = "Stores student", responses = {
            @ApiResponse(responseCode = "201", description = "Done",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "409", description = "Could not complete post due error")})
    @PostMapping
    public ResponseEntity signUp(@RequestBody StudentSignUpDTO studentSignUpDTO){
        try {
            StudentDetailmentDTO student = studentBusiness.storeStudent(studentSignUpDTO);
            return ResponseEntity.status(201).body(student);
        } catch (Exception e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(409).body(error);
        }
    }

    @Operation(summary = "Select all students", responses = {
            @ApiResponse(responseCode = "200", description = "Done",
                    content = @Content(schema = @Schema(implementation = Student.class)))})
    @GetMapping
    public ResponseEntity searchForAll(){
        try {
            List<Student> students = studentBusiness.searchForAllStudents();
            return ResponseEntity.status(200).body(students);
        } catch (Exception e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Search student by id", responses = {
            @ApiResponse(responseCode = "200", description = "Done",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "400", description = "Student cannot be found")})
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id){
        try {
            Student student = studentBusiness.searchForStudent(id);
            return ResponseEntity.status(200).body(student);
        } catch (NotFoundResourceException e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Update student", responses = {
            @ApiResponse(responseCode = "200", description = "Done",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "400", description = "Student cannot be found")})
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody StudentUpdateDTO studentUpdateDTO){
        try {
            Student student = studentBusiness.updateStudent(id, studentUpdateDTO);
            return ResponseEntity.status(200).body(student);
        }catch (NotFoundResourceException | Exception e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Delete studant", responses = {
            @ApiResponse(responseCode = "204", description = "Done",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "400", description = "Student was not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            studentBusiness.deleteStudent(id);
            return ResponseEntity.status(204).build();
        } catch (NotFoundResourceException e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }
}
