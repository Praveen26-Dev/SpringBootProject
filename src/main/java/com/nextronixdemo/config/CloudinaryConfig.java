package com.nextronixdemo.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(Map.of(
				"cloud_name","dzlutissq",
				"api_key","973479939145449",
				"api_secret","lxn4rQGj2U6FL1vVgF8lnPYpadc"
				));
	}
}
