package com.factura.facturacion.service.product;

import com.factura.facturacion.dto.product.DTOProduct;
import com.factura.facturacion.dto.product.DTOResponseProduct;
import com.factura.facturacion.model.category.Category;
import com.factura.facturacion.model.product.Product;
import com.factura.facturacion.repository.category.CategoryRepository;
import com.factura.facturacion.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Autowired
  private CategoryRepository categoryRepository;

  public Page<DTOResponseProduct> findAll(Pageable pageable){
    Page<Product> products = repository.findByStatus(true, pageable);
    List<DTOResponseProduct> descProducts = products.stream()
            .sorted(Comparator.comparing(Product::getIdProduct).reversed())
            .map(DTOResponseProduct::new)
            .toList();

    return new PageImpl<>(descProducts, pageable, products.getTotalElements());
  }

  public DTOResponseProduct findById(Integer id){
    Optional<Product> product =repository.findById(id);
    if(product.isPresent()){
      Product p =product.get();
      return new DTOResponseProduct(p.getIdProduct(), p.getNameProduct(), p.getDescriptionProduct(),
              p.getQuantityProduct(), p.getPriceProduct(), p.getCategory().getIdCategory(), p.getStatus());
    }
    return null;
  }

  public Product createProduct(DTOProduct dtoProduct){
    Category category = categoryRepository.findById(dtoProduct.idCategory())
            .orElseThrow(() -> new RuntimeException("Category doesn’t exist"));
    Product product = new Product(dtoProduct, category);
    return repository.save(product);
  }

  public Product updateProduct(Integer id, DTOProduct dtoProduct){
    Product product = repository.findById(id)
            .orElseThrow(()-> new RuntimeException("Product doesn´t exist"));
    Category category = categoryRepository.findById(dtoProduct.idCategory())
            .orElseThrow(()-> new RuntimeException("Category doesn´t exist"));
    product.setNameProduct(dtoProduct.descriptionProduct());
    product.setDescriptionProduct(dtoProduct.descriptionProduct());
    product.setQuantityProduct(dtoProduct.quantityProduct());
    product.setPriceProduct(dtoProduct.priceProduct());
    product.setCategory(category);
    product.setStatus(dtoProduct.status());

    return repository.save(product);
  }

  public void deleteProduct (Integer id){
    Product product = repository.findById(id)
            .orElseThrow(()-> new RuntimeException("Product doesn´t exist"));
    product.setStatus(false);

    repository.save(product);
  }
}
