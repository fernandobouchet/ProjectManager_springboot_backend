package com.fernandobouchet.projectmanager.service;

import com.fernandobouchet.projectmanager.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User registerUser(String username, String email, String password);
    UserDetails loginUser(String email, String password);
}
