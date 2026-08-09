package com.example.demo.services.review;

import com.example.demo.entities.review.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getAllReviews();
    Review addReview(Review review);
    Review getReviewById(Long id);
    List<Review> getReviewsByProductId(Long productId);
    List<Review> getReviewsByUserId(Long userId);
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
    Double getAverageRatingByProductId(Long productId);
    long getReviewCountByProductId(Long productId);
}
