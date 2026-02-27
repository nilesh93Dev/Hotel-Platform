package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.WaiterMaster;

@Repository
public interface WaiterMasterRepository extends JpaRepository<WaiterMaster, Long> {
	
	List<WaiterMaster> findByTenantId(Long tenantId);

}
