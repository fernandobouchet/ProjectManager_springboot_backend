package com.fernandobouchet.projectmanager.service;

import com.fernandobouchet.projectmanager.model.User;

public interface UserService {
    User registerUser(String username, String email, String password);
    User loginUser(String email, String password);
}
