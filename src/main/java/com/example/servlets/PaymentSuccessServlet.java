package com.example.servlets;

import com.example.models.Booking;
import com.example.services.BookingService;
import com.example.utils.BookingQueueManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/paymentSuccess")
public class PaymentSuccessServlet extends HttpServlet {
    private final String DATA_FOLDER = "D:/photoBook/photographBook/src/main/webapp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">>> [DEBUG] PaymentSuccessServlet Triggered <<<");

        String username = (String) request.getSession().getAttribute("username");
        if (username == null || username.trim().isEmpty()) {
            System.out.println("[ERROR] Username from session is null or empty.");
            response.sendRedirect("login.jsp");
            return;
        }

        String photographer = request.getParameter("photographer");
        String sessionDate = request.getParameter("date");
        String selectedPackage = request.getParameter("package");
        String eventType = request.getParameter("eventType");
        String price = request.getParameter("price");

        System.out.println("[DEBUG] Booking Info: " + username + ", " + photographer + ", " + sessionDate + ", " + eventType + ", " + selectedPackage);

        File bookingsFile = new File(DATA_FOLDER, "bookings.txt");
        File queueFile = new File(DATA_FOLDER, "bookingQueue.txt");

        bookingsFile.getParentFile().mkdirs();
        bookingsFile.createNewFile();
        queueFile.createNewFile();

        Booking booking = new Booking(sessionDate, eventType, username, photographer, selectedPackage);
        BookingService bookingService = new BookingService(bookingsFile.getAbsolutePath());
        bookingService.addBooking(booking);
        System.out.println("[DEBUG] Booking saved to: " + bookingsFile.getAbsolutePath());

        BookingQueueManager queueManager = new BookingQueueManager(queueFile.getAbsolutePath());
        if (!queueManager.hasActiveBooking(username)) {
            queueManager.addBooking(username, photographer, sessionDate);
            System.out.println("[DEBUG] Booking added to queue.");
        } else {
            System.out.println("[INFO] Booking already exists in queue.");
        }

        request.setAttribute("photographer", photographer);
        request.setAttribute("date", sessionDate);
        request.setAttribute("package", selectedPackage);
        request.setAttribute("price", price);
        request.setAttribute("eventType", eventType);

        request.getRequestDispatcher("payment-success.jsp").forward(request, response);
    }
}