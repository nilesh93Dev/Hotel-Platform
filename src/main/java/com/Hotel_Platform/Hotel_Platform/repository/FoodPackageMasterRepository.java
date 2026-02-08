package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.FoodPackageMaster;

@Repository
public interface FoodPackageMasterRepository extends JpaRepository<FoodPackageMaster, Long> {

	Optional<FoodPackageMaster> findByFoodNameIgnoreCaseAndTenant_Id(String foodName, Long tenantId);

	List<FoodPackageMaster> findByTenant_Id(Long tenantId);

}
