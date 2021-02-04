package com.example.demo.controller;

import java.io.IOException;
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

    @GetMapping("/users")
    public List<User> getInvitedUsers () throws IOException {
       return userService.getInvitedUsers();
    }
}