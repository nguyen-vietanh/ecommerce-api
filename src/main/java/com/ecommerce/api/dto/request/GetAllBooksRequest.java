package com.ecommerce.api.dto.request;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.api.constant.PriceRange;
import com.ecommerce.api.constant.SortCriteria;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetAllBooksRequest {

    @Min(value = 1)
    @NotNull
    Integer page = 1;

    @Min(value = 1)
    @Max(value = 100)
    @NotNull
    Integer limit = 20;

    @NotNull
    SortCriteria sort = SortCriteria.NEWEST;

    @Length(max = 200)
    String search;

    String category;

    PriceRange price;

    @Min(value = 1)
    @Max(value = 5)
    Integer rating;
}
