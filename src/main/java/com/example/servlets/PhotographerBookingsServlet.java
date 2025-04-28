package com.example.servlets;

import com.example.models.Booking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/photographerBookings")
public class PhotographerBookingsServlet extends HttpServlet {
    private final String BOOKINGS_PATH = "D:/photoBook/photographBook/src/main/webapp/data/bookings.txt";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String photographerUsername = (String) request.getSession().getAttribute("photographerUsername");
        System.out.println("[DEBUG] Logged in photographer username: " + photographerUsername);

        List<Booking> photographerBookings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKINGS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("[DEBUG] Reading line: " + line);
                String[] parts = line.split(",", -1);

                if (parts.length >= 6) {
                    System.out.println("[DEBUG] Parsed values: " + Arrays.toString(parts));
                    String bookedPhotographer = parts[3].trim();
                    System.out.println("[DEBUG] Comparing booked photographer '" + bookedPhotographer + "' with session '" + photographerUsername + "'");

                    if (bookedPhotographer.equals(photographerUsername)) {
                        Booking booking = new Booking(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                        photographerBookings.add(booking);
                        System.out.println("[DEBUG] Match found. Booking added.");
                    }
                } else {
                    System.out.println("[WARN] Skipping line with insufficient fields: " + line);
                }
            }
        }

        System.out.println("[DEBUG] Total bookings for photographer: " + photographerBookings.size());
        request.setAttribute("photographerBookings", photographerBookings);
        request.getRequestDispatcher("photographerBookings.jsp").forward(request, response);
    }
}