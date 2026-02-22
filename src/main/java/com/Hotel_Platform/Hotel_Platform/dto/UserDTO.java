package com.Hotel_Platform.Hotel_Platform.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private RoleDTO role;
    private TenantSummaryDTO tenant;
    private List<UserPermissionDTO> permissions;
}

