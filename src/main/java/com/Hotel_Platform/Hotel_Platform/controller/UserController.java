package com.Hotel_Platform.Hotel_Platform.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.entity.User;
import com.Hotel_Platform.Hotel_Platform.service.UserService;
import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.USER_MASTER;;

//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("Hotel/user")
//public class UserController {
//	
//	
//	@Autowired
//	private UserService userService;
//	
//
//	
//	@GetMapping
//    public ResponseEntity<List<User>> getUsers(@RequestAttribute("tenantId") Long tenantId) {
//        return ResponseEntity.ok(userService.getUsers(tenantId));
//    }
//
//}


@RestController
@RequestMapping(USER_MASTER)
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // Create user inside logged-in tenant
    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            @RequestAttribute("tenantId") Long tenantId) {

        User createdUser = userService.createUser(user, tenantId);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users of tenant
    @GetMapping
    public ResponseEntity<List<User>> getUsers(
            @RequestAttribute("tenantId") Long tenantId) {

        return ResponseEntity.ok(userService.getUsersByTenant(tenantId));
    }
}






