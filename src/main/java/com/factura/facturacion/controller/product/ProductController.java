package com.factura.facturacion.controller.product;

import com.factura.facturacion.dto.product.DTOProduct;
import com.factura.facturacion.dto.product.DTOResponseProduct;
import com.factura.facturacion.model.category.Category;
import com.factura.facturacion.model.product.Product;
import com.factura.facturacion.service.category.CategoryService;
import com.factura.facturacion.service.product.ProductService;
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
@RequestMapping("facturacion/api/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<Page<DTOResponseProduct>> findAll(@PageableDefault(size=10)Pageable pageable){
    return ResponseEntity.ok(productService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DTOResponseProduct> findById(@PathVariable Integer id){
    DTOResponseProduct productDTOResponse = productService.findById(id);
    return ResponseEntity.ok(productDTOResponse);
  }

  @PostMapping("/post")
  public ResponseEntity<DTOResponseProduct> postProduct(@RequestBody @Valid DTOProduct dtoProduct, UriComponentsBuilder uriComponentsBuilder){
    Product product = productService.createProduct(dtoProduct);
    Category category = categoryService.getCategoryById(dtoProduct.idCategory());

    DTOResponseProduct productDTOResponse = new DTOResponseProduct(
            product.getIdProduct(),
            product.getNameProduct(),
            product.getDescriptionProduct(),
            product.getQuantityProduct(),
            product.getPriceProduct(),
            category.getIdCategory(),
            product.getStatus()
    );

    URI url = uriComponentsBuilder.path("/facturacion/api/v1/products/{id}").buildAndExpand(product.getIdProduct()).toUri();
    return ResponseEntity.created(url).body(productDTOResponse);
  }

  @PutMapping("/put/{id}")
  public ResponseEntity<DTOProduct> putProduct(@PathVariable Integer id, @RequestBody @Valid DTOProduct dtoProductUpdate){
    Product product = productService.updateProduct(id, dtoProductUpdate);
    Category category = categoryService.getCategoryById(dtoProductUpdate.idCategory());

    return ResponseEntity.ok(new DTOProduct(
            product.getIdProduct(),
            product.getNameProduct(),
            product.getDescriptionProduct(),
            product.getQuantityProduct(),
            product.getPriceProduct(),
            category.getIdCategory(),
            product.getStatus()
    ));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable Integer id){
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

}
