package com.metusala.wisercatpets.com.metusala.wisercatpets.controllers;

import com.metusala.wisercatpets.com.metusala.wisercatpets.repositories.UserRepository;
import com.metusala.wisercatpets.com.metusala.wisercatpets.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/api/user")
    public User getUser(Principal principal) {
        // return email only
        return new User(principal.getName());
    }

}
