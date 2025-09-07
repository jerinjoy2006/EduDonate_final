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

    public void registerUser(User user)
    { user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user); }g
}
