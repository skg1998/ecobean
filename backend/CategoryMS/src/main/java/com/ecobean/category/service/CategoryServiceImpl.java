package com.ecobean.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecobean.category.dto.CategoryDto;
import com.ecobean.category.entity.Category;
import com.ecobean.category.exception.EntityNotFoundException;
import com.ecobean.category.mapper.CategoryMapper;
import com.ecobean.category.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
        return mapToDto(category);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    public List<CategoryDto> getSubcategories(Long parentId) {
        Category parentCategory = categoryRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent category with ID " + parentId + " not found"));

        List<Category> subcategories = categoryRepository.findByParentCategory(parentCategory);
        if (subcategories.isEmpty()) {
            throw new EntityNotFoundException("No subcategories found for parent category with ID " + parentId);
        }

        return subcategories.stream()
                .map(CategoryMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = mapToEntity(categoryDto);
        if (categoryDto.getParentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent category not found with id " + categoryDto.getParentCategoryId()));
            category.setParentCategory(parentCategory);
        }
        Category savedCategory = categoryRepository.save(category);
        return mapToDto(savedCategory);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
        Category updatedCategory = mapToEntity(categoryDto);
        updatedCategory.setId(existingCategory.getId());
        if (categoryDto.getParentCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(categoryDto.getParentCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent category not found with id " + categoryDto.getParentCategoryId()));
            updatedCategory.setParentCategory(parentCategory);
        }
        Category savedCategory = categoryRepository.save(updatedCategory);
        return mapToDto(savedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setImage(category.getImage());
        if (category.getSubCategories() != null) {
            categoryDto.setSubCategories(category.getSubCategories().stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList()));
        }
        if (category.getParentCategory() != null) {
            categoryDto.setParentCategoryId(category.getParentCategory().getId());
        }
        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setImage(categoryDto.getImage());
        if (categoryDto.getSubCategories() != null) {
            category.setSubCategories(categoryDto.getSubCategories().stream()
                    .map(this::mapToEntity)
                    .collect(Collectors.toSet()));
        }
        return category;
    }
}
