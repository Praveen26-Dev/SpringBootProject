package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaExampleSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaExampleSpringApplication.class, args);
		System.out.println("Heeloo From JPA");
	}

}
