package com.ecommerce.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.api.repository.ReviewRepository;
import com.ecommerce.api.repository.projection.BookRatingProjection;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;

    public List<BookRatingProjection> findRatingStatsByBookIds(List<Long> bookIds) {
        return this.reviewRepository.findRatingStatsByBookIds(bookIds);
    }
}
