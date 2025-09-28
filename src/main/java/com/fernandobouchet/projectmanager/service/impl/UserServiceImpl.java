package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.model.User;
import com.fernandobouchet.projectmanager.repository.UserRepository;
import com.fernandobouchet.projectmanager.security.CustomUserDetails;
import com.fernandobouchet.projectmanager.security.CustomUserDetailsService;
import com.fernandobouchet.projectmanager.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public User registerUser(String username, String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            throw new RuntimeException("User already registered");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public UserDetails loginUser(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        }
        catch (AuthenticationException e) {
            throw new RuntimeException("Invalid login credentials");
        }

        return customUserDetailsService.loadUserByUsername(email);
    }

    @Override
    public User findById(Long userId) {
        return  userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
