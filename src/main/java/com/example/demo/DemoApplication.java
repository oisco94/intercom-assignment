package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    UserService userService;

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.run(args);
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            ArrayList<User> users = userService.getInvitedUsers();
            printInvitedUsers(users);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    /**
     * @param users
     */
    private void printInvitedUsers(ArrayList<User> users) {
        System.out.println("invited users are as follows:");
        System.out.println("-----------------------------");

        userService.sortUsers(users);

        users.forEach(user -> {
            System.out.println("user_id: " + user.getUser_id());
            System.out.println("user_name: " + user.getName());
            System.out.println("-----------------------------");
        });

        System.out.println("---------------------------");
    }
}


