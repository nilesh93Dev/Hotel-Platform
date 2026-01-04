package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.ItemUnitMaster;

@Repository
public interface ItemUnitMasterRepository extends JpaRepository<ItemUnitMaster, Long> {
	
	Optional<ItemUnitMaster> findByUnitNameIgnoreCaseAndTenant_Id(String unitName, Long tenantId);

}
