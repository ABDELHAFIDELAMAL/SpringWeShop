package com.example.demo;


import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("Server running ... on http://localhost:8080/api/v1/products/all");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	 PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(
			ProductRepository productRepository ,
			CategoryRepository categoryRepository ,
			ImageRepository imageRepository
	){
		return args -> {
			System.out.println("chakib est : " + passwordEncoder().encode("chakib") );
		};
	}

}
