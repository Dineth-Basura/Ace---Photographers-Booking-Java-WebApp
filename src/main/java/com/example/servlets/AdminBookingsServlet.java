package com.example.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@WebServlet("/adminBookings")
public class AdminBookingsServlet extends HttpServlet {

    private static final String DATA_PATH = "D:/photoBook/photographBook/src/main/webapp/data/bookings.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String,String>> bookings = new ArrayList<>();

        try {
            // Read all lines from file
            List<String> lines = Files.readAllLines(Paths.get(DATA_PATH));

            // Parse each line
            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s*,\\s*"); // Split by comma with optional spaces

                if (parts.length >= 6) {
                    Map<String,String> booking = new LinkedHashMap<>();
                    booking.put("date", parts[0]);
                    booking.put("eventType", parts[1]);
                    booking.put("user", parts[2]);
                    booking.put("photographer", parts[3]);
                    booking.put("packageType", parts[4]);
                    booking.put("status", parts[5]);
                    bookings.add(booking);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error reading bookings: " + e.getMessage());
        }

        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("adminBookings.jsp").forward(request, response);
    }
}