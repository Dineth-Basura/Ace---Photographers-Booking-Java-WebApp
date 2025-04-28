package com.example.servlets;

import com.example.services.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/BookingDecisionServlet")
public class BookingDecisionServlet extends HttpServlet {
    private final String DATA_PATH = "D:/photoBook/photographBook/src/main/webapp/data/bookings.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("client");
        String date = request.getParameter("date");
        String decision = request.getParameter("decision"); // "Approved" or "Rejected"

        System.out.println("[DEBUG] Received decision: " + decision + " for user: " + username + " on date: " + date);

        // Validate input
        if (username == null || date == null || decision == null) {
            System.out.println("[ERROR] Missing parameters in BookingDecisionServlet");
            response.sendRedirect("photographerBookings");
            return;
        }

        // Use decision directly as status (already either Approved or Rejected)
        String status = decision;

        BookingService service = new BookingService(DATA_PATH);
        service.updateBookingStatus(username, date, status);

        System.out.println("[DEBUG] Booking updated with status: " + status);

        response.sendRedirect("photographerBookings");
    }
}