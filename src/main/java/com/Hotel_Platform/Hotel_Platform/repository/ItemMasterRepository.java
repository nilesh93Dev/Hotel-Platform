package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.ItemMaster;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long> {

	Optional<ItemMaster> findByItemNameIgnoreCaseAndTenant_Id(String itemName, Long tenantId);

	List<ItemMaster> findByTenant_Id(Long tenantId);

}
