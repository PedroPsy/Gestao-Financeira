package com.example.finances.service;

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
        Optional<Category> ca = categoryRepository.findById(id);
        if(ca.isPresent()){
            Category categoryUp = ca.get();
            categoryUp.setName(category.getName());
            categoryUp.setId(category.getId());
            categoryUp.setUser(category.getUser());
            return categoryRepository.save(categoryUp);
        }
        return null;
    }
    public Category deleteCategory(Long id) {
        Optional<Category> ca = categoryRepository.findById(id);
        if(ca.isPresent()){
            categoryRepository.delete(ca.get());
            return ca.get();
        }
        return null;
    }

    public Category getCategoryById(Long categoryId) {
        return  categoryRepository.getOne(categoryId);
    }

}
