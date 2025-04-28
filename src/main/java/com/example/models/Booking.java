package com.example.models;

public class Booking {
    private String date;
    private String eventType;
    private String username;
    private String photographer;
    private String packageName;
    private String status;

    // Full constructor including status (used when reading from file)
    public Booking(String date, String eventType, String username, String photographer, String packageName, String status) {
        this.date = date;
        this.eventType = eventType;
        this.username = username;
        this.photographer = photographer;
        this.packageName = packageName;
        this.status = status;
    }

    // Simplified constructor (defaults to Pending)
    public Booking(String date, String eventType, String username, String photographer, String packageName) {
        this(date, eventType, username, photographer, packageName, "Pending");
    }

    // Getters
    public String getDate() { return date; }
    public String getEventType() { return eventType; }
    public String getUsername() { return username; }
    public String getPhotographer() { return photographer; }
    public String getPackageName() { return packageName; }
    public String getStatus() { return status; }

    // Setter for updating booking status (Approved / Rejected)
    public void setStatus(String status) {
        this.status = status;
    }

    // Converts to file string format
    public String toFileString() {
        return date + "," + eventType + "," + username + "," + photographer + "," + packageName + "," + status;
    }
}