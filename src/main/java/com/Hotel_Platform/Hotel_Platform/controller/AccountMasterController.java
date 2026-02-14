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

import com.Hotel_Platform.Hotel_Platform.dto.AccountMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.SuccessResponse;
import com.Hotel_Platform.Hotel_Platform.service.AccountMasterService;

import static com.Hotel_Platform.Hotel_Platform.config.ApiPath.ACCOUNT_MASTER;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(ACCOUNT_MASTER)
public class AccountMasterController {
	
	
	@Autowired
    private AccountMasterService accountMasterService;

    @PostMapping
    public ResponseEntity<SuccessResponse<AccountMasterDTO>> createAccount(
            @RequestBody AccountMasterDTO accountDto,
            @RequestParam Long tenantId) {

        AccountMasterDTO saved = accountMasterService.createAccount(accountDto, tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(),
                        "Account Master Created Successfully", saved));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<AccountMasterDTO>>> getAllAccounts(
            @RequestParam Long tenantId) {

        List<AccountMasterDTO> accounts = accountMasterService.getAllAccountsByTenant(tenantId);

        return ResponseEntity.ok(
                new SuccessResponse<>(HttpStatus.OK.value(),
                        "Account Master fetched Successfully", accounts));
    }
}



