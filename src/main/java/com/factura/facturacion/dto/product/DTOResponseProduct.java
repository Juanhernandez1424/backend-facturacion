package com.factura.facturacion.dto.product;

import com.factura.facturacion.model.product.Product;

public record DTOResponseProduct(
        Integer idProduct,
        String nameProduct,
        String descriptionProduct,
        Integer quantityProduct,
        Double priceProduct,
        Integer category,
        Boolean status
) {
  public DTOResponseProduct(Product product){
    this(
            product.getIdProduct(),
            product.getNameProduct(),
            product.getDescriptionProduct(),
            product.getQuantityProduct(),
            product.getPriceProduct(),
            (product.getCategory() != null) ? product.getCategory().getIdCategory() : null,
            product.getStatus()
    );
  }
}
