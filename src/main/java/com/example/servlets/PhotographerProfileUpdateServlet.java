package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/updatePhotographerProfile")
@MultipartConfig
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

        // === Password Update ===
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

        // === Profile Picture Upload ===
        Part filePart = request.getPart("profilePic");
        if (filePart != null && filePart.getSize() > 0) {
            String profileImagePath = request.getServletContext().getRealPath("/images/profiles/" + username + ".jpg");

            File imageFile = new File(profileImagePath);
            File imageDir = imageFile.getParentFile();
            if (!imageDir.exists()) imageDir.mkdirs();

            try (InputStream input = filePart.getInputStream();
                 FileOutputStream output = new FileOutputStream(imageFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        }

        response.sendRedirect("photographerProfile.jsp?updated=1");
    }
}