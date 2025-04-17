package com.factura.facturacion.model.category;

import com.factura.facturacion.dto.category.DTOCategory;
import com.factura.facturacion.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idCategory;
  @Setter
  @Column(unique = true)
  private String nameCategory;
  @Setter
  private String descriptionCategory;
  @OneToMany (mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Setter
  private List<Product> products;
  @Setter
  private Boolean status;

  public Category(DTOCategory categoryDTO) {
    this.idCategory = categoryDTO.idCategory();
    this.nameCategory = categoryDTO.nameCategory();
    this.descriptionCategory = categoryDTO.descriptionCategory();
    this.status = categoryDTO.status();
  }
}
