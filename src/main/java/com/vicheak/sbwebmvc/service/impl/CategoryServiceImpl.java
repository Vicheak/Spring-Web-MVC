package com.vicheak.sbwebmvc.service.impl;

import com.vicheak.sbwebmvc.model.Category;
import com.vicheak.sbwebmvc.repository.CategoryRepository;
import com.vicheak.sbwebmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> loadCategories() {
        return categoryRepository.select();
    }

    @Override
    public void createNewCategory(Category category) {
        category.setDescription(category.getName());
        categoryRepository.insert(category);
    }

    @Override
    public void updateCategoryById(Integer id, Category category) {
        category.setId(id);
        category.setDescription(category.getName());
        categoryRepository.update(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category loadCategoryById(Integer id) {
        return categoryRepository.selectById(id).orElseThrow();
    }

}
