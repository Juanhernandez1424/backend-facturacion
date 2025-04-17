package com.factura.facturacion.dto.user.auth;

import jakarta.validation.constraints.NotBlank;

public record DTOAuthUser(
        @NotBlank
        String userName,
        @NotBlank
        String userPassword) {
}
