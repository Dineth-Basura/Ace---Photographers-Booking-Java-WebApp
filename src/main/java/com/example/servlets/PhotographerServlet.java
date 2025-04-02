package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/PhotographerServlet")
public class PhotographerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String username = (String) request.getSession().getAttribute("photographerUsername");

        if (action.equals("updatePassword")) {
            String newPassword = request.getParameter("newPassword");
            String filePath = getServletContext().getRealPath("D:/photoBook/photographBook/src/main/webapp/dataPhotographers.txt");
            File inputFile = new File(filePath);
            File tempFile = new File(filePath + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    parts[1] = newPassword;
                    writer.write(String.join(",", parts) + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            reader.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                throw new IOException("Failed to update file");
            }

        } else if (action.equals("accept") || action.equals("reject")) {
            String client = request.getParameter("client");
            String bookingFile = getServletContext().getRealPath("/data/bookings.txt");

            File inputFile = new File(bookingFile);
            File tempFile = new File(bookingFile + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[2].equals(username) && parts[1].equals(client) && parts[4].equals("pending")) {
                    parts[4] = action.equals("accept") ? "accepted" : "rejected";
                    writer.write(String.join(",", parts) + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            writer.close();
            reader.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                throw new IOException("Failed to update bookings file");
            }
        }

        response.sendRedirect("photographerDashboard.jsp");
    }
}