package com.example.servlets;

import com.example.services.PaymentService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/paymentHistory")
public class PaymentHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");

        if (userEmail == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            PaymentService paymentService = new PaymentService(
                    "D:/photoBook/photographBook/src/main/webapp/data/payments.txt"
            );

            List<Map<String, String>> payments = paymentService.getPaymentsForUser(userEmail);
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("paymentHistory.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error loading payment history: " + e.getMessage());
            request.getRequestDispatcher("paymentHistory.jsp").forward(request, response);
        }
    }
}