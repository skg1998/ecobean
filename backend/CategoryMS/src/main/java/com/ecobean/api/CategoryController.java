package com.ecobean.api;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecobean.dto.CategoryDto;
import com.ecobean.service.CategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    } 
    
    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<CategoryDto>> getSubcategoriesByParentId(@PathVariable Long id) {
        List<CategoryDto> subcategoriesDTO = categoryService.getSubcategories(id);
        return ResponseEntity.ok(subcategoriesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
    	List<CategoryDto> categoryDtos = categoryService.getAllCategories();
    	return ResponseEntity.ok(categoryDtos);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDTO) {
        CategoryDto savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedCategoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDTO) {
        CategoryDto updatedCategoryDTO = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
