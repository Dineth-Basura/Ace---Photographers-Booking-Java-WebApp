package com.example.services;

import java.io.*;
import java.util.*;

public class PaymentService {
    private final String filePath;
    private final Object fileLock = new Object(); // For thread-safe file operations

    public PaymentService(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            synchronized (fileLock) {
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        System.err.println("Error creating payments file: " + e.getMessage());
                    }
                }
            }
        }
    }

    public void savePayment(String userEmail, String photographer, String selectedPackage,
                            String eventType, String date, String price) throws IOException {
        synchronized (fileLock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String line = String.join(",",
                        timestamp,
                        sanitize(userEmail),
                        sanitize(photographer),
                        sanitize(selectedPackage),
                        sanitize(eventType),
                        sanitize(date),
                        sanitize(price),
                        "completed"
                );
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public List<Map<String, String>> getPaymentsForUser(String userEmail) throws IOException {
        List<Map<String, String>> payments = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return payments;
        }

        synchronized (fileLock) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", -1); // Keep empty values
                    if (parts.length >= 8 && parts[1].equals(userEmail)) {
                        Map<String, String> payment = new LinkedHashMap<>();
                        payment.put("timestamp", parts[0]);
                        payment.put("photographer", parts[2]);
                        payment.put("packageType", parts[3]);
                        payment.put("eventType", parts[4]);
                        payment.put("date", parts[5]);
                        payment.put("price", parts[6]);
                        payment.put("status", parts[7]);
                        payments.add(payment);
                    }
                }
            }
        }
        return payments;
    }

    public boolean deletePayment(String userEmail, String timestamp) throws IOException {
        boolean found = false;
        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        }

        synchronized (fileLock) {
            List<String> updatedLines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", -1);
                    if (parts.length >= 8 && parts[0].equals(timestamp) && parts[1].equals(userEmail)) {
                        found = true;
                        continue;
                    }
                    updatedLines.add(line);
                }
            }

            if (found) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (String line : updatedLines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        }
        return found;
    }

    private String sanitize(String input) {
        if (input == null) return "";
        return input.replace(",", "").replace("\n", "").replace("\r", "");
    }

    // Optional: For admin purposes
    public List<Map<String, String>> getAllPayments() throws IOException {
        List<Map<String, String>> allPayments = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return allPayments;
        }

        synchronized (fileLock) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",", -1);
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
                        allPayments.add(payment);
                    }
                }
            }
        }
        return allPayments;
    }
}