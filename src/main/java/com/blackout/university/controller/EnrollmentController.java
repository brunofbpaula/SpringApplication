package com.blackout.university.controller;

import com.blackout.university.business.EnrollmentBusiness;
import com.blackout.university.dto.enrollment.EnrollmentApplicationDTO;
import com.blackout.university.dto.enrollment.EnrollmentDetailmentDTO;
import com.blackout.university.exceptions.CourseFullException;
import com.blackout.university.exceptions.ErrorMessage;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Enrollment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentBusiness enrollmentBusiness;

    @Autowired
    private ErrorMessage error;

    @Operation(summary = "Sign up for courses", responses = {
            @ApiResponse(responseCode = "201", description = "Done",
                    content = @Content(schema = @Schema(implementation = Enrollment.class))),
            @ApiResponse(responseCode = "400", description = "Error")})
    @PostMapping
    public ResponseEntity apply(@RequestBody EnrollmentApplicationDTO enrollmentApplicationDTO) {
        try {
            Enrollment enrollment = enrollmentBusiness.enrollStudent(enrollmentApplicationDTO);
            return ResponseEntity.status(201).body(new EnrollmentDetailmentDTO(enrollment));
        } catch (CourseFullException | NotFoundResourceException e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Select all enrollments", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Enrollment.class)))})
    @GetMapping
    public ResponseEntity<List<EnrollmentDetailmentDTO>> selectAll(){
        List<EnrollmentDetailmentDTO> enrollments = enrollmentBusiness.searchEnrollments();
        return ResponseEntity.status(200).body(enrollments);
    }
}
