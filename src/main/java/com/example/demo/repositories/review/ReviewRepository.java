package com.example.demo.repositories.review;
import com.example.demo.entities.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review , Long> {
    List<Review> findByProductId(Long productId);
    List<Review> findByAppUserId(Long userId);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId")
    Double findAverageRatingByProductId(Long productId);
    long countByProductId(Long productId);
}
