package com.Hotel_Platform.Hotel_Platform.dto;

public class OtpVerificationRequest {

	private String email;
    private String otp;
	public OtpVerificationRequest(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
    
    
}
