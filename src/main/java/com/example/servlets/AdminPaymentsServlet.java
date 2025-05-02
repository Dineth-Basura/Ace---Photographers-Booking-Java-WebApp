package com.example.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@WebServlet("/adminPayments")
public class AdminPaymentsServlet extends HttpServlet {

    private static final String DATA_PATH = "D:/photoBook/photographBook/src/main/webapp/data/payments.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String,String>> payments = new ArrayList<>();

        try {
            // Read all lines from file
            List<String> lines = Files.readAllLines(Paths.get(DATA_PATH));

            // Parse each line
            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s*,\\s*"); // Split by comma with optional spaces

                if (parts.length >= 8) {
                    Map<String,String> payment = new LinkedHashMap<>();
                    payment.put("timestamp", parts[0]);
                    payment.put("user", parts[1]);
                    payment.put("photographer", parts[2]);
                    payment.put("packageType", parts[3]);
                    payment.put("eventType", parts[4]);
                    payment.put("date", parts[5]);
                    payment.put("price", parts[6]);
                    payment.put("status", parts[7]);
                    payments.add(payment);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error reading payments: " + e.getMessage());
        }

        request.setAttribute("payments", payments);
        request.getRequestDispatcher("adminPayments.jsp").forward(request, response);
    }
}