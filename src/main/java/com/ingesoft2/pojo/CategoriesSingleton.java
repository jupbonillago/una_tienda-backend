package com.ingesoft2.pojo;

import java.util.List;


import com.ingesoft2.models.Category;

public class CategoriesSingleton {
    private static CategoriesSingleton uniqueInstance;
    private List<Category> categories;

    private CategoriesSingleton(List<Category> categories2){
        this.categories = categories2;
    }

    public static CategoriesSingleton getInstance(List<Category> categories2){
        if(uniqueInstance == null){
            uniqueInstance = new CategoriesSingleton(categories2);
        }
        return uniqueInstance;
    }

    

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public static CategoriesSingleton getUniqueInstance() {
        return uniqueInstance;
    }

    public static void setUniqueInstance(CategoriesSingleton uniqueInstance) {
        CategoriesSingleton.uniqueInstance = uniqueInstance;
    }



}
