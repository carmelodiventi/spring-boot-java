package com.service;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> signIn(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public String signUp(User user) {

        try {
            Optional<User> result = userRepository.findByEmail(user.getEmail());
            if (result.isPresent()) throw new Exception("User already exists");
            user.setRole("customer");
            userRepository.save(user);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }


    }
}
