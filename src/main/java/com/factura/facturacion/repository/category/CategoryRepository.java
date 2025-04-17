package com.factura.facturacion.repository.category;

import com.factura.facturacion.model.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
  Optional<Category> findByNameCategory (String nameCategory);
  Page<Category> findByStatus(Boolean status, Pageable pageable);
}
