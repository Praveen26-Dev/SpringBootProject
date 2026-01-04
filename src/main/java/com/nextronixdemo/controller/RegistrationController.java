package com.nextronixdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nextronixdemo.dto.RegisterRequestDto;
import com.nextronixdemo.service.RegistrationService;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto req) {
        registrationService.registerUser(req);
        return "User registered. Verify Email and Phone OTP.";
    }

    @PostMapping("/verify-email-otp")
    public String verifyEmail(@RequestParam String email,
                              @RequestParam String otp) {

        return registrationService.verifyEmailOTP(email, otp)
                ? "Email verified"
                : "Invalid or expired OTP";
    }

    @PostMapping("/verify-phone-otp")
    public String verifyPhone(@RequestParam String phoneNo,
                              @RequestParam String otp) {
    	    phoneNo = phoneNo.replace(" ", "+");
        return registrationService.verifyPhoneOTP(phoneNo, otp)
                ? "Phone verified"
                : "Invalid or expired OTP";
    }
}
