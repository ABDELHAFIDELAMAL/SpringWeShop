package com.example.demo.services.review;

import com.example.demo.entities.review.Review;
import com.example.demo.repositories.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private ReviewRepository reviewRepository ;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review addReview(Review review) {
        return null;
    }

    @Override
    public Review getReviewById(Long id) {
        return null;
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return List.of();
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return List.of();
    }

    @Override
    public Review updateReview(Long id, Review review) {
        return null;
    }

    @Override
    public void deleteReview(Long id) {

    }

    @Override
    public Double getAverageRatingByProductId(Long productId) {
        return 0.0;
    }

    @Override
    public long getReviewCountByProductId(Long productId) {
        return 0;
    }
}
