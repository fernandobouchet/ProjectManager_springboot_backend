package com.fernandobouchet.projectmanager.controller;

import com.fernandobouchet.projectmanager.dto.UserLoginRequest;
import com.fernandobouchet.projectmanager.dto.UserRegisterRequest;
import com.fernandobouchet.projectmanager.dto.UserResponse;
import com.fernandobouchet.projectmanager.model.User;
import com.fernandobouchet.projectmanager.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        User user = userService.registerUser(userRegisterRequest.getUsername(), userRegisterRequest.getEmail(), userRegisterRequest.getPassword());

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());

       return response;
    }

    @PostMapping("/login")
    public UserResponse loginUser(@RequestBody UserLoginRequest userLoginRequest) {
      User user =  userService.loginUser(userLoginRequest.getEmail(), userLoginRequest.getPassword());

      UserResponse response = new UserResponse();
      response.setId(user.getId());
      response.setUsername(user.getUsername());
      response.setEmail(user.getEmail());
      response.setCreatedAt(user.getCreatedAt());

        return response;
    }
}
