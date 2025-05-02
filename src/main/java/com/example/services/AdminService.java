package com.example.services;

import com.example.models.Photographer;
import com.example.utils.FileUtil;
import com.example.utils.PhotographerSorter;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class AdminService {
    private String userFilePath;
    private String photographerFilePath;

    public AdminService(String userFilePath, String photographerFilePath) {
        this.userFilePath = userFilePath;
        this.photographerFilePath = photographerFilePath;
    }

    // Existing methods (unchanged)
    public List<String> getAllUsers() throws IOException {
        return FileUtil.readAllLines(userFilePath);
    }

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

    public void deleteUser(String usernameToDelete) throws IOException {
        List<String> users = FileUtil.readAllLines(userFilePath);
        List<String> updatedUsers = new ArrayList<>();
        for (String line : users) {
            if (!line.startsWith(usernameToDelete + ",")) {
                updatedUsers.add(line);
            }
        }
        writeAllLines(userFilePath, updatedUsers);
    }

    // Updated to handle spaces and typos
    public List<Map<String, String>> getAllBookings() throws IOException {
        String bookingsPath = userFilePath.replace("users.txt", "bookings.txt");
        List<Map<String, String>> bookings = new ArrayList<>();
        List<String> lines = FileUtil.readAllLines(bookingsPath);

        for (String line : lines) {
            String[] parts = Arrays.stream(line.split(","))
                    .map(String::trim)
                    .toArray(String[]::new);

            if (parts.length >= 6) {
                Map<String, String> booking = new LinkedHashMap<>();
                booking.put("date", parts[0]);
                booking.put("eventType", parts[1]);
                booking.put("user", parts[2]);
                booking.put("photographer", parts[3]);
                booking.put("packageType", parts[4]);
                booking.put("status", parts[5].replace("m", "")); // Fix typo
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // Updated to handle spaces
    public List<Map<String, String>> getAllPayments() throws IOException {
        String paymentsPath = userFilePath.replace("users.txt", "payments.txt");
        List<Map<String, String>> payments = new ArrayList<>();
        List<String> lines = FileUtil.readAllLines(paymentsPath);

        for (String line : lines) {
            String[] parts = Arrays.stream(line.split(","))
                    .map(String::trim)
                    .toArray(String[]::new);

            if (parts.length >= 8) {
                Map<String, String> payment = new LinkedHashMap<>();
                payment.put("timestamp", parts[0]);
                payment.put("user", parts[1]);
                payment.put("photographer", parts[2]);
                payment.put("packageType", parts[3]);
                payment.put("eventType", parts[4]);
                payment.put("date", parts[5]);
                payment.put("price", parts[6]);
                payment.put("status", parts[7]);
                payments.add(payment);
            }
        }
        return payments;
    }

    // Complete deletePayment method
    public boolean deletePayment(String timestamp) throws IOException {
        String paymentsPath = userFilePath.replace("users.txt", "payments.txt");
        List<String> lines = FileUtil.readAllLines(paymentsPath);
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = Arrays.stream(line.split(","))
                    .map(String::trim)
                    .toArray(String[]::new);

            if (parts.length > 0 && !parts[0].equals(timestamp)) {
                updatedLines.add(line);
            } else {
                found = true;
            }
        }

        if (found) {
            writeAllLines(paymentsPath, updatedLines);
        }
        return found;
    }

    // Helper method
    private void writeAllLines(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}