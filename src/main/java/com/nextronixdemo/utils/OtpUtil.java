package com.nextronixdemo.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {

    public static String generateOtp() {
        Random random = new Random();
        int number=100000+random.nextInt(900000);
        return String.valueOf(number);
    }
}
