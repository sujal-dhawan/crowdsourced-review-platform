package com.review.reviewplatform.repository;

import com.review.reviewplatform.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBusinessIdAndStatus(Long businessId, String status);
    List<Review> findByStatus(String status);
}
