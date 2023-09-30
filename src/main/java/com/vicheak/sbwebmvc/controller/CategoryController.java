package com.vicheak.sbwebmvc.controller;

import com.vicheak.sbwebmvc.model.Category;
import com.vicheak.sbwebmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> loadCategories() {
        return categoryService.loadCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNewCategory(@RequestBody Category category) {
        categoryService.createNewCategory(category);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateCategory(@PathVariable("id") Integer id,
                               @RequestBody Category category) {
        categoryService.updateCategoryById(id, category);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategoryById(id);
    }

    @GetMapping("/{id}")
    public Category loadCategoryById(@PathVariable("id") Integer id) {
        return categoryService.loadCategoryById(id);
    }

}
