package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.RoomTypeMaster;

@Repository
public interface RoomTypeMasterRepository extends JpaRepository<RoomTypeMaster, Long> {

	Optional<RoomTypeMaster> findByRoomTypeIgnoreCaseAndTenant_Id(String roomType, Long tenantId);

	List<RoomTypeMaster> findByTenant_Id(Long tenantId);

}
