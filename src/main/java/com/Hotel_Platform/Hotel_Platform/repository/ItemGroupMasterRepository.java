package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.ItemGroupMaster;

@Repository
public interface ItemGroupMasterRepository extends JpaRepository<ItemGroupMaster, Long> {

	Optional<ItemGroupMaster> findByGroupNameIgnoreCaseAndTenantId(String groupName, Long tenantId);

	List<ItemGroupMaster> findByTenantId(Long tenantId);

}
