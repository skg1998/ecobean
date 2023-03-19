package com.ecobean.category.dto;
import java.util.List;

import lombok.Data;

@Data
public class CategoryDto {
	private Long id;
    private String name;
    private String image;
    private List<CategoryDto> subCategories;
    private Long parentCategoryId;
}
