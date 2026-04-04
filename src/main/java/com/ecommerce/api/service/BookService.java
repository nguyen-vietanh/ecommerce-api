package com.ecommerce.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecommerce.api.constant.PriceRange;
import com.ecommerce.api.dto.request.GetAllBooksRequest;
import com.ecommerce.api.dto.response.ApiSuccessResponse;
import com.ecommerce.api.dto.response.GetAllBooksReponse;
import com.ecommerce.api.entity.Book;
import com.ecommerce.api.repository.BookRepository;
import com.ecommerce.api.repository.projection.BookRatingProjection;
import com.ecommerce.api.repository.specification.BookSpecs;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

// @formatter:off
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BookService {

    BookRepository bookRepository;
    // BookMapper bookMapper;
    ReviewService reviewService;

    public ApiSuccessResponse<List<GetAllBooksReponse>> getBooks(GetAllBooksRequest getAllBooksRequest) {
        Pageable pageable = this.buildPageable(getAllBooksRequest);
        Specification<Book> bookSpecs = this.builBookSpec(getAllBooksRequest);
        Page<Book> bookPage = this.bookRepository.findAll(bookSpecs, pageable);

        List<Long> bookIds = bookPage.getContent().stream()
            .map(book -> book.getId())
            .toList();
        Map<Long, BookRatingProjection> statsMap = this.reviewService.findRatingStatsByBookIds(bookIds)
            .stream()
            .collect(Collectors.toMap(bookRatingProjection -> bookRatingProjection.getBookId(), bookRatingProjection -> bookRatingProjection));

        return ApiSuccessResponse.<List<GetAllBooksReponse>>builder()
            .data(bookPage.getContent().stream()
                .map(book -> GetAllBooksReponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .price(book.getPrice())
                    .coverImageUrl(book.getCoverImageUrl())
                    .avgRating(
                        Optional.ofNullable(statsMap.get(book.getId()))
                            .map(bookRatingProjection -> bookRatingProjection.getAvgRating())
                            .orElse(0d)
                    )
                    .reviewCount(
                        Optional.ofNullable(statsMap.get(book.getId()))
                            .map(bookRatingProjection -> bookRatingProjection.getReviewCount())
                            .orElse(0L)
                    )
                    .build())
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

    private Specification<Book> builBookSpec(GetAllBooksRequest getAllBooksRequest) {
        Specification<Book> bookSpecs = (root, query, cb) -> null;

        String search = getAllBooksRequest.getSearch();
        if(search != null && !search.isBlank()) {
            Specification<Book> titleOrAuthorContains = BookSpecs.hasTitleOrAuthorContains(search);
            bookSpecs = bookSpecs.and(titleOrAuthorContains);
        }

        String category = getAllBooksRequest.getCategory();
        if(category != null && !category.isBlank()) {
            Specification<Book> inCategory = BookSpecs.inCategory(category);
            bookSpecs = bookSpecs.and(inCategory);
        }

        PriceRange priceRange = getAllBooksRequest.getPrice();
        if (priceRange != null) {
            bookSpecs = bookSpecs.and(BookSpecs.inPriceRange(priceRange));
        }

        return bookSpecs;
    }

    private Pageable buildPageable(GetAllBooksRequest getAllBooksRequest) {
        return PageRequest.of(
            getAllBooksRequest.getPage() - 1,
            getAllBooksRequest.getLimit(),
            Sort.by(getAllBooksRequest.getSort().getDirection(), getAllBooksRequest.getSort().getField())
        );
    }
}
