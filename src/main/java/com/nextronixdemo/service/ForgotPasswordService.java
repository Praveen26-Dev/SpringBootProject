package com.nextronixdemo.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.ForgotRequestDto;
import com.nextronixdemo.dto.ResetPasswordRequestDto;
import com.nextronixdemo.model.User;
import com.nextronixdemo.repository.UserRepository;
import com.nextronixdemo.utils.EmailServiceReg;
import com.nextronixdemo.utils.TokenUtil;

@Service
public class ForgotPasswordService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailServiceReg emailServiceReg;
	
	@Autowired 
	private RedisTemplate<String ,String > redisTemplate;
	
	public void sendResetLink(ForgotRequestDto request) {
	 userRepository.findByEmail(request.getEmail()).ifPresent(user->{
		
//		 String token = TokenUtil.generateToken();
		 
		 String token = UUID.randomUUID().toString();
		 String redisKey="reset:"+token;
		 
		 redisTemplate.opsForValue().set(redisKey,user.getId().toString(),15,TimeUnit.MINUTES);
		 
		 String resetLink = "http://localhost:5173/reset-password?token=" + token;

		 emailServiceReg.sendEmailOtp(
				 user.getEmail(),"Reset You Password ","Click the below link to reset Your Password \n\n"
				 		+resetLink);
	 });
		
	}
	
	public void validateToken(String token) {
		String redisKey="redis:"+token;
		String userId= redisTemplate.opsForValue().get(redisKey);
		
		if(userId==null) {
			throw new RuntimeException("Invlid or expired");
			
		}
		
	}

	public void resetPass(ResetPasswordRequestDto req) {
		String redisKey="reset:"+req.getToken();
		String userId= redisTemplate.opsForValue().get(redisKey);
		
		if(userId==null) {
			throw new RuntimeException("Invlid or expired");
			
		}
		
		User user = userRepository.findById(Integer.valueOf(userId)).orElseThrow(()->new RuntimeException("User Not Found"));
		user.setPassword(passwordEncoder.encode(req.getNewPassword()));
		userRepository.save(user);
		
		redisTemplate.delete(redisKey);
 	}
	
	
}
