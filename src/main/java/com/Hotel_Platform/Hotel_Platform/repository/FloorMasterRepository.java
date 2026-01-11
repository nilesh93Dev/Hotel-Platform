package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.FloorMaster;

@Repository
public interface FloorMasterRepository extends JpaRepository<FloorMaster, Long> {

	Optional<FloorMaster> findByFloorNameIgnoreCaseAndTenant_Id(String floorName, Long tenantId);

	List<FloorMaster> findByTenant_Id(Long tenantId);

}
