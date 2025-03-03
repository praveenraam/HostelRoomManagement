package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.User;
import com.praveenraam.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }
}
