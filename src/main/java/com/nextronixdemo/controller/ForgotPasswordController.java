package com.nextronixdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nextronixdemo.dto.ForgotRequest;
import com.nextronixdemo.dto.ResetPasswordRequest;
import com.nextronixdemo.service.ForgotPasswordService;

@RestController
@RequestMapping("/auth")
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotRequest request) {
	 forgotPasswordService.sendResetLink(request);
	 return ResponseEntity.ok("If email Exists, verfication link send to email"); 	
	}
	
	@PostMapping("/reset-password/validate")
	public ResponseEntity<String> validateToken(@RequestParam String token)
	{
		return ResponseEntity.ok("Valid Token received");
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest req) {
		forgotPasswordService.resetPass(req);
		return ResponseEntity.ok("Password is changed");
	}
}
