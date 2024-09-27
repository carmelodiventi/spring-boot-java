package com;

import com.entity.User;
import com.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.entity")
@EnableJpaRepositories(basePackages = "com.repository")
public class SportyShoesApplication {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesApplication.class, args);
		System.out.println("Application started successfully at http://localhost:8080");
	}

	@PostConstruct
	public void init(){
		String email = "admin@gmail.com";
		String password = "123456";
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRole("admin");
		String result = userService.signUp(user);
		if(result.equals("success")){
			System.out.println("Admin user created successfully with email: " + email + " and password " + password);
		}
	}

}
