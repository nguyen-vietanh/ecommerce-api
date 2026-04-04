package com.ecommerce.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.api.entity.Review;
import com.ecommerce.api.repository.projection.BookRatingProjection;

// @formatter:off
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT
            r.book.id AS bookId,
            AVG(r.rating) AS avgRating,
            COUNT(r.id) AS reviewCount
        FROM Review r
        WHERE r.book.id IN :bookIds
        GROUP BY r.book.id
    """)
    List<BookRatingProjection> findRatingStatsByBookIds(@Param("bookIds") List<Long> bookIds);
}
