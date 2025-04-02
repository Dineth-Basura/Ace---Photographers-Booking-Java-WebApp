package com.example.servlets;

import com.example.models.Booking;
import com.example.utils.BookingQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.Collectors;

@WebServlet("/bookedPhotographers")
public class BookedPhotographersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getSession().getAttribute("username");

        Queue<Booking> userBookings = BookingQueue.getQueue().stream()
                .filter(b -> b.getUsername().equals(username))
                .collect(Collectors.toCollection(LinkedList::new));

        request.setAttribute("userBookings", userBookings);
        request.getRequestDispatcher("bookedPhotographers.jsp").forward(request, response);
    }
}