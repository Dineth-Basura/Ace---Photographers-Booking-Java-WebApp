package com.example.servlets;

import com.example.services.AdminService;
import com.example.models.Photographer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService adminService = new AdminService(
                getServletContext().getRealPath("/data/users.txt"),
                getServletContext().getRealPath("/data/photographers.txt")
        );

        List<String> allUsers = adminService.getAllUsers();
        List<Photographer> sortedPhotographers = adminService.getSortedPhotographers();

        request.setAttribute("users", allUsers);
        request.setAttribute("photographers", sortedPhotographers);

        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userToDelete = request.getParameter("deleteUser");
        if (userToDelete != null && !userToDelete.isEmpty()) {
            AdminService adminService = new AdminService(
                    getServletContext().getRealPath("/data/users.txt"),
                    getServletContext().getRealPath("/data/photographers.txt")
            );
            adminService.deleteUser(userToDelete);
        }
        response.sendRedirect("admin");
    }
}