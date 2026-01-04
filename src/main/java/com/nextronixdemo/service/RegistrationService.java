package com.nextronixdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextronixdemo.dto.RegisterRequestDto;
import com.nextronixdemo.model.User;
import com.nextronixdemo.repository.UserRepository;
import com.nextronixdemo.utils.EmailServiceReg;
import com.nextronixdemo.utils.OtpUtil;
import com.nextronixdemo.utils.PhoneService;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServiceReg emailService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // REGISTER
    public void registerUser(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        String formattedPhone = request.getPhoneNo().startsWith("+")
                ? request.getPhoneNo()
                : "+91" + request.getPhoneNo();

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNo(formattedPhone)
                .emailVarified(false)
                .phoneVarified(false)
                .status("PENDING")
                .build();

        userRepository.save(user);

        String emailOtp = OtpUtil.generateOtp();
        String phoneOtp = OtpUtil.generateOtp();

        redisTemplate.opsForValue()
                .set("email_otp:" + request.getEmail(), emailOtp, 15, TimeUnit.MINUTES);

        redisTemplate.opsForValue()
                .set("phone_otp:" + formattedPhone, phoneOtp, 15, TimeUnit.MINUTES);

        emailService.sendEmailOtp(
                request.getEmail(),
                "Verify Your Email",
                "Your OTP is: " + emailOtp
        );

        phoneService.sendOtp(formattedPhone, phoneOtp);
    }

    // VERIFY EMAIL
    public boolean verifyEmailOTP(String email, String otp) {

        String key = "email_otp:" + email;
        String redisOtp = redisTemplate.opsForValue().get(key);

        if (redisOtp != null && redisOtp.equals(otp)) {

            redisTemplate.delete(key);

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setEmailVarified(true);
            activateIfReady(user);
            userRepository.save(user);

            return true;
        }
        return false;
    }

    // VERIFY PHONE
    public boolean verifyPhoneOTP(String phoneNo, String otp) {

    	  String formattedPhone = phoneNo.trim();
    	   if (!formattedPhone.startsWith("+")) {
   	    formattedPhone = "+91" + formattedPhone;
	    }
        String key = "phone_otp:" + formattedPhone;

        String redisOtp = redisTemplate.opsForValue().get(key);

        if (redisOtp != null && redisOtp.equals(otp)) {

            redisTemplate.delete(key);

            User user = userRepository.findByPhoneNo(formattedPhone)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setPhoneVarified(true);
            activateIfReady(user);
            userRepository.save(user);

            return true;
        }
        return false;
    }

    private void activateIfReady(User user) {
        if (user.isEmailVarified() && user.isPhoneVarified()) {
            user.setStatus("ACTIVE");
        }
    }
}
