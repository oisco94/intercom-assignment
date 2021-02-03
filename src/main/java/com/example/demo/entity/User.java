package com.example.demo.entity;

public class User implements Comparable {

    private int user_id;
    private Double latitude;
    private Double longitude;
    private String name;

    public User(int userId, double latitude, double longitude, String name) {
        this.user_id = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object compareTo) {
        // user_id ascending
        int userId = ((User) compareTo).getUser_id();

        return this.user_id - userId;
    }
}
