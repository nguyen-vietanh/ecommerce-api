package com.ecommerce.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiSuccessResponse<T> {

    String message;
    T data;
    Integer currentPage;
    Integer requestedPageSize;
    Integer currentPageElements;
    Long totalElements;
    Integer totalPages;
    Boolean hasNext;
    Boolean hasPrevious;
}
