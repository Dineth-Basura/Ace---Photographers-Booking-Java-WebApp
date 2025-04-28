package com.example.services;

import com.example.models.Booking;
import com.example.utils.BookingQueue;

import java.io.*;
import java.util.*;

public class BookingService {
    private final String bookingFilePath;

    public BookingService(String bookingFilePath) {
        this.bookingFilePath = bookingFilePath;
    }

    public void addBooking(Booking booking) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(bookingFilePath, true))) {
            pw.println(booking.toFileString());
            BookingQueue.addBooking(booking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(bookingFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 6) {
                    bookings.add(new Booking(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public void updateBookingStatus(String username, String date, String newStatus) {
        List<Booking> bookings = getAllBookings();
        try (PrintWriter pw = new PrintWriter(new FileWriter(bookingFilePath))) {
            for (Booking b : bookings) {
                if (b.getUsername().equals(username) && b.getDate().equals(date)) {
                    b.setStatus(newStatus);
                }
                pw.println(b.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}