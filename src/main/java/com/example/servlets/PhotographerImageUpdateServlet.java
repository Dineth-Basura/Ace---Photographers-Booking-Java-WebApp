package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/updatePhotographerProfileImage")
@MultipartConfig
public class PhotographerImageUpdateServlet extends HttpServlet {
    private final String IMAGE_FOLDER = "D:/photoBook/photographBook/src/main/webapp/images/profiles";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("photographerUsername") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("photographerUsername");
        Part profilePicPart = request.getPart("profilePic");

        if (profilePicPart != null && profilePicPart.getSize() > 0) {
            File uploadDir = new File(IMAGE_FOLDER);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File imageFile = new File(uploadDir, username + ".jpg");

            try (InputStream input = profilePicPart.getInputStream();
                 FileOutputStream output = new FileOutputStream(imageFile)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("[UPLOAD] Profile picture updated for: " + username);
        }

        response.sendRedirect("photographerProfile.jsp?imgUpdated=1");
    }
}