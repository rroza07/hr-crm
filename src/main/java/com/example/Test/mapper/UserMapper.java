package com.example.Test.mapper;

import com.example.Test.dto.userDto.UserDto;
import com.example.Test.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toUserDto(User user);
}