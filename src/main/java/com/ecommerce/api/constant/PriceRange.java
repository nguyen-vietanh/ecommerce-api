package com.ecommerce.api.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum PriceRange {
    UNDER_50_000(0L, 50_000L),
    BETWEEN_50_000_100_000(50_000L, 100_000L),
    BETWEEN_100_000_200_000(100_000L, 200_000L),
    BETWEEN_200_000_500_000(200_000L, 500_000L),
    ABOVE_500_000(500_000L, Long.MAX_VALUE);

    Long minPrice;
    Long maxPrice;
}
