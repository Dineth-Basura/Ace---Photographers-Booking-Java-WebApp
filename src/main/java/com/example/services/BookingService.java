package com.example.services;

import com.example.models.Booking;
import com.example.utils.BookingQueue;
import java.io.*;
import java.util.*;

public class BookingService {
    private final String bookingFilePath;

    public BookingService(String bookingFilePath) {
        this.bookingFilePath = bookingFilePath;
        System.out.println("[BookingService] Initialized with file: " + bookingFilePath);
    }

    public void addBooking(Booking booking) {
        System.out.println("\n[BookingService] Adding new booking:");
        System.out.println(" - Date: " + booking.getDate());
        System.out.println(" - User: " + booking.getUsername());
        System.out.println(" - Photographer: " + booking.getPhotographer());

        File file = new File(bookingFilePath);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            String bookingString = booking.toFileString();
            pw.println(bookingString);
            System.out.println("Written to file: " + bookingString);

            BookingQueue.addBooking(booking);
            System.out.println("Added to BookingQueue");
        } catch (IOException e) {
            System.err.println("ERROR writing booking:");
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        System.out.println("[BookingService] Reading all bookings from: " + bookingFilePath);
        List<Booking> bookings = new ArrayList<>();
        File file = new File(bookingFilePath);

        if (!file.exists()) {
            System.err.println("ERROR: Bookings file not found");
            return bookings;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("Processing line " + lineNum + ": " + line);
                String[] parts = line.split(",", -1);
                if (parts.length >= 6) {
                    Booking booking = new Booking(
                            parts[0], parts[1], parts[2],
                            parts[3], parts[4], parts[5]
                    );
                    bookings.add(booking);
                    System.out.println("Parsed booking: " + booking);
                } else {
                    System.err.println("Skipping invalid line (expected 6 fields): " + line);
                }
                lineNum++;
            }
        } catch (IOException e) {
            System.err.println("ERROR reading bookings:");
            e.printStackTrace();
        }
        System.out.println("Total bookings loaded: " + bookings.size());
        return bookings;
    }

    public void updateBookingStatus(String username, String date, String newStatus) {
        System.out.println("\n[BookingService] Updating status for:");
        System.out.println(" - User: " + username);
        System.out.println(" - Date: " + date);
        System.out.println(" - New Status: " + newStatus);

        List<Booking> bookings = getAllBookings();
        File file = new File(bookingFilePath);

        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (Booking b : bookings) {
                if (b.getUsername().equals(username) && b.getDate().equals(date)) {
                    System.out.println("Found matching booking - updating status");
                    b.setStatus(newStatus);
                }
                pw.println(b.toFileString());
            }
            System.out.println("Bookings file updated successfully");
        } catch (IOException e) {
            System.err.println("ERROR updating bookings:");
            e.printStackTrace();
        }
    }
}