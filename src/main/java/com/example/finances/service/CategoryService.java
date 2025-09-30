package com.example.finances.service;

import com.example.finances.exception.CategoryNotFound;
import com.example.finances.models.Category;
import com.example.finances.models.User;
import com.example.finances.repository.CategoryRepository;
import com.example.finances.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    public CategoryService(CategoryRepository categoryRepository,  UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    public Category createCategory(Category category, String username) {
        User user = userRepository.findByUsername(username);
        category.setUser(user);
        return categoryRepository.save(category);
    }
    public Category updateCategory(Long id, Category category) {
        Category categoryUp = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category not found"));
        categoryUp.setName(category.getName());
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
