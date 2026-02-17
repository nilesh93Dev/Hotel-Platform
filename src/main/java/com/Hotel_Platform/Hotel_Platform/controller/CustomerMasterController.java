package com.Hotel_Platform.Hotel_Platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Platform.Hotel_Platform.dto.CustomerMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.CustomerMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.CUSTOMER_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(CUSTOMER_MASTER)
public class CustomerMasterController {

    @Autowired
    private CustomerMasterService customerService;

    // Create Customer
    @PostMapping
    public ResponseEntity<SuccessResponse<CustomerMasterDTO>> createCustomer(
            @RequestBody CustomerMasterDTO customerDto,
            @RequestParam Long tenantId) {

        CustomerMasterDTO saved = customerService.createCustomer(customerDto, tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(),
                        "Customer Master Created Successfully", saved));
    }

    // Get All Customers by Tenant
    @GetMapping
    public ResponseEntity<SuccessResponse<List<CustomerMasterDTO>>> getAllCustomers(
            @RequestParam Long tenantId) {

        List<CustomerMasterDTO> customers = customerService.getAllCustomersByTenant(tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(),
                        "Customer Master fetched Successfully", customers));
    }

   
}
