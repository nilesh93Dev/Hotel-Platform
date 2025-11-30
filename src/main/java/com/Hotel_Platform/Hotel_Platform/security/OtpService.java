package com.Hotel_Platform.Hotel_Platform.security;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	
	private final Map<String, String> otpMap = new ConcurrentHashMap<>();
	
	public String generateOtp(String email) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // 6-digit OTP
        otpMap.put(email, otp);
        return otp;
    }
	
	public boolean validateOtp(String email, String otp) {
        return otp.equals(otpMap.get(email));
    }

    public void clearOtp(String email) {
        otpMap.remove(email);
    }
	

}
