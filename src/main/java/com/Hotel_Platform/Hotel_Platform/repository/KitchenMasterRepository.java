package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.KitchenMaster;

@Repository
public interface KitchenMasterRepository extends JpaRepository<KitchenMaster, Long> {

	Optional<KitchenMaster> findByKitchenNameIgnoreCaseAndTenant_Id(String kitchenName, Long tenantId);

	List<KitchenMaster> findByTenant_Id(Long tenantId);

}
