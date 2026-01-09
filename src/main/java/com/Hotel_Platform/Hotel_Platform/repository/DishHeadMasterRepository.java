package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.DishHeadMaster;
import com.Hotel_Platform.Hotel_Platform.entity.Tenant;

@Repository
public interface DishHeadMasterRepository extends JpaRepository<DishHeadMaster, Long> {

	List<DishHeadMaster> findByTenant_Id(Long tenantId);

}
