<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String photographer = request.getParameter("photographer");
  String selectedPackage = request.getParameter("package");
  String sessionDate = request.getParameter("date");
  String price = request.getParameter("price");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Payment Successful</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    body { background: #f4f6f9; }
    .receipt-card {
      background: white;
      border-radius: 10px;
      padding: 30px;
      margin: 60px auto;
      max-width: 600px;
      box-shadow: 0 4px 15px rgba(0,0,0,0.1);
      text-align: center;
    }
  </style>
</head>
<body>
<div class="receipt-card">
  <h3>Payment Successful!</h3>
  <p class="text-success">Thank you for booking with Ace.</p>
  <hr>
  <p><strong>Photographer:</strong> <%= photographer %></p>
  <p><strong>Package:</strong> <%= selectedPackage %></p>
  <p><strong>Session Date:</strong> <%= sessionDate %></p>
  <p><strong>Total Paid:</strong> $<%= price %></p>
  <hr>
  <a href="dashboard.jsp" class="btn btn-outline-primary mt-3">Back to Dashboard</a>
</div>
</body>
</html>