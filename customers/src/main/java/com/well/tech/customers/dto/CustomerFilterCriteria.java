package com.well.tech.customers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CustomerFilterCriteria", description = "Optional filters for searching customers")
public record CustomerFilterCriteria(
        @Schema(description = "Partial match on customer name", example = "John")
        String name,

        @Schema(description = "Exact CPF match", example = "123.456.789-09")
        String cpf,

        @Schema(description = "Exact email match", example = "john.doe@example.com")
        String email,

        @Schema(description = "Exact phone number match", example = "+55 11 91234-5678")
        String phoneNumber
) {
}
