package com.well.tech.customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "AddressResponse", description = "Address data returned by the API")
public record AddressResponseDTO(
        @Schema(description = "Address unique identifier", example = "b2c3d4e5-f6a7-8901-bcde-f12345678901")
        UUID id,

        @Schema(description = "Street name", example = "Avenida Paulista")
        String street,

        @Schema(description = "Building number", example = "1000")
        String number,

        @Schema(description = "Additional address information", example = "Apt 42")
        String complement,

        @Schema(description = "Neighborhood", example = "Bela Vista")
        String neighborhood,

        @Schema(description = "City name", example = "São Paulo")
        String city,

        @Schema(description = "State abbreviation", example = "SP")
        String state,

        @Schema(description = "ZIP code", example = "01310-100")
        String zipCode
) {
}