package com.factura.facturacion.dto.user;

import jakarta.validation.constraints.NotNull;

public record DTOUser(
        Integer idUser,
        @NotNull
        String userName,
        @NotNull
        String userPassword,
        @NotNull
        Boolean status
) {
}
