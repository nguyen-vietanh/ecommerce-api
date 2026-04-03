package com.ecommerce.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.api.dto.response.GetAllCategoriesResponse;
import com.ecommerce.api.mapper.CategoryMapper;
import com.ecommerce.api.repository.CategoryRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

// @formatter:off
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryService {
    
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public List<GetAllCategoriesResponse> getAllCategories() {
        return this.categoryRepository.findAll().stream()
            .map(category -> this.categoryMapper.toGetAllCategoriesResponse(category))
            .toList();
    }
}
