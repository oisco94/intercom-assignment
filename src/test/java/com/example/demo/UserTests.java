package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @Test
    public void userWithinOfficeRangeIsIdentified(){
        // when user from Wicklow is created
        User user = new User(1, 52.986375, -6.043701, "example user from wicklow");

        // then they are within range of the office
        boolean isUserWithinRange = userService.isUserWithinOfficeRange(user);

        assertTrue(isUserWithinRange);
    }

    @Test
    public void userOutsideOfficeRangeIsIdentified(){
        // when user from Kerry is created
        User user = new User(1, 51.92893, -10.27699, "example user");

        // then they are not within range of the office
        boolean isUserWithinRange = userService.isUserWithinOfficeRange(user);

        assertFalse(isUserWithinRange);
    }

    @Test
    public void usersAreReadFromFileCorrectly() throws IOException {
        ArrayList<User> users = userService.getUsersFromFile();
        assertNotNull(users);
    }

    @Test
    public void usersAreSortedByUserId(){
        User user = new User(5, 52.986375, -6.043701, "example user from wicklow");
        User user2 = new User(9, 52.986375, -6.043701, "example user from wicklow");
        User user3 = new User(1, 52.986375, -6.043701, "example user from wicklow");

        ArrayList<User> usersList = new ArrayList<>();

        usersList.add(user);
        usersList.add(user2);
        usersList.add(user3);

        userService.sortUsers(usersList);

        assertEquals( usersList.get(0).getUser_id() ,1);
        assertEquals( usersList.get(1).getUser_id() ,5);
    }


    @Test
    public void distanceBetweenTwoPointsIsCalculatedCorrectly(){
        final double DUBLIN_OFFICE_LATITUDE= 53.339428;
        final double DUBLIN_OFFICE_LONGITUDE= -6.257664;
        final double CORK_CITY_LATITUDE = 51.8985;
        final double CORK_CITY_LONGITUDE = -8.4756;

        final int DUBLIN_OFFICE_TO_CORK_DISTANCE_KM = 219;

//      according to the following resource -> https://www.gpsvisualizer.com/calculators
//      the great circular distance between these two points is 219 km (rounded to nearest km)

        int distanceBetweenPoints = (int) userService.getGreatCircleDistanceKM(DUBLIN_OFFICE_LATITUDE, DUBLIN_OFFICE_LONGITUDE, CORK_CITY_LATITUDE, CORK_CITY_LONGITUDE);

        assertEquals( distanceBetweenPoints ,DUBLIN_OFFICE_TO_CORK_DISTANCE_KM);
    }

}
