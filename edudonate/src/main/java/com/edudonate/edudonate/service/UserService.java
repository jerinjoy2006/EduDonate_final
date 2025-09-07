package com.edudonate.edudonate.service;

import com.edudonate.edudonate.model.User;
import com.edudonate.edudonate.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    public boolean registerUser(User user) {
        // Check if username already exists using Optional
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false; // username already exists
        }

        // Encode password and save
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true; // registration successful
    }

    // Login method (authenticate user) using Optional filter
    public User login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }
}
