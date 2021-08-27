package com.example.Test.controller;

import com.example.Test.dto.userDto.CreateUserDto;
import com.example.Test.dto.userDto.UpdateUserDto;
import com.example.Test.dto.userDto.UserDto;
import com.example.Test.dtoService.UserDtoService;
import com.example.Test.exception.UserSaveException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserDtoService userDtoService;

    public UserController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @PostMapping
    public UserDto create(@RequestBody CreateUserDto createUserDto) throws UserSaveException {
        return userDtoService.create(createUserDto);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        return userDtoService.getById(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userDtoService.getAll();
    }

    @PutMapping
    public UserDto update(@RequestBody UpdateUserDto updateUserDto) throws UserSaveException {
        return userDtoService.update(updateUserDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userDtoService.delete(id);
    }
}