package com.ingesoft2.controller;

import java.util.List;

import com.ingesoft2.pojo.CategoriesPOJO;
import com.ingesoft2.pojo.CategoriesSingleton;
import com.ingesoft2.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/api/category")
public class CategoryController {
  
  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService){
    this.categoryService = categoryService;
  }

  @GetMapping(value = {"/categories"})
  public List<CategoriesPOJO> getCategories() {
    CategoriesPOJO categoriesPOJO = new CategoriesPOJO();//Solo creo el objeto para poder llamar la función
    return categoriesPOJO.categoriesPOJO(CategoriesSingleton.getInstance(categoryService.getCategories()).getCategories());
  }
}