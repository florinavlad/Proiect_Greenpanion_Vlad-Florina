package com.florina.greenpanion.service;

import com.florina.greenpanion.dto.request.UserRequest;
import com.florina.greenpanion.model.User;

import java.util.List;
import java.util.Optional;

public interface AuthService {
    public Optional<User> AddUser(UserRequest user);
    boolean checkEmailExists(String email);
    List<User> getRanking();
}
