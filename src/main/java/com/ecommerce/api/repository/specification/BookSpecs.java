package com.ecommerce.api.repository.specification;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerce.api.constant.PriceRange;
import com.ecommerce.api.constant.ReviewStatus;
import com.ecommerce.api.entity.Book;
import com.ecommerce.api.entity.Review;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class BookSpecs {

    public static Specification<Book> hasTitleOrAuthorContains(String searchKeyword) {
        return (root, query, cb) -> {
            if (searchKeyword == null || searchKeyword.isBlank()) {
                return null;
            }

            String[] tokens = searchKeyword.trim().toLowerCase().split("\\s+");

            List<Predicate> tokenPredicates = Arrays.stream(tokens)
                    .map(token -> {
                        String pattern = "%" + token + "%";
                        Predicate hasTitleContains = cb.like(cb.lower(root.get("title")), pattern);
                        Predicate hasAuthorContains = cb.like(cb.lower(root.get("author")), pattern);
                        return cb.or(hasTitleContains, hasAuthorContains);
                    })
                    .toList();

            return cb.and(tokenPredicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Book> inCategory(String categorySlug) {
        return (root, query, cb) -> {
            if (categorySlug == null || categorySlug.isBlank()) {
                return null;
            }
            return cb.equal(root.get("category").get("slug"), categorySlug);
        };
    }

    public static Specification<Book> inPriceRange(PriceRange priceRange) {
        return (root, query, cb) -> {
            if (priceRange == null) {
                return null;
            }
            return cb.between(root.get("price"), priceRange.getMinPrice(), priceRange.getMaxPrice());
        };
    }

    public static Specification<Book> hasMinRating(Integer minRating) {
        return (root, query, cb) -> {
            if (minRating == null) {
                return null;
            }

            Subquery<Double> avgRatingSubquery = query.subquery(Double.class);
            Root<Review> reviewRoot = avgRatingSubquery.from(Review.class);
            avgRatingSubquery
                    .select(cb.avg(reviewRoot.get("rating")))
                    .where(
                            cb.equal(reviewRoot.get("book"), root),
                            cb.equal(reviewRoot.get("status"), ReviewStatus.APPROVED));

            return cb.greaterThanOrEqualTo(avgRatingSubquery, (double) minRating);
        };
    }

    private BookSpecs() {

    }
}
