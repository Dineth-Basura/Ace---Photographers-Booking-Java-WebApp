package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/deletePhotographer")
public class DeletePhotographerServlet extends HttpServlet {
    private final String FILE_PATH = "D:/photoBook/photographBook/src/main/webapp/data/Photographers.txt";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usernameToDelete = request.getParameter("username");
        if (usernameToDelete == null || usernameToDelete.trim().isEmpty()) {
            response.sendRedirect("adminPhotographers.jsp");
            return;
        }

        File file = new File(FILE_PATH);
        List<String> updated = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("Photographer: " + usernameToDelete + ",")) {
                    updated.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String entry : updated) {
                writer.write(entry);
                writer.newLine();
            }
        }

        response.sendRedirect("adminPhotographers.jsp");
    }
}