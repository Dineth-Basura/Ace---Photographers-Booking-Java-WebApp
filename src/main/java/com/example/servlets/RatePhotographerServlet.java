package com.example.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.util.*;

@WebServlet("/ratePhotographer")
public class RatePhotographerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String photographerName = request.getParameter("photographer");
        double newRating = Double.parseDouble(request.getParameter("rating"));

        String filePath = getServletContext().getRealPath("/WEB-INF/photographers.txt");
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length >= 4 && parts[1].trim().equalsIgnoreCase(photographerName)) {
                    double currentRating = Double.parseDouble(parts[3].trim());
                    double updatedRating = (currentRating + newRating) / 2;
                    parts[3] = String.format("%.1f", updatedRating);
                    line = String.join(",", parts);
                }
                updatedLines.add(line);
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for(String updatedLine : updatedLines) {
                pw.println(updatedLine);
            }
        }

        response.sendRedirect("categories");
    }
}