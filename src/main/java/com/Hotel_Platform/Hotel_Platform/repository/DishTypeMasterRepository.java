package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.DishTypeMaster;

@Repository
public interface DishTypeMasterRepository extends JpaRepository<DishTypeMaster, Long> {
	
	Optional<DishTypeMaster> findByDishTypeNameIgnoreCaseAndTenant_Id(String dishTypeName, Long tenantId);

	List<DishTypeMaster> findByTenant_Id(Long tenantId);

}
