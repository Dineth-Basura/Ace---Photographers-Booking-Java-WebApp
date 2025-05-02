<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String photographer = (String) request.getAttribute("photographer");
  String selectedPackage = (String) request.getAttribute("package");
  String sessionDate = (String) request.getAttribute("date");
  String price = (String) request.getAttribute("price");
  String eventType = (String) request.getAttribute("eventType");
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
  <p><strong>Event Type:</strong> <%= eventType %></p>
  <p><strong>Package:</strong> <%= selectedPackage %></p>
  <p><strong>Session Date:</strong> <%= sessionDate %></p>
  <p><strong>Total Paid:</strong> $<%= price %></p>
  <hr>
  <a href="dashboard.jsp" class="btn btn-outline-primary mt-3">Back to Dashboard</a>
  <!-- Add this below the "Back to Dashboard" button -->
  <a href="paymentHistory" class="btn btn-primary mt-3 ml-2">View Payment History</a>
</div>
</body>
</html>