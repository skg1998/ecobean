package com.ecobean.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.ecobean.dto.CategoryDto;
import com.ecobean.entity.Category;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	@Mapping(source = "parentCategory.id", target = "parentId")
    CategoryDto toDTO(Category category);

	@Mapping(target = "parentCategory", ignore = true)
    Category toEntity(CategoryDto categoryDTO);
}
