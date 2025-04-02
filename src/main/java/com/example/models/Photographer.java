package com.example.models;

public class Photographer {
    private int id;
    private String name;
    private String category;
    private double rating;

    public Photographer(int id, String name, String category, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getRating() { return rating; }

    public void setRating(double rating) {
        this.rating = rating;
    }
}