package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/updatePhotographerProfile")
public class PhotographerProfileUpdateServlet extends HttpServlet {
    private final String FILE_PATH = "D:/photoBook/photographBook/src/main/webapp/data/Photographers.txt";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("photographerUsername") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("photographerUsername");
        String newPassword = request.getParameter("newPassword");

        File file = new File(FILE_PATH);
        List<String> updatedLines = new ArrayList<>();
        boolean passwordUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Photographer:")) {
                    String[] parts = line.substring(13).trim().split(",");
                    if (parts.length >= 3 && parts[0].equals(username)) {
                        String passwordToSave = (newPassword == null || newPassword.trim().isEmpty()) ? parts[1] : newPassword;
                        updatedLines.add("Photographer: " + parts[0] + "," + passwordToSave + "," + parts[2]);
                        passwordUpdated = true;
                        continue;
                    }
                }
                updatedLines.add(line);
            }
        }

        if (passwordUpdated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String updated : updatedLines) {
                    writer.write(updated);
                    writer.newLine();
                }
            }
        }

        response.sendRedirect("photographerProfile.jsp?updated=1");
    }
}