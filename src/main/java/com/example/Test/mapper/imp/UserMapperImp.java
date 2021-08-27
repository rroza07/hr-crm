package com.example.Test.mapper.imp;

import com.example.Test.dto.userDto.UserDto;
import com.example.Test.entity.User;
import com.example.Test.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImp implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole());

        return userDto;
    }
}