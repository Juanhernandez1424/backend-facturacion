package com.factura.facturacion.repository.product;

import com.factura.facturacion.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  Optional<Product> findByNameProduct(String nameProduct);
  Page<Product> findByStatus(Boolean status, Pageable pageable);
}
