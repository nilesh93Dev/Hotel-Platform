package com.Hotel_Platform.Hotel_Platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel_Platform.Hotel_Platform.dto.RoleDTO;
import com.Hotel_Platform.Hotel_Platform.dto.TenantSummaryDTO;
import com.Hotel_Platform.Hotel_Platform.dto.WaiterMasterDTO;
import com.Hotel_Platform.Hotel_Platform.entity.Role;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;
import com.Hotel_Platform.Hotel_Platform.entity.WaiterMaster;
import com.Hotel_Platform.Hotel_Platform.repository.RoleRepository;
import com.Hotel_Platform.Hotel_Platform.repository.TenantRepository;
import com.Hotel_Platform.Hotel_Platform.repository.WaiterMasterRepository;

@Service
public class WaiterMasterService {


	    @Autowired
	    private WaiterMasterRepository waiterRepository;

	    @Autowired
	    private RoleRepository roleRepository;
	    
	    @Autowired
	    private TenantRepository tenantRepository;

//	    public WaiterMasterDTO createWaiter(String waiterName, String password, Tenant tenant) {
//	        Role waiterRole = roleRepository.findByName("WAITER")
//	                .orElseThrow(() -> new RuntimeException("Role WAITER not found"));
//
//	        WaiterMaster waiter = new WaiterMaster();
//	        waiter.setWaiterName(waiterName);
//	        waiter.setPassword(password); // ideally encode with BCrypt
//	        waiter.setTenant(tenant);
//	        waiter.setRole(waiterRole);
//
//	        WaiterMaster saved = waiterRepository.save(waiter);
//
//	        return new WaiterMasterDTO(
//	                saved.getId(),
//	                saved.getWaiterName(),
//	                null,
//	                new RoleDTO(saved.getRole().getId(), saved.getRole().getName()),
//	                new TenantSummaryDTO(saved.getTenant().getId(), saved.getTenant().getName())
//	        );
	    
	    
	    public WaiterMasterDTO createWaiter(String waiterName, String password, Long tenantId) {
	        // ✅ Tenant को DB से fetch करो
	        Tenant tenant = tenantRepository.findById(tenantId)
	                .orElseThrow(() -> new RuntimeException("Tenant not found"));

	        // ✅ Role को DB से fetch करो
	        Role waiterRole = roleRepository.findByName("WAITER")
	                .orElseThrow(() -> new RuntimeException("Role WAITER not found"));

	        // ✅ Waiter entity बनाओ
	        WaiterMaster waiter = new WaiterMaster();
	        waiter.setWaiterName(waiterName);
	        waiter.setPassword(password); // BCrypt encode करना चाहिए
	        waiter.setTenant(tenant);
	        waiter.setRole(waiterRole);

	        WaiterMaster saved = waiterRepository.save(waiter);

	        // ✅ DTO return करो
	        return new WaiterMasterDTO(
	                saved.getId(),
	                saved.getWaiterName(),
	                //null, // email optional है
	                new RoleDTO(saved.getRole().getId(), saved.getRole().getName()),
	                new TenantSummaryDTO(saved.getTenant().getId(), saved.getTenant().getName())
	        );
	    }
	
	    

	    public List<WaiterMasterDTO> getAllWaiters(Long tenantId) {
	        return waiterRepository.findByTenantId(tenantId).stream()
	                .map(w -> new WaiterMasterDTO(
	                        w.getId(),
	                        w.getWaiterName(),
	                        //null,
	                        new RoleDTO(w.getRole().getId(), w.getRole().getName()),
	                        new TenantSummaryDTO(w.getTenant().getId(), w.getTenant().getName())
	                ))
	                .toList();
	    }
	}



