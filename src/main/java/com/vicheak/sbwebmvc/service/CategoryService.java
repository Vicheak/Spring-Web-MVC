package com.vicheak.sbwebmvc.service;

import com.vicheak.sbwebmvc.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> loadCategories();

    void createNewCategory(Category category);

    void updateCategoryById(Integer id, Category category);

    void deleteCategoryById(Integer id);

    Category loadCategoryById(Integer id);

}
