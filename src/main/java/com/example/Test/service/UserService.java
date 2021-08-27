package com.example.Test.service;

import com.example.Test.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);
    User getById(Long id);
    List<User> getAll();
    User findByUsername(String currentPrincipalName);
}
