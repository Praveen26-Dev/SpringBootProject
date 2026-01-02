package com.nextronixdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.LoginRequest;
import com.nextronixdemo.dto.RegisterRequest;
import com.nextronixdemo.model.User;
import com.nextronixdemo.repository.UserRepository;
import com.nextronixdemo.security.JwtUtil;

@Service
public class LoginAuthService {

	@Autowired
    private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

//    AuthService(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//	
//	public String register(RegisterRequest req) {
//		
//		if(userRepository.existsByEmail(req.getEmail())) {
//			return "Email already Registered";
//		}
//		
//		User user = User.builder()
//				.name(req.getName())
//				.email(req.getEmail())
//				.password(passwordEncoder.encode(req.getPassword()))
//				.build();
//		
//		userRepository.save(user);
//	    return "User registered";
//	}
	
	
	public String login(LoginRequest req) {

        String identifier = req.getIdentifier();
        String password = req.getPassword();

        User user;

        // 🔍 Decide identifier type
        if (identifier.contains("@")) {
            // EMAIL LOGIN
            user = userRepository.findByEmail(identifier)
                    .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        } else if (identifier.matches("\\d{10}")) {
            // PHONE LOGIN
            user = userRepository.findByPhoneNo(identifier)
                    .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        } else {
            // USERNAME LOGIN
            user = userRepository.findByName(identifier)
                    .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));
        }

        // 🔐 Password verification
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ JWT generation
        return jwtUtil.generateToken(user.getEmail());
    }
}

