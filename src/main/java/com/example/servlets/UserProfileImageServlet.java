package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/userProfileImage")
public class UserProfileImageServlet extends HttpServlet {
    private static final String IMAGE_DIR = "D:/photoBook/photographBook/src/main/webapp/images/user_profiles/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User is required.");
            return;
        }

        File imageFile = new File(IMAGE_DIR + user + ".jpg");
        if (!imageFile.exists()) {
            imageFile = new File(IMAGE_DIR + "default-profile.jpg");
        }

        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        try (FileInputStream in = new FileInputStream(imageFile);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }
    }
}