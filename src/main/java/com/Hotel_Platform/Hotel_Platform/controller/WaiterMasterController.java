package com.Hotel_Platform.Hotel_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.dto.WaiterMasterDTO;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.service.WaiterMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.WAITER_MASTER;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(WAITER_MASTER)
public class WaiterMasterController {

	    @Autowired
	    private WaiterMasterService waiterService;

	    // Create new waiter (Admin only)
//	    @PostMapping
//	    @PreAuthorize("hasAuthority('ADMIN')")
//	    public ResponseEntity<WaiterMasterDTO> createWaiter(
//	            @RequestBody Map<String, String> request,
//	            @RequestAttribute("tenantId") Long tenantId) {
//
//	        String waiterName = request.get("waiterName");
//	        String password = request.get("password");
//
//	        Tenant tenant = new Tenant();
//	        tenant.setId(tenantId);
//
//	        WaiterMasterDTO waiter = waiterService.createWaiter(waiterName, password, tenant);
//	        return ResponseEntity.ok(waiter);
//	    }
	    
	    @PostMapping
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public ResponseEntity<WaiterMasterDTO> createWaiter(
	            @RequestBody Map<String, String> request,
	            @RequestAttribute("tenantId") Long tenantId) {

	        String waiterName = request.get("waiterName");
	        String password = request.get("password");

	        WaiterMasterDTO waiter = waiterService.createWaiter(waiterName, password, tenantId);
	        return ResponseEntity.ok(waiter);
	    }


	    // Get all waiters of tenant
	    @GetMapping
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public ResponseEntity<List<WaiterMasterDTO>> getAllWaiters(@RequestAttribute("tenantId") Long tenantId) {
	        return ResponseEntity.ok(waiterService.getAllWaiters(tenantId));
	    }
	}

	
	
	


