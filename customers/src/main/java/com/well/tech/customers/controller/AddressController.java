package com.well.tech.customers.controller;

import com.well.tech.api.ApiVersion;
import com.well.tech.customers.dto.AddressFilterCriteria;
import com.well.tech.customers.dto.AddressRequestDTO;
import com.well.tech.customers.dto.AddressResponseDTO;
import com.well.tech.customers.service.AddressService;
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
@RequestMapping(ApiVersion.API_BASE_PATH + ApiVersion.API_VERSION)
@RequiredArgsConstructor
@Tag(name = "Addresses", description = "Address management endpoints")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/addresses/{id}")
    @Operation(summary = "Get address by ID", description = "Returns a single address by its UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address found"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    public ResponseEntity<AddressResponseDTO> findById(
            @Parameter(description = "Address UUID", required = true) @PathVariable UUID id) {
        AddressResponseDTO response = addressService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customers/{customerId}/addresses")
    @Operation(summary = "Add an address", description = "Adds a new address to an existing customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Address added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<AddressResponseDTO> create(
            @Parameter(description = "Customer UUID", required = true) @PathVariable UUID customerId,
            @Valid @RequestBody AddressRequestDTO dto) {
        AddressResponseDTO response = addressService.create(customerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/addresses/{id}")
    @Operation(summary = "Update an address", description = "Updates an existing address")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    public ResponseEntity<AddressResponseDTO> update(
            @Parameter(description = "Address UUID", required = true) @PathVariable UUID id,
            @Valid @RequestBody AddressRequestDTO dto) {
        AddressResponseDTO response = addressService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/addresses/{id}")
    @Operation(summary = "Delete an address", description = "Removes an address")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Address UUID", required = true) @PathVariable UUID id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/addresses/findAll")
    @Operation(summary = "Search addresses", description = "Filters addresses by optional criteria and returns a paginated result")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search completed successfully")
    })
    public ResponseEntity<Page<AddressResponseDTO>> findAll(
            AddressFilterCriteria criteria,
            Pageable pageable) {
        Page<AddressResponseDTO> response = addressService.findAll(criteria, pageable);
        return ResponseEntity.ok(response);
    }
}