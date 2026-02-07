package com.review.reviewplatform.repository;

import com.review.reviewplatform.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
