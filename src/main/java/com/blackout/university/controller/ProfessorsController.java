package com.blackout.university.controller;

import com.blackout.university.business.ProfessorsBusiness;
import com.blackout.university.dto.professors.ProfessorsDetailmentDTO;
import com.blackout.university.dto.professors.ProfessorsRegistrationDTO;
import com.blackout.university.dto.professors.ProfessorsUpdateDTO;
import com.blackout.university.exceptions.ErrorMessage;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Professors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;

@Controller
@RequestMapping("/professors")
public class ProfessorsController {

    @Autowired
    private ProfessorsBusiness professorsBusiness;

    @Autowired
    private ErrorMessage error;

    @Operation(summary = "Add professor", responses = {
            @ApiResponse(responseCode = "201", description = "Done",
                    content = @Content(schema = @Schema(implementation = Professors.class))),
            @ApiResponse(responseCode = "409", description = "Could not complete post due error")})
    @PostMapping
    public ResponseEntity add(@RequestBody ProfessorsRegistrationDTO professorsRegistrationDTO){
        try {
            ProfessorsDetailmentDTO professor = professorsBusiness.storeProfessor(professorsRegistrationDTO);
            return ResponseEntity.status(201).body(professor);
        } catch (Exception e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Select all professors", responses = {
            @ApiResponse(responseCode = "201", description = "Done",
                    content = @Content(schema = @Schema(implementation = Professors.class)))})
    @GetMapping
    public ResponseEntity searchForAll(){
        try {
            List<Professors> professors = professorsBusiness.searchForAllProfessors();
            return ResponseEntity.status(200).body(professors);
        } catch (Exception e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Search for professor by id", responses = {
            @ApiResponse(responseCode = "200", description = "Done",
                    content = @Content(schema = @Schema(implementation = Professors.class))),
            @ApiResponse(responseCode = "400", description = "Professor was not found")})
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id){
        try {
            Professors professor = professorsBusiness.searchProfessorById(id);
            Link link = WebMvcLinkBuilder.linkTo(ProfessorsController.class).slash(professor.getId()).withSelfRel();
            return ResponseEntity.status(200).body(link);
        }catch (NotFoundResourceException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Updates professor", responses = {
            @ApiResponse(responseCode = "200", description = "Done",
                    content = @Content(schema = @Schema(implementation = Professors.class))),
            @ApiResponse(responseCode = "400", description = "Professor was not found")})
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody ProfessorsUpdateDTO professorsUpdateDTO){
        try {
            Professors professor = professorsBusiness.updateProfessor(id, professorsUpdateDTO);
            return ResponseEntity.status(200).body(professor);
        } catch (NotFoundResourceException e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Deleta um professor pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Professor.class))),
            @ApiResponse(responseCode = "400", description = "Professor não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            professorService.deletarProfessor(id);
            return ResponseEntity.status(204).build();
        }catch (Exception e){
            error.setError(e.getMessage());
            error.setObservacao("Este endpoint funciona parcialmente por conta do 'CascadeType.MERGE' solicitado na documentação.");
            return ResponseEntity.status(400).body(error);
        } catch (NotFoundResourceException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }


}
