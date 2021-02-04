package com.example.demo.service;

import com.example.demo.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    final static double DUBLIN_OFFICE_LONGITUDE = 53.339428;
    final static double DUBLIN_OFFICE_LATITUDE = -6.257664;

    final static double KM_RANGE_WITHIN_OFFICE = 100;
    final static double EARTH_RADIUS_KM = 6371;

    /**
     *
     * @return List<User>
     */
    public List<User> getInvitedUsers () throws IOException {
        ArrayList<User> proposedUsers = getUsersFromFile();
        ArrayList<User> invitedUsers = new ArrayList<>();

        // create a new list of users which are within range of the dublin office
        proposedUsers.forEach(user -> {
            if (isUserWithinOfficeRange(user)) {
                invitedUsers.add(user);
            }
        });

        sortUsers(invitedUsers);

        return invitedUsers;
    }

    public void sortUsers(List<User> invitedUsers) {
        Collections.sort(invitedUsers);
    }

    /**
     * returns true/false if the user is within the range of the dublin office
     *
     * @param user
     * @return
     */
    public boolean isUserWithinOfficeRange(User user) {
        if (user.getLatitude() == null || user.getLongitude() == null) {
            return false;
        }

        return getGreatCircleDistanceKM(user.getLongitude(), user.getLatitude(), DUBLIN_OFFICE_LATITUDE, DUBLIN_OFFICE_LONGITUDE) <= KM_RANGE_WITHIN_OFFICE;
    }

    /**
     * calculates the distance between 2 points using the Haversine formula.
     *
     * @param pointALat
     * @param pointALong
     * @param pointBLat
     * @param pointBLong
     * @return (double) distance between the 2 points in KM.
     */
    public double getGreatCircleDistanceKM(double pointALat, double pointALong, double pointBLat, double pointBLong) {
        //convert to radians
        pointALat = Math.toRadians(pointALat);
        pointALong = Math.toRadians(pointALong);
        pointBLat = Math.toRadians(pointBLat);
        pointBLong = Math.toRadians(pointBLong);

        //apply Haversine formula -> https://www.igismap.com/haversine-formula-calculate-geographic-distance-earth/
        double latDifference = pointALat - pointBLat;
        double longDifference = pointALong - pointBLong;

        double a = Math.pow(Math.sin(latDifference / 2), 2) +
                Math.pow(Math.sin(longDifference / 2), 2) *
                        Math.cos(pointALat) *
                        Math.cos(pointBLat);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public ArrayList<User> getUsersFromFile() throws IOException {
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
}
