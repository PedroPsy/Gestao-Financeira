package com.example.finances.service;

import com.example.finances.exception.CategoryNotFound;
import com.example.finances.models.Category;
import com.example.finances.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Category updateCategory(Long id, Category category) {
        Category categoryUp = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFound("Category not found"));
        categoryUp.setName(category.getName());
        categoryUp.setId(category.getId());
        categoryUp.setUser(category.getUser());
        return categoryRepository.save(categoryUp);
    }
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFound("Category not found"));
        categoryRepository.delete(category);
    }

    public Category getCategoryById(Long categoryId) {
        return  categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFound("Category not found"));
    }

}
