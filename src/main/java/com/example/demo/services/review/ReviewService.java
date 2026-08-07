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
}
