package com.example.finances.controller;

import com.example.finances.dto.CategoryDto;
import com.example.finances.models.Category;
import com.example.finances.models.User;
import com.example.finances.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;


    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/categories")
    public Category save(@RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return categoryService.createCategory(category);
    }
    @PutMapping("/categories/{id}")
    public Category update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = convertToEntity(categoryDto);
        return categoryService.updateCategory(id, category);
    }
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
    @GetMapping("/categories")
    public Category getCategory(@RequestParam Long id) {
        return categoryService.getCategoryById(id);
    }

    private CategoryDto convertToDTO(User u) {
        return modelMapper.map(u, CategoryDto.class);
    }
    private Category convertToEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

}
