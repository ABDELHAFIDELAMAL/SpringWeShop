package com.example.demo.controllers.review;

import com.example.demo.entities.review.Review;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${api.prefix}/reviews")
public class ReviewController  {

    @Autowired
    private IReviewService reviewService ;

    @GetMapping(path = "/all")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }


    @PostMapping(path = "/add")
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @GetMapping(path = "/{id}/review")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping(path = "/{productId}/product")
    public List<Review> getReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping(path = "/{userId}/user")
    public List<Review> getReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @PutMapping(path = "/{id}/update")
    public Review updateReview(@PathVariable Long id,@RequestBody Review review) {
        return reviewService.updateReview(id , review);
    }
    @DeleteMapping(path = "/{id}/delete")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    @GetMapping(path = "/{productId}/average")
    public Double getAverageRatingByProductId(@PathVariable Long productId) {
        return reviewService.getAverageRatingByProductId(productId);
    }

    @GetMapping(path = "/{productId}/count")
    public long getReviewCountByProductId(@PathVariable Long productId) {
        return reviewService.getReviewCountByProductId(productId);
    }
}
