package com.example.models;

public class PhotographerRating {
    private String name;
    private double rating;

    public PhotographerRating(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}