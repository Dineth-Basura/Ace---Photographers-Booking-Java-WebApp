package com.example.utils;

import com.example.models.Booking;
import java.util.LinkedList;
import java.util.Queue;

public class BookingQueue {
    private static Queue<Booking> bookingQueue = new LinkedList<>();

    public static void addBooking(Booking booking) {
        bookingQueue.add(booking);
    }

    public static Queue<Booking> getQueue() {
        return bookingQueue;
    }

    public static void cancelBooking(String username) {
        bookingQueue.removeIf(b -> b.getUsername().equals(username));
    }

    public static boolean hasActiveBooking(String username) {
        return bookingQueue.stream().anyMatch(b -> b.getUsername().equals(username));
    }
}