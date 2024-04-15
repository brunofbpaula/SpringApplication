package com.blackout.university.controller;

import com.blackout.university.business.AddressBusiness;
import com.blackout.university.exceptions.ErrorMessage;
import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Address;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressBusiness addressBusiness;

    @Autowired
    private ErrorMessage error;


    @Operation(summary = "Select all addresses", responses = {
            @ApiResponse(responseCode = "200", description = "Done",
                    content = @Content(schema = @Schema(implementation = Address.class)))})
    @GetMapping
    public ResponseEntity searchForAll(){
        try {
            List<Address> addresses = addressBusiness.searchForAllAdresses();
            return ResponseEntity.status(200).body(addresses);
        } catch (Exception e) {
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Search address by id", responses = {
            @ApiResponse(responseCode = "200", description = "Done",
                    content = @Content(schema = @Schema(implementation = Address.class))),
            @ApiResponse(responseCode = "400", description = "Address was not found")})
    @GetMapping("/{id}")
    public ResponseEntity searchById(@PathVariable Long id){
        try {
            Address address = addressBusiness.searchById(id);
            return ResponseEntity.status(200).body(address);
        }catch (NotFoundResourceException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

}
