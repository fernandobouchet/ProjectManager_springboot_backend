package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.exception.UserAlreadyExistsException;
import com.fernandobouchet.projectmanager.model.User;
import com.fernandobouchet.projectmanager.repository.UserRepository;
import com.fernandobouchet.projectmanager.security.CustomUserDetailsService;
import com.fernandobouchet.projectmanager.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String username, String email, String password) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        });


        String encodedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loginUser(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid login credentials");
        }

        return customUserDetailsService.loadUserByUsername(email);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
