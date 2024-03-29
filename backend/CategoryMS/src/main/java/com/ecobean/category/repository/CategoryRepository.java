package com.ecobean.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecobean.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findById(Long parentCategoryId);
	List<Category> findByParentCategory(Category parentCategory);

}
