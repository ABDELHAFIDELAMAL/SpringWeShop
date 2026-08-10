package com.example.demo.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<AppUser> appUserList = new HashSet<>();

    public AppUserRole(UserRole userRole) {
        this.userRole = userRole ;
    }
}
