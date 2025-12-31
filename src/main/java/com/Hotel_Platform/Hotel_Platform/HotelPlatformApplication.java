package com.Hotel_Platform.Hotel_Platform;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class HotelPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelPlatformApplication.class, args);
	}
	
	@PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println("Forced JVM TimeZone: " + TimeZone.getDefault().getID());
    }

}
