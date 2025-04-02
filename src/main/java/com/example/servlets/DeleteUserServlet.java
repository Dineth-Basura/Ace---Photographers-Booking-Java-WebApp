package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usernameToDelete = request.getParameter("username");
        if (usernameToDelete == null || usernameToDelete.trim().isEmpty()) {
            response.sendRedirect("adminUsers.jsp");
            return;
        }

        // ✅ Hardcoded to point directly to your source users.txt
        String usersFilePath = "D:/photoBook/photographBook/src/main/webapp/data/users.txt";
        File usersFile = new File(usersFilePath);

        System.out.println("[DELETE DEBUG] Trying to delete: " + usernameToDelete);
        System.out.println("[DELETE DEBUG] Path: " + usersFile.getAbsolutePath());
        System.out.println("[DELETE DEBUG] Exists? " + usersFile.exists());

        List<String> updatedUsers = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().startsWith(usernameToDelete + ",")) {
                    updatedUsers.add(line);
                } else {
                    System.out.println("[DELETE DEBUG] Found and removed: " + line);
                    found = true;
                }
            }
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile))) {
                for (String user : updatedUsers) {
                    writer.write(user);
                    writer.newLine();
                }
            }
        } else {
            System.out.println("[DELETE DEBUG] No match found for deletion.");
        }

        response.sendRedirect("adminUsers.jsp");
    }
}