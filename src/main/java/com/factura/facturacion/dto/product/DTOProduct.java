package com.factura.facturacion.dto.product;

import jakarta.validation.constraints.NotNull;

public record DTOProduct(
        Integer idProduct,
        @NotNull
        String nameProduct,
        @NotNull
        String descriptionProduct,
        @NotNull
        Integer quantityProduct,
        @NotNull
        Double priceProduct,
        @NotNull
        Integer idCategory,
        @NotNull
        Boolean status
) {

}
