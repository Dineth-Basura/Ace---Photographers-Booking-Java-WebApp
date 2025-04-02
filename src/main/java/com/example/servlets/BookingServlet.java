package com.example.servlets;

import com.example.models.Booking;
import com.example.services.BookingService;
import com.example.utils.BookingQueueManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");
        String eventDate = request.getParameter("date");
        String eventType = request.getParameter("eventType");
        String photographer = request.getParameter("photographer");

        // Create Booking object with all 4 parameters
        Booking booking = new Booking(username, eventType, eventDate, photographer);

        // Save booking to bookings.txt
        BookingService bookingService = new BookingService(
                getServletContext().getRealPath("/WEB-INF/bookings.txt"));
        bookingService.addBooking(booking);

        // Handle Queue
        String queueFilePath = getServletContext().getRealPath("/WEB-INF/bookingQueue.txt");
        BookingQueueManager queueManager = new BookingQueueManager(queueFilePath);

        if (!queueManager.hasActiveBooking(username)) {
            queueManager.addBooking(username, photographer, eventDate);
            System.out.println("Added to queue: " + photographer + " for " + username);
        } else {
            System.out.println("User already has an active booking");
        }

        response.sendRedirect("dashboard.jsp");
    }
}