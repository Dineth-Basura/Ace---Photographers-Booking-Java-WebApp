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
            BookingQueue.addBooking(booking); // add to queue
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
                if (parts.length >= 4) {
                    bookings.add(new Booking(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}