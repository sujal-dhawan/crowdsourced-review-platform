package com.review.reviewplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReviewplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewplatformApplication.class, args);
		System.out.println("Review Platform Backend Running on http://localhost:8080");
	}
}
