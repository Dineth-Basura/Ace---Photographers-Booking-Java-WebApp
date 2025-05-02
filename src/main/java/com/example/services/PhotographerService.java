package com.example.services;

import com.example.models.Photographer;
import com.example.models.PhotographerRating;
import com.example.utils.PhotographerSorter;

import java.io.*;
import java.util.*;

public class PhotographerService {
    private final String photographerFilePath;
    private final String ratingsFilePath;

    public PhotographerService(String photographerFilePath, String ratingsFilePath) {
        this.photographerFilePath = photographerFilePath;
        this.ratingsFilePath = ratingsFilePath;

        // Ensure ratings file exists
        File ratingsFile = new File(ratingsFilePath);
        if (!ratingsFile.exists()) {
            try {
                ratingsFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating ratings file: " + e.getMessage());
            }
        }
    }

    // Update Photographer Rating
    public boolean updatePhotographerRating(String name, double newRating) throws IOException {
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        // Read existing ratings
        File ratingsFile = new File(ratingsFilePath);
        if (ratingsFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ratingsFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2 && parts[0].trim().equalsIgnoreCase(name)) {
                        line = name + "," + newRating;
                        found = true;
                    }
                    if (!line.trim().isEmpty()) {
                        updatedLines.add(line);
                    }
                }
            }
        }

        // If not found, add new rating
        if (!found) {
            updatedLines.add(name + "," + newRating);
        }

        // Write back to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ratingsFile))) {
            for (String line : updatedLines) {
                bw.write(line);
                bw.newLine();
            }
        }

        return true;
    }

    // Get all ratings
    public Map<String, Double> getAllRatings() throws IOException {
        Map<String, Double> ratings = new HashMap<>();
        File ratingsFile = new File(ratingsFilePath);

        if (ratingsFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ratingsFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        ratings.put(parts[0].trim(), Double.parseDouble(parts[1].trim()));
                    }
                }
            }
        }
        return ratings;
    }

    // Get photographer names from photographers.txt
    public List<String> getPhotographerNames() throws IOException {
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(photographerFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Photographer:")) {
                    String name = line.split(":")[1].split(",")[0].trim();
                    names.add(name);
                }
            }
        }
        return names;
    }

    // Get sorted photographers with ratings
    public List<PhotographerRating> getSortedPhotographerRatings() throws IOException {
        Map<String, Double> ratings = getAllRatings();
        List<PhotographerRating> photographerRatings = new ArrayList<>();

        // Get all photographer names
        List<String> names = getPhotographerNames();

        // Create PhotographerRating objects
        for (String name : names) {
            double rating = ratings.getOrDefault(name, 0.0);
            photographerRatings.add(new PhotographerRating(name, rating));
        }

        // Bubble sort by rating (descending)
        int n = photographerRatings.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (photographerRatings.get(j).getRating() < photographerRatings.get(j + 1).getRating()) {
                    PhotographerRating temp = photographerRatings.get(j);
                    photographerRatings.set(j, photographerRatings.get(j + 1));
                    photographerRatings.set(j + 1, temp);
                }
            }
        }

        return photographerRatings;
    }
}