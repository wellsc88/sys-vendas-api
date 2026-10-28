package com.well.tech.customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AddressFilterCriteria", description = "Optional filters for searching addresses")
public record AddressFilterCriteria(
        @Schema(description = "Partial match on street name", example = "Avenida")
        String street,

        @Schema(description = "Exact city match", example = "São Paulo")
        String city,

        @Schema(description = "Exact state abbreviation", example = "SP")
        String state,

        @Schema(description = "Exact ZIP code match", example = "01310-100")
        String zipCode
) {
}
