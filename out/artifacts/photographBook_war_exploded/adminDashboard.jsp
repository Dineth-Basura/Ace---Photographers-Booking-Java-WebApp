<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
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

        .card-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 0 8px rgba(0,0,0,0.05);
        }

        .status-confirmed { color: green; font-weight: bold; }
        .status-pending { color: orange; font-weight: bold; }
        .status-cancelled { color: red; font-weight: bold; }

        .admin-table th, .admin-table td {
            vertical-align: middle !important;
        }

        .star {
            color: gold;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h3>Admin Panel</h3>
    <a href="#">Dashboard</a>
    <a href="adminUsers.jsp">Users</a>
    <a href="adminPhotographers.jsp">Photographers</a>
    <a href="adminBookings.jsp">Bookings</a>
    <a href="adminPayments.jsp">Payments</a>
    <a href="#">Reports</a>
    <a href="#">Settings</a>
    <a href="logout.jsp" style="color: #dc3545;">Logout</a>
</div>

<div class="main-content">
    <h2 class="mb-4">Admin Dashboard</h2>

    <div class="row mb-4">
        <div class="col-md-3"><div class="card-box text-center"> <h6>Total Users</h6><h4>148</h4></div></div>
        <div class="col-md-3"><div class="card-box text-center"> <h6>Total Photographers</h6><h4>32</h4></div></div>
        <div class="col-md-3"><div class="card-box text-center"> <h6>Total Bookings</h6><h4>256</h4></div></div>
        <div class="col-md-3"><div class="card-box text-center"> <h6>Total Revenue</h6><h4>$12,450</h4></div></div>
    </div>

    <div class="row mb-4">
        <div class="col-md-3"><div class="card-box"> <h6>New Bookings</h6> <p>42 <span class="text-success">+12%</span></p></div></div>
        <div class="col-md-3"><div class="card-box"> <h6>Completed Sessions</h6> <p>36 <span class="text-success">+5%</span></p></div></div>
        <div class="col-md-3"><div class="card-box"> <h6>New Users</h6> <p>18 <span class="text-success">+8%</span></p></div></div>
        <div class="col-md-3"><div class="card-box"> <h6>Pending Payments</h6> <p>$850 <span class="text-danger">-4</span></p></div></div>
    </div>

    <div class="card-box mb-4">
        <h5>Top Photography Categories</h5>
        <div class="progress mb-2"><div class="progress-bar bg-info" style="width: 90%">Wedding Photography</div></div>
        <div class="progress mb-2"><div class="progress-bar bg-primary" style="width: 70%">Portrait Photography</div></div>
        <div class="progress mb-2"><div class="progress-bar bg-warning" style="width: 60%">Event Photography</div></div>
        <div class="progress mb-2"><div class="progress-bar bg-secondary" style="width: 50%">Landscape Photography</div></div>
        <div class="progress"><div class="progress-bar bg-dark" style="width: 30%">Other</div></div>
    </div>

    <div class="card-box mb-4">
        <h5>Recent Bookings</h5>
        <table class="table table-striped admin-table">
            <thead><tr>
                <th>Client</th><th>Photographer</th><th>Session Type</th><th>Date</th><th>Amount</th><th>Status</th><th>Action</th>
            </tr></thead>
            <tbody>
            <tr><td>Sarah Johnson</td><td>Alex Rivera</td><td>Family Portrait</td><td>May 15, 2023</td><td>$350</td><td class="status-confirmed">Confirmed</td><td><a href="#">Details</a></td></tr>
            <tr><td>Michael Chen</td><td>Jessica Wong</td><td>Landscape</td><td>May 16, 2023</td><td>$250</td><td class="status-pending">Pending</td><td><a href="#">Details</a></td></tr>
            <tr><td>Emily Rodriguez</td><td>David Smith</td><td>Wedding</td><td>May 20, 2023</td><td>$1200</td><td class="status-confirmed">Confirmed</td><td><a href="#">Details</a></td></tr>
            <tr><td>David Wilson</td><td>Maria Garcia</td><td>Corporate Event</td><td>May 22, 2023</td><td>$800</td><td class="status-confirmed">Confirmed</td><td><a href="#">Details</a></td></tr>
            <tr><td>Jennifer Lee</td><td>Robert Johnson</td><td>Product Photography</td><td>May 25, 2023</td><td>$450</td><td class="status-cancelled">Cancelled</td><td><a href="#">Details</a></td></tr>
            </tbody>
        </table>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="card-box">
                <h5>Top Photographers</h5>
                <table class="table admin-table">
                    <thead><tr><th>Name</th><th>Specialty</th><th>Bookings</th><th>Rating</th><th>Earnings</th></tr></thead>
                    <tbody>
                    <tr><td>Alex Rivera</td><td>Portrait, Wedding</td><td>24</td><td><span class="star">★</span> 4.9</td><td>$4,800</td></tr>
                    <tr><td>Jessica Wong</td><td>Landscape, Nature</td><td>18</td><td><span class="star">★</span> 4.8</td><td>$3,600</td></tr>
                    <tr><td>David Smith</td><td>Wedding, Events</td><td>22</td><td><span class="star">★</span> 4.7</td><td>$5,700</td></tr>
                    <tr><td>Maria Garcia</td><td>Corporate, Product</td><td>16</td><td><span class="star">★</span> 4.9</td><td>$3,950</td></tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card-box">
                <h5>Recent Users</h5>
                <table class="table admin-table">
                    <thead><tr><th>Name</th><th>Email</th><th>Joined</th><th>Bookings</th></tr></thead>
                    <tbody>
                    <tr><td>Sarah Johnson</td><td>sarah.j@example.com</td><td>May 10, 2023</td><td>3</td></tr>
                    <tr><td>Michael Chen</td><td>mchen@example.com</td><td>May 8, 2023</td><td>1</td></tr>
                    <tr><td>Emily Rodriguez</td><td>emily.r@example.com</td><td>May 5, 2023</td><td>2</td></tr>
                    <tr><td>David Wilson</td><td>d.wilson@example.com</td><td>May 2, 2023</td><td>1</td></tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>