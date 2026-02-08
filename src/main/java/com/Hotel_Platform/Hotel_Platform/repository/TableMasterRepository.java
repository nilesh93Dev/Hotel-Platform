package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.TableMaster;

@Repository
public interface TableMasterRepository extends JpaRepository<TableMaster, Long> {

	Optional<TableMaster> findByTableNameIgnoreCaseAndTenant_Id(String tableName, Long tenantId);

	List<TableMaster> findByTenant_Id(Long tenantId);

}
