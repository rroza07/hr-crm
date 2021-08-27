package com.example.Test.dtoService;

import com.example.Test.dto.userDto.CreateUserDto;
import com.example.Test.dto.userDto.UpdateUserDto;
import com.example.Test.dto.userDto.UserDto;
import com.example.Test.exception.UserSaveException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDtoService {
    UserDto create(CreateUserDto createUserDto) throws UserSaveException;
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto update(UpdateUserDto updateUserDto) throws UserSaveException;
    void delete(Long id);
}