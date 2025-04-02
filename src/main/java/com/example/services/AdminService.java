package com.example.services;

import com.example.models.Photographer;
import com.example.utils.FileUtil;
import com.example.utils.PhotographerSorter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private String userFilePath;
    private String photographerFilePath;

    public AdminService(String userFilePath, String photographerFilePath) {
        this.userFilePath = userFilePath;
        this.photographerFilePath = photographerFilePath;
    }

    // Get all users from users.txt
    public List<String> getAllUsers() throws IOException {
        return FileUtil.readAllLines(userFilePath);
    }

    // Get sorted photographer list by rating
    public List<Photographer> getSortedPhotographers() throws IOException {
        List<String> lines = FileUtil.readAllLines(photographerFilePath);
        List<Photographer> photographers = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",", -1);
            if (parts.length >= 4) {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String category = parts[2].trim();
                double rating = Double.parseDouble(parts[3].trim());
                photographers.add(new Photographer(id, name, category, rating));
            }
        }
        PhotographerSorter.sortByRating(photographers);
        return photographers;
    }

    // Delete a user from users.txt by username
    public void deleteUser(String usernameToDelete) throws IOException {
        List<String> users = FileUtil.readAllLines(userFilePath);
        List<String> updatedUsers = new ArrayList<>();
        for (String line : users) {
            if (!line.startsWith(usernameToDelete + ",")) {
                updatedUsers.add(line);
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(userFilePath))) {
            for (String updatedLine : updatedUsers) {
                pw.println(updatedLine);
            }
        }
    }
}