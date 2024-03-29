package com.ecobean.category.service;

import java.util.List;

import com.ecobean.category.dto.CategoryDto;

public interface CategoryService {
	List<CategoryDto> getAllCategories();
	CategoryDto getCategoryById(Long id);
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(Long id, CategoryDto categoryDto);
	void deleteCategory(Long id);
	List<CategoryDto> getSubcategories(Long id);
}
 