package com.factura.facturacion.model.product;

import com.factura.facturacion.dto.product.DTOProduct;
import com.factura.facturacion.model.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idProduct;
  @Column(unique = true)
  @Setter
  private String nameProduct;
  @Setter
  private String descriptionProduct;
  @Setter
  private Integer quantityProduct;
  @Setter
  private Double priceProduct;
  @ManyToOne
  @JoinColumn(name = "idCategory")
  @Setter
  private Category category;
  @Setter
  private Boolean status;

  public Product(DTOProduct productDTO, Category category) {
    this.idProduct = productDTO.idProduct();
    this.nameProduct = productDTO.nameProduct();
    this.descriptionProduct = productDTO.descriptionProduct();
    this.quantityProduct = productDTO.quantityProduct();
    this.priceProduct = productDTO.priceProduct();
    this.category = category;
    this.status = productDTO.status();
  }
}
