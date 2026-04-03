package com.ecommerce.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.dto.response.GetAllCategoriesResponse;
import com.ecommerce.api.service.CategoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

// @formatter:off
@RestController
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryController {
    
    CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<GetAllCategoriesResponse>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }
}
