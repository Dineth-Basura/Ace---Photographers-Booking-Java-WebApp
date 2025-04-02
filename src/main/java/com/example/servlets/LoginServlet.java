package com.example.servlets;

import com.example.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final String DATA_FOLDER = "D:/photoBook/photographBook/src/main/webapp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isPhotographer = request.getParameter("isPhotographer") != null;

        File adminFile = new File(DATA_FOLDER, "admin.txt");
        File photographerFile = new File(DATA_FOLDER, "Photographers.txt");
        File usersFile = new File(DATA_FOLDER, "users.txt");

        // Admin Login
        if (adminFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(adminFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.trim().split(",");
                    if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                        request.getSession().setAttribute("adminUsername", username);
                        response.sendRedirect("adminDashboard.jsp");
                        return;
                    }
                }
            }
        }

        // Photographer Login
        if (isPhotographer && photographerFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(photographerFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.startsWith("Photographer:")) continue;
                    String[] parts = line.substring(13).trim().split(",");
                    if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                        request.getSession().setAttribute("photographerUsername", parts[0]);
                        response.sendRedirect("photographerDashboard.jsp");
                        return;
                    }
                }
            }
            response.sendRedirect("login.jsp?error=1");
            return;
        }

        // User Login
        UserService userService = new UserService(usersFile.getAbsolutePath());
        if (userService.authenticateUser(username, password)) {
            List<String> users = userService.getAllUsers();
            for (String line : users) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(username)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", parts[0]);
                    session.setAttribute("email", parts[2]);
                    session.setAttribute("userName", parts[0]);
                    break;
                }
            }
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}