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

    /**
     * Save a new user into the database.
     * Ensures password is encoded and role is set.
     */
    public User saveUser(User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // If no role is provided, assign default role USER
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");  // ✅ default role
        }

        return userRepository.save(user);
    }

    /**
     * Find user by username.
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
