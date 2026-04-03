package com.ecommerce.api.constant;

import org.springframework.data.domain.Sort;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum SortCriteria {
    NEWEST("createdAt", Sort.Direction.DESC),
    PRICE_ASC("price", Sort.Direction.ASC),
    PRICE_DESC("price", Sort.Direction.DESC),
    TITLE_ASC("title", Sort.Direction.ASC);

    String field;
    Sort.Direction direction;
}
