package com.well.tech.customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(name = "CustomerResponse", description = "Customer data returned by the API")
public record CustomerResponseDTO(
        @Schema(description = "Customer unique identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
        UUID id,

        @Schema(description = "Customer full name", example = "John Doe")
        String name,

        @Schema(description = "Customer CPF", example = "123.456.789-09")
        String cpf,

        @Schema(description = "Customer email address", example = "john.doe@example.com")
        String email,

        @Schema(description = "Customer phone number", example = "+55 11 91234-5678")
        String phoneNumber,

        @Schema(description = "List of addresses associated with the customer")
        List<AddressResponseDTO> addresses
) {
}