package com.example.models;

public class Booking {
    private String date;
    private String eventType;
    private String username;
    private String photographer;

    public Booking(String date, String eventType, String username, String photographer) {
        this.date = date;
        this.eventType = eventType;
        this.username = username;
        this.photographer = photographer;
    }

    public String getDate() {
        return date;
    }

    public String getEventType() {
        return eventType;
    }

    public String getUsername() {
        return username;
    }

    public String getPhotographer() {
        return photographer;
    }

    public String toFileString() {
        return date + "," + eventType + "," + username + "," + photographer;
    }
}