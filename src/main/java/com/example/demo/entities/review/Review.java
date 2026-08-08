package com.example.demo.entities.review;

import com.example.demo.entities.Product;
import com.example.demo.entities.user.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"product", "appUser"})
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private int rating;

    @NotBlank(message = "Comment is required")
    @Column(length = 1000)
    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

    public Review(int rating, String comment, Product product, AppUser appUser) {
        this.rating = rating;
        this.comment = comment;
        this.product = product;
        this.appUser = appUser;
    }
}