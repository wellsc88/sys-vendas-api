package com.well.tech.customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(name = "CustomerRequest", description = "Payload for creating or updating a customer")
public record CustomerRequestDTO(

        @Schema(description = "Customer full name", example = "John Doe", maxLength = 150)
        @NotBlank
        @Size(max = 150)
        String name,

        @Schema(description = "Customer CPF (Brazilian individual taxpayer registry)", example = "123.456.789-09", maxLength = 14)
        @NotBlank
        @Size(max = 14)
        String cpf,

        @Schema(description = "Customer email address (optional)", example = "john.doe@example.com", maxLength = 255)
        @Email
        @Size(max = 255)
        String email,

        @Schema(description = "Customer phone number (optional)", example = "+55 11 91234-5678", maxLength = 20)
        @Size(max = 20)
        String phoneNumber,

        @Schema(description = "List of addresses associated with the customer")
        List<@Valid AddressRequestDTO> addresses
) {
}
