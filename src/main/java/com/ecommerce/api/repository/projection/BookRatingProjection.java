package com.ecommerce.api.repository.projection;

// @formatter:off
public interface BookRatingProjection {
    Long getBookId();
    Double getAvgRating();
    Long getReviewCount();
}
