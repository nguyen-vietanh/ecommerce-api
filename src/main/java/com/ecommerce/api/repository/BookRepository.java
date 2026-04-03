package com.ecommerce.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.api.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
