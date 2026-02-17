package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.CustomerMaster;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMaster, Long> {

	Optional<CustomerMaster> findByMobileNoAndTenant_Id(String mobileNo, Long tenantId);

	List<CustomerMaster> findByTenant_Id(Long tenantId);

}
