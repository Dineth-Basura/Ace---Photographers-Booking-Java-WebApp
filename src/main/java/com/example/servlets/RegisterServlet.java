package com.example.servlets;

import com.example.models.User;
import com.example.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final String DATA_FOLDER = "D:/photoBook/photographBook/src/main/webapp/data";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        boolean isPhotographer = "true".equals(request.getParameter("isPhotographer"));

        File usersFile = new File(DATA_FOLDER, "users.txt");
        File photographersFile = new File(DATA_FOLDER, "Photographers.txt");
        File allUsersFile = new File(DATA_FOLDER, "all_users.txt");

        usersFile.getParentFile().mkdirs();
        usersFile.createNewFile();
        photographersFile.createNewFile();
        allUsersFile.createNewFile();

        // === Duplicate Check ===
        boolean exists = false;
        BufferedReader reader = new BufferedReader(new FileReader(isPhotographer ? photographersFile : usersFile));
        String line;
        while ((line = reader.readLine()) != null) {
            if (isPhotographer && line.startsWith("Photographer:")) {
                line = line.substring(13).trim();
            }
            String[] parts = line.split(",");
            if (parts.length >= 3 && (parts[0].equals(username) || parts[2].equals(email))) {
                exists = true;
                break;
            }
        }
        reader.close();

        if (exists) {
            response.sendRedirect("register.jsp?error=exists");
            return;
        }

        if (isPhotographer) {
            String entry = "Photographer: " + username + "," + password + "," + email;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(photographersFile, true));
                 BufferedWriter allWriter = new BufferedWriter(new FileWriter(allUsersFile, true))) {
                writer.write(entry);
                writer.newLine();
                allWriter.write(entry);
                allWriter.newLine();
            }

            response.sendRedirect("login.jsp");

        } else {
            User user = new User(username, password, email);
            UserService userService = new UserService(usersFile.getAbsolutePath());

            if (userService.registerUser(user)) {
                try (BufferedWriter allWriter = new BufferedWriter(new FileWriter(allUsersFile, true))) {
                    allWriter.write("User: " + username + "," + password + "," + email);
                    allWriter.newLine();
                }

                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp?error=1");
            }
        }
    }
}