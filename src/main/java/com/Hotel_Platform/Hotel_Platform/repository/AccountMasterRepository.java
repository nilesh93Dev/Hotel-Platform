package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.AccountMaster;

@Repository
public interface AccountMasterRepository extends JpaRepository<AccountMaster, Long> {

	Optional<AccountMaster> findByPartyNameIgnoreCaseAndTenant_Id(String partyName, Long tenantId);

	List<AccountMaster> findByTenant_Id(Long tenantId);

}
