package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/updateProfile")
@MultipartConfig
public class ProfileUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        // Use deployed runtime paths (REALPATH)
        String usersFilePath = request.getServletContext().getRealPath("/data/users.txt");
        String profileImagePath = request.getServletContext().getRealPath("/images/user_profiles/" + username + ".jpg");

        // === Save profile picture if uploaded ===
        Part profilePicPart = request.getPart("profilePic");
        if (profilePicPart != null && profilePicPart.getSize() > 0) {
            File imageFile = new File(profileImagePath);
            File folder = imageFile.getParentFile();
            if (!folder.exists()) folder.mkdirs();

            try (InputStream input = profilePicPart.getInputStream();
                 FileOutputStream output = new FileOutputStream(imageFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        }

        // === Update password in users.txt ===
        List<String> updatedLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(username)) {
                    String updatedPass = (newPassword != null && !newPassword.isEmpty()) ? newPassword : parts[1];
                    updatedLines.add(parts[0] + "," + updatedPass + "," + parts[2]);
                } else {
                    updatedLines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFilePath))) {
            for (String updated : updatedLines) {
                writer.write(updated);
                writer.newLine();
            }
        }

        // Force refresh on redirect
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.sendRedirect("profileUpdate.jsp?updated=1");
    }
}