package com.example.servlets;

import com.example.services.PhotographerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/DeleteRatingServlet")
public class DeleteRatingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String photographer = request.getParameter("photographer");

        // Initialize service
        String basePath = "D:/photoBook/photographBook/src/main/webapp/data/";
        PhotographerService photographerService = new PhotographerService(
                basePath + "photographers.txt",
                basePath + "photographer_ratings.txt"
        );

        try {
            // Set rating to 0 to effectively "delete" it
            photographerService.updatePhotographerRating(photographer, 0.0);
            response.sendRedirect("categories.jsp");
        } catch (IOException e) {
            request.setAttribute("error", "Error deleting rating: " + e.getMessage());
            request.getRequestDispatcher("categories.jsp").forward(request, response);
        }
    }
}