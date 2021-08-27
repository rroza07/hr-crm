package com.example.Test.dto.userDto;

import com.example.Test.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Role role;
    private boolean isDeleted;
}