package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {
    private final String BOOKINGS_PATH = "D:/photoBook/photographBook/src/main/webapp/data/bookings.txt";
    private final String QUEUE_PATH = "D:/photoBook/photographBook/src/main/webapp/data/bookingQueue.txt";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String date = request.getParameter("date");

        // === Remove from bookings.txt ===
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKINGS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains(username) || !line.contains(date)) {
                    updatedLines.add(line);
                }
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKINGS_PATH))) {
            for (String updated : updatedLines) {
                pw.println(updated);
            }
        }

        // === Remove from bookingQueue.txt ===
        List<String> queueLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(QUEUE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(username + ",") || !line.contains(date)) {
                    queueLines.add(line);
                }
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(QUEUE_PATH))) {
            for (String q : queueLines) {
                pw.println(q);
            }
        }

        System.out.println("[DEBUG] Booking cancelled for user: " + username + " on date: " + date);
        response.sendRedirect("bookedPhotographers");
    }
}