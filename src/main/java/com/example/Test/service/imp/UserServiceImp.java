package com.example.Test.service.imp;

import com.example.Test.entity.User;
import com.example.Test.repository.UserRepository;
import com.example.Test.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findByUsername(String currentPrincipalName) {
        return userRepository.findByUsername(currentPrincipalName);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllByIsDeletedFalse();
    }
}