package com.example.demo.entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Builder
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotBlank
    @Size(min = 3, max = 50)
    @Column(unique = true, nullable = false)
    private String username ;
    @NotBlank
    @Size(min = 6, max = 100)
    @Column(nullable = false)
    private String password ;
    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email ;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles_junction",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<AppUserRole> roles = new HashSet<>();

}
