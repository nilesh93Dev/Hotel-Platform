package com.Hotel_Platform.Hotel_Platform.dto;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomMasterDTO {

    private Long id;
    private String roomName;

    private RoomTypeDTO roomType;
    private FloorMasterDTO floor;

    private double rentPerDay;
    private double extraBedCharge;

    private String currentStatus;

    private TenantSummaryDTO tenant;
}
