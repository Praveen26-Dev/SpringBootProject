package com.nextron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextron.dto.LoginRequest;
import com.nextron.dto.RegisterRequest;
import com.nextron.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
 
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public String regis(@RequestBody RegisterRequest req) {
		return authService.register(req);
	}
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
    	return authService.login(req);
    }

}
