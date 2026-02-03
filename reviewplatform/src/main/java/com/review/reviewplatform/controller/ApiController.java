package com.review.reviewplatform.controller;

import com.review.reviewplatform.entity.Business;
import com.review.reviewplatform.entity.Review;
import com.review.reviewplatform.repository.BusinessRepository;
import com.review.reviewplatform.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ApiController {

    private final BusinessRepository businessRepo;
    private final ReviewRepository reviewRepo;

    public ApiController(BusinessRepository businessRepo, ReviewRepository reviewRepo) {
        this.businessRepo = businessRepo;
        this.reviewRepo = reviewRepo;
    }

    @GetMapping("/")
    public String home() {
        return "Backend running";
    }

    @PostMapping("/business")
    public Business addBusiness(@RequestBody Business b) {
        return businessRepo.save(b);
    }

    @PostMapping("/review")
    public Review addReview(@RequestBody Review r) {
        r.setStatus("PENDING");
        return reviewRepo.save(r);
    }

    @PutMapping("/review/{id}/approve")
    public Review approve(@PathVariable Long id) {
        Review r = reviewRepo.findById(id).orElseThrow();
        r.setStatus("APPROVED");
        return reviewRepo.save(r);
    }

    @GetMapping("/reviews/{businessId}")
    public List<Review> approvedReviews(@PathVariable Long businessId) {
        return reviewRepo.findByBusinessIdAndStatus(businessId, "APPROVED");
    }
}

