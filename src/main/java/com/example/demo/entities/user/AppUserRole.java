package com.example.demo.entities.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Builder
@Table(name = "users_roles")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRole {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private UserRole userRole ;
    @ManyToMany
    private List<AppUser> appUserList ;
}
