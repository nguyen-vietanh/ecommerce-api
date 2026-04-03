package com.ecommerce.api.specification;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerce.api.entity.Book;

import jakarta.persistence.criteria.Predicate;

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

    private BookSpecs() {

    }
}
