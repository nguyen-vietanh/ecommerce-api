package com.ecommerce.api.mapper;

import org.mapstruct.Mapper;

import com.ecommerce.api.dto.response.GetAllCategoriesResponse;
import com.ecommerce.api.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    GetAllCategoriesResponse toGetAllCategoriesResponse(Category category);
}
