package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.AccountMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.AccountMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.enums.AccountType;
import com.Hotel_Platform.Hotel_Platform.repository.AccountMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class AccountMasterService {

    @Autowired
    private AccountMasterRepository accountMasterRepo;

    @Autowired
    private TenantRepository tenantRepository;

    private AccountMasterDTO mapToDTO(AccountMaster account) {
        AccountMasterDTO dto = new AccountMasterDTO();
        dto.setId(account.getId());
        dto.setPartyName(account.getPartyName());
        dto.setAddress1(account.getAddress1());
        dto.setAddress2(account.getAddress2());
        dto.setPinCode(account.getPinCode());
        dto.setGstNo(account.getGstNo());
        dto.setMobileNo(account.getMobileNo());
        dto.setEmail(account.getEmail());
        dto.setAccountType(account.getAccountType().name());

        if (account.getTenant() != null) {
            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(account.getTenant().getId());
            tenantDto.setName(account.getTenant().getName());
            dto.setTenant(tenantDto);
        }
       // dto.setCreditAllow(account.getCreditAllow());
        return dto;
    }

    public AccountMasterDTO createAccount(AccountMasterDTO dto, Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        // Duplicate check
        Optional<AccountMaster> existing =
                accountMasterRepo.findByPartyNameIgnoreCaseAndTenant_Id(dto.getPartyName(), tenantId);

        if (existing.isPresent()) {
            throw new CustomException("Account Master Already Exists with this Tenant Id",
                    HttpStatus.BAD_REQUEST);
        }

        AccountMaster entity = new AccountMaster();
        entity.setPartyName(dto.getPartyName());
        entity.setAddress1(dto.getAddress1());
        entity.setAddress2(dto.getAddress2());
        entity.setPinCode(dto.getPinCode());
        entity.setGstNo(dto.getGstNo());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmail(dto.getEmail());
        entity.setAccountType(AccountType.valueOf(dto.getAccountType().toUpperCase()));
       // entity.setCreditAllow(dto.getCreditAllow());
        entity.setTenant(tenant);

        AccountMaster saved = accountMasterRepo.save(entity);
        return mapToDTO(saved);
    }

    public List<AccountMasterDTO> getAllAccountsByTenant(Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        List<AccountMaster> accounts = accountMasterRepo.findByTenant_Id(tenantId);

        return accounts.stream()
                .map(this::mapToDTO)
                .toList();
    }
}
