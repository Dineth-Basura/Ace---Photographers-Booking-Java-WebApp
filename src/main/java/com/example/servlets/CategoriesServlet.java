package com.example.servlets;

import com.example.models.Photographer;
import com.example.utils.PhotographerSorter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/categories")
public class CategoriesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = getServletContext().getRealPath("/WEB-INF/photographers.txt");
        List<Photographer> photographers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 4) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String category = parts[2].trim();
                        double rating = Double.parseDouble(parts[3].trim());

                        photographers.add(new Photographer(id, name, category, rating));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        photographers = PhotographerSorter.sortByRating(photographers);
        request.setAttribute("photographers", photographers);
        request.getRequestDispatcher("categories.jsp").forward(request, response);
    }
}