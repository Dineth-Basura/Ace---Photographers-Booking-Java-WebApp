package com.example.servlets;

import com.example.services.PaymentService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deletePayment")
public class DeletePaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");

        if (userEmail == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String timestamp = request.getParameter("timestamp");

        try {
            PaymentService paymentService = new PaymentService(
                    "D:/photoBook/photographBook/src/main/webapp/data/payments.txt"
            );

            boolean deleted = paymentService.deletePayment(userEmail, timestamp);
            if (deleted) {
                session.setAttribute("message", "Payment deleted successfully");
            } else {
                session.setAttribute("error", "Payment not found or already deleted");
            }
        } catch (Exception e) {
            session.setAttribute("error", "Error deleting payment: " + e.getMessage());
        }

        response.sendRedirect("paymentHistory");
    }
}