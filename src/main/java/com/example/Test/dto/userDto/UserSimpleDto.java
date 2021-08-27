package com.example.Test.dto.userDto;

import com.example.Test.enums.Role;
import lombok.Data;

@Data
public class UserSimpleDto {
    private Long id;
    private String username;
    private Role role;
}
