package com.factura.facturacion.dto.category;

import com.factura.facturacion.model.product.Product;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DTOCategory(
        Integer idCategory,
        @NotNull
        String nameCategory,
        @NotNull
        String descriptionCategory,
        @NotNull
        Boolean status
) {
}
