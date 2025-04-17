package com.factura.facturacion.service.category;

import com.factura.facturacion.dto.category.DTOCategory;
import com.factura.facturacion.dto.category.DTOResponseCategory;
import com.factura.facturacion.model.category.Category;
import com.factura.facturacion.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository repository;

  public Page<DTOResponseCategory> findAll(Pageable pageable){
    Page<Category> categories = repository.findByStatus(true, pageable);
    List<DTOResponseCategory> descCategories = categories.stream()
            .sorted(Comparator.comparing(Category::getIdCategory).reversed())
            .map(DTOResponseCategory::new)
            .toList();

    return new PageImpl<>(descCategories, pageable, categories.getTotalElements());
  }

  public DTOResponseCategory findById(Integer id){
    Optional<Category> category = repository.findById(id);
    if(category.isPresent()){
      Category c = category.get();
      return new DTOResponseCategory(c.getIdCategory(), c.getNameCategory(), c.getDescriptionCategory(), c.getStatus());
    }
    return null;
  }

  public Category getCategoryById(Integer id){
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Catgory doesn't exist"));
  }

  public Category createCategory(DTOCategory dtoCategory){
    return repository.save(new Category(dtoCategory));
  }

  public Category updateCategory (Integer id, DTOCategory dtoCategory){
    Category category = repository.findById(id)
            .orElseThrow(()-> new RuntimeException("Catgory doesn't exist"));
    category.setNameCategory(dtoCategory.nameCategory());
    category.setDescriptionCategory(dtoCategory.descriptionCategory());
    category.setStatus(dtoCategory.status());

    return repository.save(category);
  }

  public void deleteCategory(Integer id){
    Category category = repository.findById(id)
            .orElseThrow(()-> new RuntimeException("Catgory doesn't exist"));
    category.setStatus(false);

    repository.save(category);
  }
}
