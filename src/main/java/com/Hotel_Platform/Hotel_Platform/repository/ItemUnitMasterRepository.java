package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.ItemUnitMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;


@Repository
public interface ItemUnitMasterRepository extends JpaRepository<ItemUnitMaster, Long> {

	
	
	Optional<ItemUnitMaster> findByUnitNameIgnoreCaseAndTenant_Id(String unitName, Long tenantId);

    List<ItemUnitMaster> findByTenant_Id(Long tenantId);

	Optional<ItemUnitMaster> findByIdAndTenant_Id(Long id, Long tenantId);
}
