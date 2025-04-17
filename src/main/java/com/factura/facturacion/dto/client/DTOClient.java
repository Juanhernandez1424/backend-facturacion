package com.factura.facturacion.dto.client;

import jakarta.validation.constraints.NotNull;

public record DTOClient(
        Integer idClient,
        @NotNull
        String firstNameClient,
        @NotNull
        String lastNameClient,
        @NotNull
        String identifyClient,
        @NotNull
        String phoneNumberClient,
        @NotNull
        String addressClient,
        @NotNull
        String emailClient,
        @NotNull
        Boolean status
) {
}
