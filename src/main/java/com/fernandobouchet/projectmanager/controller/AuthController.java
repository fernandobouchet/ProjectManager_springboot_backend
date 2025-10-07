package com.fernandobouchet.projectmanager.controller;

import com.fernandobouchet.projectmanager.dto.UserLoginRequest;
import com.fernandobouchet.projectmanager.dto.UserRegisterRequest;
import com.fernandobouchet.projectmanager.dto.UserResponse;
import com.fernandobouchet.projectmanager.model.User;
import com.fernandobouchet.projectmanager.security.CustomUserDetails;
import com.fernandobouchet.projectmanager.security.JwtUtil;
import com.fernandobouchet.projectmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());

       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest request) {
            UserDetails user = userService.loginUser(request.getEmail(),request.getPassword());

            String token = jwtUtil.generateToken(user.getUsername());

            return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());

        return ResponseEntity.ok(response);
    }
}
