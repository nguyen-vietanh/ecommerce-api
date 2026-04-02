package com.ecommerce.api.entity;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Book extends BaseEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(300)")
    String title;

    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    String author;

    @Column(unique = true, columnDefinition = "VARCHAR(13)")
    String isbn;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @Column(nullable = false, check = @CheckConstraint(constraint = "price > 0"))
    Long price; // VND

    @Column(columnDefinition = "VARCHAR(500)")
    String coverImageUrl;

    @Column(columnDefinition = "SMALLINT")
    Integer publicationYear;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT 1")
    Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    Inventory inventory;
}
