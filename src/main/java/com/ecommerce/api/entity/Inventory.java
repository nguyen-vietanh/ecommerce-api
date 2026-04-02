package com.ecommerce.api.entity;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
@Table(name = "inventories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Inventory extends BaseEntity {

    @Column(nullable = false, columnDefinition = "INTEGER", check = @CheckConstraint(constraint = "quantity >= 0"))
    @Builder.Default
    Integer quantity = 0;

    @Column(nullable = false, columnDefinition = "INTEGER", check = @CheckConstraint(constraint = "reserved_quantity >= 0"))
    Integer reservedQuantity;

    @Column(nullable = false, columnDefinition = "INTEGER")
    @Builder.Default
    Integer lowStockThreshold = 5;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    Book book;
}
