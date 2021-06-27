package com.ingesoft2.services;

import java.util.List;

import com.ingesoft2.models.Category;
import com.ingesoft2.repositories.CategoryRepository;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  
  private final CategoryRepository categoryRepository;


  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }


  public List<Category> getCategories(){
    return categoryRepository.findAll();
  }

  public Category getCategory(Integer id){
    return categoryRepository.getOne(id);
  }
}
