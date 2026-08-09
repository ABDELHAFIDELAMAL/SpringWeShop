package com.example.demo.services.review;

import com.example.demo.entities.review.Review;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.review.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewService implements IReviewService{
    @Autowired
    private ReviewRepository reviewRepository ;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).
                orElseThrow(()->new RuntimeException("Review not found with id :" + id));
    }


    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByAppUserId(userId);
    }

    @Override
    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete. Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    @Override
    public Double getAverageRatingByProductId(Long productId) {
        Double avgRating = reviewRepository.findAverageRatingByProductId(productId);
        return (avgRating != null) ? avgRating : 0.0;
    }

    @Override
    public long getReviewCountByProductId(Long productId) {
        return reviewRepository.countByProductId(productId);
    }
}
