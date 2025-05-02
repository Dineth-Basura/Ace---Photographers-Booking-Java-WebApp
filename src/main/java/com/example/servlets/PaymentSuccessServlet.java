package com.example.servlets;

import com.example.services.PaymentService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/paymentSuccess")
public class PaymentSuccessServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");

        // Get payment details
        String photographer = request.getParameter("photographer");
        String selectedPackage = request.getParameter("package");
        String date = request.getParameter("date");
        String price = request.getParameter("price");
        String eventType = request.getParameter("eventType");

        // Save payment to file
        PaymentService paymentService = new PaymentService(
                "D:/photoBook/photographBook/src/main/webapp/data/payments.txt"
        );
        paymentService.savePayment(userEmail, photographer, selectedPackage, eventType, date, price);

        // Set attributes for receipt page
        request.setAttribute("photographer", photographer);
        request.setAttribute("packageType", selectedPackage);
        request.setAttribute("date", date);
        request.setAttribute("price", price);
        request.setAttribute("eventType", eventType);

        request.getRequestDispatcher("payment-success.jsp").forward(request, response);
    }
}