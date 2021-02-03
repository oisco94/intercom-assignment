package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public List<User> getInvitedUsers(@RequestBody ArrayList<User> usersInput) {
       return userService.getInvitedUsers(usersInput);
    }
}