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

    private final String USER_FILE = "D:/photoBook/photographBook/src/main/webapp/data/users.txt";
    private final String PROFILE_IMG_DIR = "D:/photoBook/photographBook/src/main/webapp/images/user_profiles/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        boolean passwordChanged = false;
        boolean passwordMismatch = false;
        boolean incorrectOldPass = false;

        List<String> updatedLines = new ArrayList<>();

        // Validate and update password
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(username)) {
                    String currentPass = parts[1];

                    if (oldPassword == null || !oldPassword.equals(currentPass)) {
                        incorrectOldPass = true;
                        break;
                    }

                    if (newPassword != null && !newPassword.isEmpty()) {
                        if (!newPassword.equals(confirmPassword)) {
                            passwordMismatch = true;
                            break;
                        } else {
                            passwordChanged = true;
                            updatedLines.add(parts[0] + "," + newPassword + "," + parts[2]);
                        }
                    } else {
                        updatedLines.add(line);  // No password change
                    }
                } else {
                    updatedLines.add(line);
                }
            }
        }

        if (incorrectOldPass) {
            response.sendRedirect("profileUpdate.jsp?error=incorrectOldPassword");
            return;
        }

        if (passwordMismatch) {
            response.sendRedirect("profileUpdate.jsp?error=mismatch");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (String updated : updatedLines) {
                writer.write(updated);
                writer.newLine();
            }
        }

        // === Handle profile picture ===
        Part profilePic = request.getPart("profilePic");
        if (profilePic != null && profilePic.getSize() > 0) {
            File imageFile = new File(PROFILE_IMG_DIR + username + ".jpg");
            if (imageFile.exists()) imageFile.delete();

            File folder = imageFile.getParentFile();
            if (!folder.exists()) folder.mkdirs();

            try (InputStream input = profilePic.getInputStream();
                 FileOutputStream output = new FileOutputStream(imageFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
        }

        // Prevent caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.sendRedirect("profileUpdate.jsp?updated=1");
    }
}