package com.well.tech.customers.controller;

import com.well.tech.api.ApiVersion;
import com.well.tech.customers.dto.CustomerFilterCriteria;
import com.well.tech.customers.dto.CustomerRequestDTO;
import com.well.tech.customers.dto.CustomerResponseDTO;
import com.well.tech.customers.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiVersion.API_BASE_PATH + ApiVersion.API_VERSION + "/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Customer management endpoints")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a customer", description = "Creates a new customer with an optional list of addresses")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO response = customerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/findAll")
    @Operation(summary = "Search customers", description = "Filters customers by optional criteria and returns a paginated result")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    public ResponseEntity<Page<CustomerResponseDTO>> findAll(
            CustomerFilterCriteria criteria,
            Pageable pageable) {
        Page<CustomerResponseDTO> response = customerService.findAll(criteria, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Returns a single customer by its UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponseDTO> findById(
            @Parameter(description = "Customer UUID", required = true) @PathVariable UUID id) {
        CustomerResponseDTO response = customerService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer", description = "Replaces customer data and the full address list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponseDTO> update(
            @Parameter(description = "Customer UUID", required = true) @PathVariable UUID id,
            @Valid @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO response = customerService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Deletes a customer and all associated addresses")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Customer UUID", required = true) @PathVariable UUID id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
