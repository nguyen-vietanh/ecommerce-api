package com.ecommerce.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.api.dto.request.GetAllBooksRequest;
import com.ecommerce.api.dto.response.ApiSuccessResponse;
import com.ecommerce.api.dto.response.GetAllBooksReponse;
import com.ecommerce.api.entity.Book;
import com.ecommerce.api.mapper.BookMapper;
import com.ecommerce.api.repository.BookRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

// @formatter:off
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BookService {

    BookRepository bookRepository;
    BookMapper bookMapper;

    public ApiSuccessResponse<List<GetAllBooksReponse>> getBooks(GetAllBooksRequest getAllBooksRequest) {
        Pageable pageable = PageRequest.of(
            getAllBooksRequest.getPage() - 1,
            getAllBooksRequest.getSize(),
            Sort.by(getAllBooksRequest.getSort().getDirection(), getAllBooksRequest.getSort().getField())
        );
        Page<Book> bookPage = this.bookRepository.findAll(pageable);
        return ApiSuccessResponse.<List<GetAllBooksReponse>>builder()
            .data(bookPage.getContent().stream()
                .map(book -> this.bookMapper.toGetAllBooksReponse(book))
                .toList())
            .currentPage(bookPage.getNumber() + 1)
            .requestedPageSize(bookPage.getSize())
            .currentPageElements(bookPage.getNumberOfElements())
            .totalElements(bookPage.getTotalElements())
            .totalPages(bookPage.getTotalPages())
            .hasNext(bookPage.hasNext())
            .hasPrevious(bookPage.hasPrevious())
            .build();
    }
}
