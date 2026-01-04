package com.nextronixdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.GoogleLoginRequestDto;
import com.nextronixdemo.dto.LoginRequestDto;
import com.nextronixdemo.dto.RegisterRequestDto;
import com.nextronixdemo.service.LoginAuthService;
import com.nextronixdemo.service.GoogleAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class AuthController {
 
	@Autowired
	private LoginAuthService loginAuthService;
	
	@Autowired
	private GoogleAuthService googleAuthService;
	
//	@PostMapping("/register")
//	public String regis(@RequestBody RegisterRequest req) {
//		return authService.register(req);
//	}
	
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto req) {
    	return loginAuthService.login(req);
    }
    
    @PostMapping("/google")
    public String googleLogin(@RequestBody GoogleLoginRequestDto req) {
    	 return googleAuthService.SignInWithGoogle(req.getIdToken());
    }
    

}
