<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.models.Booking, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Photographer Bookings</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f5f5f5;
        }

        .sidebar {
            width: 240px;
            background-color: #121212;
            color: white;
            height: 100vh;
            position: fixed;
            padding: 30px 20px;
        }

        .sidebar h3 {
            color: #ffc107;
            font-weight: bold;
        }

        .sidebar a {
            color: white;
            display: block;
            margin: 20px 0;
            text-decoration: none;
        }

        .sidebar a:hover {
            color: #ffc107;
        }

        .main-content {
            margin-left: 260px;
            padding: 30px;
        }

        .booking-card {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 6px rgba(0, 0, 0, 0.05);
            margin-bottom: 20px;
        }

        .btn-approve {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 5px 15px;
            border-radius: 5px;
            margin-right: 10px;
        }

        .btn-reject {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 15px;
            border-radius: 5px;
        }

        .status {
            font-weight: bold;
        }

        .status-approved {
            color: green;
        }

        .status-rejected {
            color: red;
        }

        .status-pending {
            color: orange;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h3><img src="images/logo-black.png" style="width: 30px; margin-right: 5px;"> Pro <span style="color: white;">Photography</span></h3>
    <a href="photographerDashboard.jsp">Dashboard</a>
    <a href="photographerBookings">Bookings</a>
    <a href="#">Clients</a>
    <a href="#">Payments</a>
    <a href="photographerProfile.jsp">My Profile</a>
    <a href="logout.jsp" style="color: #dc3545;">Logout</a>
</div>

<div class="main-content">
    <h3>Client Bookings</h3>

    <%
        List<Booking> bookings = (List<Booking>) request.getAttribute("photographerBookings");
        if (bookings == null || bookings.isEmpty()) {
    %>
    <div class="alert alert-info">No bookings received yet.</div>
    <%
    } else {
        for (Booking b : bookings) {
    %>
    <div class="booking-card">
        <p><strong>Client:</strong> <%= b.getUsername() %></p>
        <p><strong>Event:</strong> <%= b.getEventType() %></p>
        <p><strong>Date:</strong> <%= b.getDate() %></p>
        <p><strong>Package:</strong> <%= b.getPackageName() %></p>
        <p class="status
            <%= b.getStatus().equals("Approved") ? "status-approved" :
                b.getStatus().equals("Rejected") ? "status-rejected" : "status-pending" %>">
            <strong>Status:</strong> <%= b.getStatus() %>
        </p>

        <% if (b.getStatus().equals("Pending")) { %>
        <!-- Approve Form -->
        <form action="BookingDecisionServlet" method="post" style="display:inline;">
            <input type="hidden" name="date" value="<%= b.getDate() %>">
            <input type="hidden" name="client" value="<%= b.getUsername() %>">
            <input type="hidden" name="photographer" value="<%= b.getPhotographer() %>">
            <input type="hidden" name="decision" value="Approved">
            <button type="submit" class="btn-approve">Approve</button>
        </form>

        <!-- Reject Form -->
        <form action="BookingDecisionServlet" method="post" style="display:inline;">
            <input type="hidden" name="date" value="<%= b.getDate() %>">
            <input type="hidden" name="client" value="<%= b.getUsername() %>">
            <input type="hidden" name="photographer" value="<%= b.getPhotographer() %>">
            <input type="hidden" name="decision" value="Rejected">
            <button type="submit" class="btn-reject">Reject</button>
        </form>
        <% } %>
    </div>
    <%
            }
        }
    %>
</div>

</body>
</html>