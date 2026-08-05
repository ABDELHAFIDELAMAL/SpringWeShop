package com.example.demo;

import com.example.demo.entities.user.AppUser;
import com.example.demo.entities.user.AppUserRole;
import com.example.demo.entities.user.UserRole;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.user.IAcountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(
			ProductRepository productRepository,
			CategoryRepository categoryRepository,
			ImageRepository imageRepository,
			IAcountService acountService,
			PasswordEncoder passwordEncoder
	) {
		return args -> {
			System.out.println("Server running on http://localhost:8080/api/v1/products/all");
			System.out.println("chakib encoded: " + passwordEncoder.encode("chakib"));

			try {
				if (acountService.getAllRoles().isEmpty()) {
					acountService.addNewRole(AppUserRole.builder().userRole(UserRole.ADMIN).build());
					acountService.addNewRole(AppUserRole.builder().userRole(UserRole.USER).build());
					acountService.addNewRole(AppUserRole.builder().userRole(UserRole.CUSTOMER).build());
					System.out.println("Default roles created successfully!");
				}
			} catch (Exception e) {
				System.out.println("Roles already exist or error: " + e.getMessage());
			}

			try {
				if (acountService.findUserByUserName("admin") == null) {
					AppUser admin = AppUser.builder()
							.username("admin")
							.password("12345678")
							.email("admin@springshop.com")
							.build();

					acountService.addNewUser(admin);

					acountService.addRoleToUser(UserRole.ADMIN, "admin");
					acountService.addRoleToUser(UserRole.USER, "admin");

					System.out.println("Default Admin user created successfully!");
				}
			} catch (Exception e) {
				System.out.println("Admin user already exists or error: " + e.getMessage());
			}
		};
	}
}