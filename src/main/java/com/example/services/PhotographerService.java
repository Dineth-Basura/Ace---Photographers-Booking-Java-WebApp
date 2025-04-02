package com.example.services;

import com.example.models.Photographer;
import com.example.utils.FileUtil;
import com.example.utils.PhotographerSorter;

import java.io.*;
import java.util.*;

public class PhotographerService {

    private String photographerFilePath;

    public PhotographerService(String photographerFilePath) {
        this.photographerFilePath = photographerFilePath;
    }

    // Register a photographer and auto-generate ID
    public void registerPhotographer(String name, String category, double rating) throws IOException {
        int newId = generatePhotographerId();
        String line = newId + "," + name + "," + category + "," + rating;
        try (FileWriter fw = new FileWriter(photographerFilePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(line);
            bw.newLine();
        }
    }

    // Generate next photographer ID
    private int generatePhotographerId() throws IOException {
        List<String> lines = FileUtil.readAllLines(photographerFilePath);
        int maxId = 100;
        for (String line : lines) {
            String[] parts = line.split(",", -1);
            if (parts.length >= 1) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    if (id > maxId) {
                        maxId = id;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return maxId + 1;
    }

    // Get all photographers from file
    public List<Photographer> getAllPhotographers() throws IOException {
        List<String> lines = FileUtil.readAllLines(photographerFilePath);
        List<Photographer> photographers = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",", -1);
            if (parts.length >= 4) {
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String category = parts[2].trim();
                    double rating = Double.parseDouble(parts[3].trim());
                    photographers.add(new Photographer(id, name, category, rating));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return photographers;
    }

    // Get sorted photographers by rating
    public List<Photographer> getSortedPhotographers() throws IOException {
        List<Photographer> photographers = getAllPhotographers();
        return PhotographerSorter.sortByRating(photographers);
    }
}