package com.review.reviewplatform.controller;

import com.review.reviewplatform.entity.Business;
import com.review.reviewplatform.entity.Review;
import com.review.reviewplatform.repository.BusinessRepository;
import com.review.reviewplatform.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ApiController {

    private final BusinessRepository businessRepo;
    private final ReviewRepository reviewRepo;

    public ApiController(BusinessRepository businessRepo, ReviewRepository reviewRepo) {
        this.businessRepo = businessRepo;
        this.reviewRepo = reviewRepo;
    }

    @GetMapping("/")
    public String home() {
        return "Review Platform Backend Running";
    }

    // -------- BUSINESS --------
    @GetMapping("/businesses")
    public List<Business> getAllBusinesses() {
        return businessRepo.findAll();
    }

    @PostMapping("/business")
    public Business addBusiness(@RequestBody Business business) {
        business.setAvgRating(0.0);
        return businessRepo.save(business);
    }

    @GetMapping("/business/{id}")
    public Business getBusiness(@PathVariable Long id) {
        return businessRepo.findById(id).orElseThrow();
    }


    // -------- REVIEWS --------
    @PostMapping("/review")
    public Review addReview(@RequestBody Review review) {
        review.setStatus("PENDING");   // moderation enabled
        return reviewRepo.save(review);
    }

    @GetMapping("/reviews/{businessId}")
    public List<Review> getApprovedReviews(@PathVariable Long businessId) {
        return reviewRepo.findByBusinessIdAndStatus(businessId, "APPROVED");
    }

    // -------- ADMIN --------
    @GetMapping("/admin/reviews/pending")
    public List<Review> getPendingReviews() {
        return reviewRepo.findByStatus("PENDING");
    }

    @GetMapping("/review/{id}/approve")
    public Review approveReview(@PathVariable Long id) {
        Review review = reviewRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setStatus("APPROVED");
        reviewRepo.save(review);

        updateAvgRating(review.getBusinessId());
        return review;
    }

    // -------- HELPER --------
    private void updateAvgRating(Long businessId) {
        List<Review> approved = reviewRepo
                .findByBusinessIdAndStatus(businessId, "APPROVED");

        double avg = approved.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        Business business = businessRepo.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        business.setAvgRating(Math.round(avg * 10.0) / 10.0);
        businessRepo.save(business);
    }
}
