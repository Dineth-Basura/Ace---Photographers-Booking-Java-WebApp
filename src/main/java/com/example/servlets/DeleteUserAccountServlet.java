package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/deleteProfile")
public class DeleteUserAccountServlet extends HttpServlet {

    private static final String FILE_PATH = "D:/photoBook/photographBook/src/main/webapp/data/users.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String usernameToDelete = request.getParameter("username");
        if (usernameToDelete == null || usernameToDelete.isEmpty()) {
            response.sendRedirect("profileUpdate.jsp");
            return;
        }

        File inputFile = new File(FILE_PATH);
        File tempFile = new File(FILE_PATH + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(usernameToDelete + ",")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        if (!inputFile.delete()) {
            System.err.println("Failed to delete original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Failed to rename temp file.");
        }

        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}

