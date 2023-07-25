package com.example.springmemoreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringMemoReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMemoReviewApplication.class, args);
	}

}
