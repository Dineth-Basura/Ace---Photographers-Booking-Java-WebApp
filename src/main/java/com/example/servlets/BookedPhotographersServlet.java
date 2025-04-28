package com.example.servlets;

import com.example.models.Booking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/bookedPhotographers")
public class BookedPhotographersServlet extends HttpServlet {
    private final String BOOKINGS_PATH = "D:/photoBook/photographBook/src/main/webapp/data/bookings.txt";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        System.out.println("[DEBUG] Logged-in username: " + username);

        List<Booking> userBookings = new ArrayList<>();

        if (username != null && !username.isEmpty()) {
            try (BufferedReader br = new BufferedReader(new FileReader(BOOKINGS_PATH))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("[DEBUG] Line read: " + line);
                    String[] parts = line.split(",", -1);
                    if (parts.length >= 5) {
                        System.out.println("[DEBUG] Parsed parts: " + Arrays.toString(parts));
                        if (parts[2].equals(username)) {
                            Booking booking = new Booking(parts[0], parts[1], parts[2], parts[3], parts[4]);
                            userBookings.add(booking);
                            System.out.println("[DEBUG] Booking added to list: " + booking.toFileString());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("[ERROR] Exception while reading bookings: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("[DEBUG] Username is null or empty!");
        }

        System.out.println("[DEBUG] Total bookings found for user: " + userBookings.size());
        request.setAttribute("userBookings", userBookings);
        request.getRequestDispatcher("bookedPhotographers.jsp").forward(request, response);
    }
}