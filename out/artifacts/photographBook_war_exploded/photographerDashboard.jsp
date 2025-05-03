<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Photographer Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
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

        .stat-card {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            text-align: center;
            box-shadow: 0 0 8px rgba(0,0,0,0.05);
        }

        .stat-card h4 {
            font-size: 1.2rem;
            margin-bottom: 10px;
        }

        .stat-card .value {
            font-size: 1.8rem;
            font-weight: bold;
        }

        .upcoming-bookings {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 0 8px rgba(0,0,0,0.05);
        }

        .booking-entry {
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }

        .booking-entry:last-child {
            border-bottom: none;
        }

        .booking-entry .status {
            font-weight: bold;
        }

        .confirmed {
            color: green;
        }

        .pending {
            color: orange;
        }

        .availability {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 8px rgba(0,0,0,0.05);
        }

        .availability span {
            padding: 8px 12px;
            border-radius: 5px;
            margin-right: 8px;
            margin-bottom: 10px;
            display: inline-block;
        }

        .available {
            background-color: #d4edda;
            color: #155724;
        }

        .unavailable {
            background-color: #f8d7da;
            color: #721c24;
        }

        .btn-update {
            background-color: #343a40;
            color: white;
            border: none;
            padding: 8px 20px;
            border-radius: 5px;
        }

        .btn-update:hover {
            background-color: #212529;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h3><img src="images/logo-black.png" style="width: 30px; margin-right: 5px;"> Pro <span style="color: white;">Photography</span></h3>
    <a href="#">Dashboard</a>
    <a href="photographerBookings">Bookings</a>
    <a href="#">Clients</a>
    <a href="#">Payments</a>
    <a href="photographerProfile.jsp">My Profile</a>
    <a href="logout.jsp" style="color: #dc3545;">Logout</a>
</div>

<div class="main-content">
    <div class="row mb-4">
        <div class="col-md-4">
            <div class="stat-card">
                <h4>Upcoming Sessions</h4>
                <div class="value">8</div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-card" style="background-color: #fff3cd;">
                <h4>Monthly Earnings</h4>
                <div class="value">$2,450</div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-card" style="background-color: #fff3cd;">
                <h4>Total Clients</h4>
                <div class="value">32</div>
            </div>
        </div>
    </div>

    <div class="upcoming-bookings">
        <h5>Upcoming Bookings</h5>
        <div class="booking-entry">
            <strong>Sarah Johnson</strong> – Family Portrait Session<br>
            Today at 2:00 PM – <span class="status confirmed">$350 Confirmed</span>
        </div>
        <div class="booking-entry">
            <strong>Michael Chen</strong> – Landscape Session<br>
            Tomorrow at 7:00 AM – <span class="status pending">$250 Pending</span>
        </div>
        <div class="booking-entry">
            <strong>Emily Rodriguez</strong> – Wedding Session<br>
            May 20 at 11:00 AM – <span class="status confirmed">$1200 Confirmed</span>
        </div>
        <div class="booking-entry">
            <strong>David Wilson</strong> – Corporate Event<br>
            May 22 at 3:00 PM – <span class="status confirmed">$800 Confirmed</span>
        </div>
        <div class="text-right mt-2">
            <a href="#" class="btn btn-sm btn-dark">View All Bookings</a>
        </div>
    </div>

    <div class="availability">
        <h5>Availability Schedule</h5>
        <div>
            <span class="available">Mon</span>
            <span class="available">Tue</span>
            <span class="available">Wed</span>
            <span class="available">Thu</span>
            <span class="available">Fri</span>
            <span class="unavailable">Sat</span>
            <span class="unavailable">Sun</span>
        </div>
        <button class="btn-update mt-3">Update Availability</button>
    </div>
</div>

</body>
</html>