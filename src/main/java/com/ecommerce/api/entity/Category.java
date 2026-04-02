package com.ecommerce.api.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    String slug;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    String nameEn;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(100)")
    String nameVi;

    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 0")
    Integer displayOrder;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT 1")
    Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    List<Book> books;
}
