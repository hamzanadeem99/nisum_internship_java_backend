package com.warehouse.dto;

import com.warehouse.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestDTO {
    private String username;
    private String password;
    private Role role;
}
