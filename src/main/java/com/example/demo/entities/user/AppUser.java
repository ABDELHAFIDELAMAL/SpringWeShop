package com.example.demo.entities.user;

import com.example.demo.entities.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"reviews", "roles", "password"})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private Set<UserRole> userRoles = new HashSet<>() ;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();


}