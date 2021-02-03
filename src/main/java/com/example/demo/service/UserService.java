package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    final static double DUBLIN_OFFICE_LONGITUDE = 53.339428;
    final static double DUBLIN_OFFICE_LATITUDE = -6.257664;

    final static double KM_RANGE_WITHIN_OFFICE = 100;
    final static double EARTH_RADIUS_KM = 6371;

    /**
     * accepts a list of users and returns the subset of users which are in out desired range from our office
     *
     * @param proposedUsers
     * @return List<User>
     */
    public List<User> getInvitedUsers(ArrayList<User> proposedUsers) {
        List<User> invitedUsers = new ArrayList<>();
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
}
