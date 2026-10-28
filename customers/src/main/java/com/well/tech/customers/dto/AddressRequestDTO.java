package com.well.tech.customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "AddressRequest", description = "Payload for creating or updating an address")
public record AddressRequestDTO(

        @Schema(description = "Street name", example = "Avenida Paulista", maxLength = 200)
        @NotBlank
        @Size(max = 200)
        String street,

        @Schema(description = "Building number", example = "1000", maxLength = 20)
        @NotBlank
        @Size(max = 20)
        String number,

        @Schema(description = "Additional address information (optional)", example = "Apt 42", maxLength = 100)
        @Size(max = 100)
        String complement,

        @Schema(description = "Neighborhood", example = "Bela Vista", maxLength = 100)
        @NotBlank
        @Size(max = 100)
        String neighborhood,

        @Schema(description = "City name", example = "São Paulo", maxLength = 100)
        @NotBlank
        @Size(max = 100)
        String city,

        @Schema(description = "State abbreviation (2 characters)", example = "SP", minLength = 2, maxLength = 2)
        @NotBlank
        @Size(min = 2, max = 2)
        String state,

        @Schema(description = "ZIP code", example = "01310-100", maxLength = 9)
        @NotBlank
        @Size(max = 9)
        String zipCode
) {
}