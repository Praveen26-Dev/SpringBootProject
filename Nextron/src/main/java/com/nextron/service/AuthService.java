package com.nextron.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextron.dto.LoginRequest;
import com.nextron.dto.RegisterRequest;
import com.nextron.model.User;
import com.nextron.repository.UserRepository;
import com.nextron.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	@Autowired
    private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
	
	public String register(RegisterRequest req) {
		
		if(userRepository.existsByEmail(req.getEmail())) {
			return "Email already Registered";
		}
		
		User user = User.builder()
				.name(req.getName())
				.email(req.getEmail())
				.password(passwordEncoder.encode(req.getPassword()))
				.age(req.getAge())
				.phoneNo(req.getPhoneNo())
				.build();
		
		userRepository.save(user);
	    return "User registered";
	}
	public String login(LoginRequest req) {

	    User user = userRepository.findByEmail(req.getEmail())
	            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

	    if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid credentials");
	    }

	    return jwtUtil.generateToken(user.getEmail());
	}
	
}
