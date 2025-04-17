package com.factura.facturacion.controller.category;

import com.factura.facturacion.dto.category.DTOCategory;
import com.factura.facturacion.dto.category.DTOResponseCategory;
import com.factura.facturacion.model.category.Category;
import com.factura.facturacion.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("facturacion/api/v1/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Page<DTOResponseCategory>> findAll(@PageableDefault(size=10) Pageable pageable){
    return ResponseEntity.ok(categoryService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DTOResponseCategory> findById(@PathVariable Integer id){
    DTOResponseCategory dtoResponseCategory = categoryService.findById(id);
    return ResponseEntity.ok(dtoResponseCategory);
  }

  @PostMapping("/post")
  public ResponseEntity<DTOResponseCategory> postCategory(@RequestBody @Valid DTOCategory dtoCategory, UriComponentsBuilder uriComponentsBuilder){
    Category category = categoryService.createCategory(dtoCategory);
    DTOResponseCategory dtoResponseCategory = new DTOResponseCategory(category.getIdCategory(), category.getNameCategory(), category.getDescriptionCategory(),category.getStatus());

    URI url =uriComponentsBuilder.path("facturacion/api/v1/categories/{id}").buildAndExpand(category.getIdCategory()).toUri();
    return ResponseEntity.created(url).body(dtoResponseCategory);
  }

  @PutMapping("/put/{id}")
  public ResponseEntity<DTOCategory> putCategory(@PathVariable Integer id, @RequestBody @Valid DTOCategory dtoCategoryUpdate){
    Category category =categoryService.updateCategory(id, dtoCategoryUpdate);
    return ResponseEntity.ok(new DTOCategory(
            category.getIdCategory(),
            category.getNameCategory(),
            category.getDescriptionCategory(),
            category.getStatus()
    ));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<DTOCategory> deleteCategory(@PathVariable Integer id){
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }
}
