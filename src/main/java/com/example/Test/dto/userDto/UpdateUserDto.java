package com.example.Test.dto.userDto;

import com.example.Test.enums.Role;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class UpdateUserDto {
    @NotNull
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private Role role;
}