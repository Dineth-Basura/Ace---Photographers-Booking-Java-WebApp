package com.example.utils;

import java.io.*;
import java.util.*;

public class BookingQueueManager {
    private final String queueFilePath;

    public BookingQueueManager(String queueFilePath) {
        this.queueFilePath = queueFilePath;
    }

    public Queue<String> loadQueue() throws IOException {
        Queue<String> queue = new LinkedList<>();
        File file = new File(queueFilePath);
        if (!file.exists()) return queue;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                queue.offer(line);
            }
        }
        return queue;
    }

    public void saveQueue(Queue<String> queue) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(queueFilePath))) {
            for (String booking : queue) {
                writer.println(booking);
            }
        }
    }

    public boolean hasActiveBooking(String username) throws IOException {
        Queue<String> queue = loadQueue();
        for (String entry : queue) {
            if (entry.startsWith(username + ",")) {
                return true;
            }
        }
        return false;
    }

    public void addBooking(String username, String photographer, String date) throws IOException {
        Queue<String> queue = loadQueue();
        queue.offer(username + "," + photographer + "," + date);
        saveQueue(queue);
    }

    public void cancelBooking(String username) throws IOException {
        Queue<String> queue = loadQueue();
        Queue<String> updated = new LinkedList<>();

        for (String entry : queue) {
            if (!entry.startsWith(username + ",")) {
                updated.offer(entry);
            }
        }
        saveQueue(updated);
    }
}