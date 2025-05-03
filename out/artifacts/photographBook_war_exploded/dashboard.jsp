<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userName = (String) session.getAttribute("userName");
    if (userName == null || userName.trim().isEmpty()) {
        response.sendRedirect("login.jsp"); // Redirect if not logged in
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard | Ace</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        body {
            background-color: #fff;
            font-family: 'Segoe UI', sans-serif;
        }

        .main-content {
            flex-grow: 1;
            padding: 30px;
            background-color: #f8f9fa;
        }

        .topbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .topbar h3 {
            color: #000;
            font-weight: 700;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-pic {
            width: 45px;
            height: 45px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 10px;
            border: 2px solid #ffc107;
        }

        .greeting {
            font-size: 16px;
            font-weight: 600;
            color: #000;
        }

        .card-stats {
            border-radius: 10px;
            padding: 20px;
            color: #000;
            background-color: #ffc107;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .card-stats h2 {
            font-weight: bold;
            margin-top: 10px;
            font-size: 30px;
        }

        .card-stats.dark {
            background-color: #000;
            color: #fff;
        }

        .section-title {
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 15px;
            color: #333;
        }

        .card.bookings {
            border: none;
            border-radius: 10px;
            background: #fff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }

        .card-header {
            background-color: #ffc107;
            font-weight: bold;
            color: #000;
            border-radius: 10px 10px 0 0;
        }
    </style>
</head>
<body>
<div class="d-flex">
    <jsp:include page="sidebar.jsp"/>

    <div class="main-content">
        <!-- Top bar with greeting and profile -->
        <div class="topbar">
            <h3>Dashboard</h3>
            <div class="user-info">
                <img src="images/profile-icon.png" alt="User" class="user-pic">
                <span class="greeting">Welcome, <%= userName %>!</span>
            </div>
        </div>

        <!-- Dashboard Stats -->
        <div class="row mb-4">
            <div class="col-md-4 mb-3">
                <div class="card-stats">
                    <h5>Total Bookings</h5>
                    <h2>12</h2>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card-stats dark">
                    <h5>Upcoming Sessions</h5>
                    <h2>3</h2>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <div class="card-stats">
                    <h5>Pending Payments</h5>
                    <h2>$150</h2>
                </div>
            </div>
        </div>

        <!-- Recent Bookings -->
        <div class="card bookings mt-4">
            <div class="card-header">Recent Bookings</div>
            <div class="card-body">
                <div class="alert alert-info mb-0">No recent bookings found.</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>