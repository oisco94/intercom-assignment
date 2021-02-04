package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
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
            ArrayList<User> users = getUsersFromFile();
            printInvitedUsers(users);
        }catch (IOException e){
            LOGGER.error(e.toString());
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    private ArrayList<User> getUsersFromFile() throws IOException {
        LOGGER.info("reading file and deserializing contents");

        Resource resource = new ClassPathResource("users.json");
        InputStream inputStream = resource.getInputStream();
        //to string
        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
        String data = new String(bdata, StandardCharsets.UTF_8);
        //to class
        Gson gson = new GsonBuilder().create();
        Type founderListType = new TypeToken<ArrayList<User>>() {
        }.getType();

        ArrayList<User> users = gson.fromJson(data, founderListType);

        LOGGER.info("users successfully read from file");

        return users;
    }

    /**
     * @param users
     */
    private void printInvitedUsers(ArrayList<User> users) {
        System.out.println("invited users are as follows:");
        System.out.println("-----------------------------");

        userService.getInvitedUsers(users).forEach(user -> {
            System.out.println("user_id: " + user.getUser_id());
            System.out.println("user_name: " + user.getName());
            System.out.println("-----------------------------");
        });

        System.out.println("---------------------------");
    }
}


