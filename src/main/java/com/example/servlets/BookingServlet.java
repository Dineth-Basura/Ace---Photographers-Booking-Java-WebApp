package com.example.servlets;

import com.example.models.Booking;
import com.example.services.BookingService;
import com.example.utils.BookingQueueManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    private final String DATA_FOLDER = "D:/photoBook/photographBook/src/main/webapp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        String eventDate = request.getParameter("date");
        String eventType = request.getParameter("eventType");
        String photographer = request.getParameter("photographer");
        String packageName = request.getParameter("package");

        Booking booking = new Booking(eventDate, eventType, username, photographer, packageName);

        // Save to bookings.txt in /data
        File bookingsFile = new File(DATA_FOLDER, "bookings.txt");
        bookingsFile.getParentFile().mkdirs();
        BookingService bookingService = new BookingService(bookingsFile.getAbsolutePath());
        bookingService.addBooking(booking);

        // Queue logic using /data path
        File queueFile = new File(DATA_FOLDER, "D:/photoBook/photographBook/src/main/webapp/data/bookingQueue.txt");
        BookingQueueManager queueManager = new BookingQueueManager(queueFile.getAbsolutePath());

        if (!queueManager.hasActiveBooking(username)) {
            queueManager.addBooking(username, photographer, eventDate);
            System.out.println("[DEBUG] Added to queue: " + photographer + " for " + username);
        } else {
            System.out.println("[INFO] User already has an active booking");
        }

        response.sendRedirect("dashboard.jsp");
    }
}