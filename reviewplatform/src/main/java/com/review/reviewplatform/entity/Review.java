package com.review.reviewplatform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "business_id", nullable = false)
    private Long businessId;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private String status; // PENDING / APPROVED / REJECTED

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getReviewId() { return reviewId; }

    public Long getBusinessId() { return businessId; }
    public void setBusinessId(Long businessId) { this.businessId = businessId; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
