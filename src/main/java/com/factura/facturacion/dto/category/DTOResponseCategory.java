package com.factura.facturacion.dto.category;

import com.factura.facturacion.model.category.Category;

public record DTOResponseCategory(
        Integer idCategory,
        String nameCategory,
        String descriptionCategory,
        Boolean status
){
  public DTOResponseCategory(Category category){
    this(
            category.getIdCategory(),
            category.getNameCategory(),
            category.getDescriptionCategory(),
            category.getStatus()
    );
  }
}
