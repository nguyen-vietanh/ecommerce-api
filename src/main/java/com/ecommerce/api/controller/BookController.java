package com.ecommerce.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.dto.request.GetAllBooksRequest;
import com.ecommerce.api.dto.response.ApiSuccessResponse;
import com.ecommerce.api.dto.response.GetAllBooksReponse;
import com.ecommerce.api.service.BookService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

// @formatter:off
@RestController
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BookController {

    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<ApiSuccessResponse<List<GetAllBooksReponse>>> getBooks(@ModelAttribute @Valid GetAllBooksRequest getAllBooksRequest) {
        return ResponseEntity.ok(this.bookService.getBooks(getAllBooksRequest));
    }
}
