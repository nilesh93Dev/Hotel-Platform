package com.Hotel_Platform.Hotel_Platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Platform.Hotel_Platform.entity.RoomMaster;

@Repository
public interface RoomMasterRepository extends JpaRepository<RoomMaster, Long> {

	Optional<RoomMaster> findByRoomNameIgnoreCaseAndTenant_Id(String roomName, Long tenantId);

	List<RoomMaster> findByTenant_Id(Long tenantId);

}
