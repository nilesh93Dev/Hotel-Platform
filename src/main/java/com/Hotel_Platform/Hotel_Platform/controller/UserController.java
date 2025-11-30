package com.Hotel_Platform.Hotel_Platform.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.dto.UserCreateRequest;
import com.Hotel_Platform.Hotel_Platform.entity.User;
import com.Hotel_Platform.Hotel_Platform.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Hotel/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
//	@PostMapping
//	public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request ,
//			                               @RequestHeader("tenantId") Long tenantId){
//		
//		return ResponseEntity.ok(userService.createUser(request, tenantId));
//	}
	
	@GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestAttribute("tenantId") Long tenantId) {
        return ResponseEntity.ok(userService.getUsers(tenantId));
    }

}
