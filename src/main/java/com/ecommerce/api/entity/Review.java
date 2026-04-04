package com.ecommerce.api.entity;

import com.ecommerce.api.constant.ReviewStatus;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Review extends BaseEntity {

    @Column(nullable = false, columnDefinition = "SMALLINT", check = @CheckConstraint(constraint = "rating >= 1 AND rating <= 5"))
    Integer rating;

    @Column(columnDefinition = "MEDIUMTEXT")
    String comment;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    ReviewStatus status = ReviewStatus.PENDING;

    @Column(columnDefinition = "TEXT")
    String rejectedReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    Book book;
}
