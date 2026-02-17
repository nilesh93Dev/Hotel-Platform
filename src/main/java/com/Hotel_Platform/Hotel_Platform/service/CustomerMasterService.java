package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.CustomException;
import com.Hotel_Platform.Hotel_Platform.dto.CustomerMasterDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.entity.CustomerMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.repository.CustomerMasterRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;

@Service
public class CustomerMasterService {

    @Autowired
    private CustomerMasterRepository customerRepo;

    @Autowired
    private TenantRepository tenantRepo;

    // Mapping Entity → DTO
    private CustomerMasterDTO mapToDTO(CustomerMaster customer) {
        CustomerMasterDTO dto = new CustomerMasterDTO();
        dto.setId(customer.getId());
        dto.setMobileNo(customer.getMobileNo());
        dto.setCustName(customer.getCustName());
        dto.setAddress1(customer.getAddress1());
        dto.setAddress2(customer.getAddress2());
        dto.setCompanyName(customer.getCompanyName());
        dto.setGstNo(customer.getGstNo());
        dto.setEmail(customer.getEmail());
        dto.setReferenceName(customer.getReferenceName());
        dto.setReferenceMobileNo(customer.getReferenceMobileNo());
        dto.setRemarks(customer.getRemarks());

        if (customer.getTenant() != null) {
            TenantSummaryDTO tenantDto = new TenantSummaryDTO();
            tenantDto.setId(customer.getTenant().getId());
            tenantDto.setName(customer.getTenant().getName());
            dto.setTenant(tenantDto);
        }
        return dto;
    }

    // Create Customer
    public CustomerMasterDTO createCustomer(CustomerMasterDTO dto, Long tenantId) {
        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        // Duplicate check by mobile number
        Optional<CustomerMaster> existing =
                customerRepo.findByMobileNoAndTenant_Id(dto.getMobileNo(), tenantId);

        if (existing.isPresent()) {
            throw new CustomException("Customer Already Exists with this Mobile No for Tenant",
                    HttpStatus.BAD_REQUEST);
        }

        CustomerMaster entity = new CustomerMaster();
        entity.setMobileNo(dto.getMobileNo());
        entity.setCustName(dto.getCustName());
        entity.setAddress1(dto.getAddress1());
        entity.setAddress2(dto.getAddress2());
        entity.setCompanyName(dto.getCompanyName());
        entity.setGstNo(dto.getGstNo());
        entity.setEmail(dto.getEmail());
        entity.setReferenceName(dto.getReferenceName());
        entity.setReferenceMobileNo(dto.getReferenceMobileNo());
        entity.setRemarks(dto.getRemarks());
        entity.setTenant(tenant);

        CustomerMaster saved = customerRepo.save(entity);
        return mapToDTO(saved);
    }

    // Get All Customers by Tenant
    public List<CustomerMasterDTO> getAllCustomersByTenant(Long tenantId) {
        tenantRepo.findById(tenantId)
                .orElseThrow(() -> new CustomException("Tenant Not Found", HttpStatus.NOT_FOUND));

        List<CustomerMaster> customers = customerRepo.findByTenant_Id(tenantId);
        return customers.stream()
                .map(this::mapToDTO)
                .toList();
    }

   
   
}


