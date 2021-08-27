package com.example.Test.dtoService.imp;

import com.example.Test.dto.userDto.CreateUserDto;
import com.example.Test.dto.userDto.UpdateUserDto;
import com.example.Test.dto.userDto.UserDto;
import com.example.Test.dtoService.UserDtoService;
import com.example.Test.entity.User;
import com.example.Test.enums.Role;
import com.example.Test.exception.UserSaveException;
import com.example.Test.mapper.UserMapper;
import com.example.Test.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoServiceImp implements UserDtoService {

    private final UserMapper userMapper;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDtoServiceImp(UserMapper userMapper, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto create(CreateUserDto createUserDto) throws UserSaveException {
        User user = new User();

        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(createUserDto.getPassword()));
        user.setUsername((getGenerateUsername(createUserDto.getFirstName()) + getGenerateUsername(createUserDto.getLastName()) + getYearFormat()).toLowerCase());
        user.setRole(checkRole(createUserDto.getRole()));

        User savedUser = userService.save(user);

        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toUserDto(userService.getById(id));
    }

    @Override
    public List<UserDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(UpdateUserDto updateUserDto) throws UserSaveException {
        User user = userService.getById(updateUserDto.getId());

        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());
        user.setRole(checkRole(updateUserDto.getRole()));

        return userMapper.toUserDto(userService.save(user));
    }

    @Override
    public void delete(Long id) {
        User user = userService.getById(id);
        user.setDeleted(true);
        userService.save(user);
    }

    private String getGenerateUsername(String username) {
        if (username.length() > 3) {
            return username.substring(0, 3);
        } else
            return username;
    }

    private String getYearFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.format(new Date());
    }

    private Role checkRole(Role role) throws UserSaveException {
        if (Role.ADMIN != role) {
            return role;
        }
        throw new UserSaveException("Unable to use ADMIN role.");
    }
}