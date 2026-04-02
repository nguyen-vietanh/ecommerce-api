package com.ecommerce.api.mapper;

import org.mapstruct.Mapper;

import com.ecommerce.api.dto.response.GetAllBooksReponse;
import com.ecommerce.api.entity.Book;

// @formatter:off
@Mapper(componentModel = "spring")
public interface BookMapper {

    GetAllBooksReponse toGetAllBooksReponse(Book book);
}
