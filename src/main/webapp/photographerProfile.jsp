<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("photographerUsername");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String relativeImagePath = "images/profiles/" + username + ".jpg";
    String absoluteImagePath = application.getRealPath("/") + relativeImagePath;

    java.io.File profileImageFile = new java.io.File(absoluteImagePath);
    boolean hasImage = profileImageFile.exists();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <style>
        body { background-color: #f5f5f5; }
        .sidebar {
            width: 240px;
            background-color: #121212;
            color: white;
            height: 100vh;
            position: fixed;
            padding: 30px 20px;
        }
        .sidebar h3 { color: #ffc107; font-weight: bold; }
        .sidebar a {
            color: white;
            display: block;
            margin: 20px 0;
            text-decoration: none;
        }
        .sidebar a:hover { color: #ffc107; }
        .main-content {
            margin-left: 260px;
            padding: 30px;
        }
        .profile-box {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 8px rgba(0,0,0,0.05);
            max-width: 600px;
        }
        .profile-box h4 { margin-bottom: 20px; }
        .form-control { margin-bottom: 15px; }
        .btn-update {
            background-color: #343a40;
            color: white;
        }
        .btn-update:hover { background-color: #212529; }
        .profile-pic {
            width: 120px;
            height: 120px;
            object-fit: cover;
            border-radius: 50%;
            border: 3px solid #ffc107;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h3><img src="images/logo-black.png" style="width: 30px; margin-right: 5px;"> Pro <span style="color: white;">Photography</span></h3>
    <a href="photographerDashboard.jsp">Dashboard</a>
    <a href="#">Bookings</a>
    <a href="#">Clients</a>
    <a href="#">Payments</a>
    <a href="photographerProfile.jsp">My Profile</a>
    <a href="logout.jsp" style="color: #dc3545;">Logout</a>
</div>

<div class="main-content">
    <div class="profile-box">
        <h4>My Profile</h4>

        <% if (hasImage) { %>
        <img src="<%= relativeImagePath %>?ts=<%= System.currentTimeMillis() %>" alt="Profile Picture" class="profile-pic">
        <% } else { %>
        <img src="images/profiles/default-profile.jpg" alt="Default Profile" class="profile-pic">
        <% } %>

        <form action="updatePhotographerProfile" method="post" enctype="multipart/form-data">
            <label>Username</label>
            <input type="text" name="username" class="form-control" value="<%= username %>" readonly>

            <label>New Password</label>
            <input type="password" name="newPassword" class="form-control" placeholder="Enter new password">

            <label>Profile Picture</label>
            <input type="file" name="profilePic" class="form-control">

            <button type="submit" class="btn btn-update mt-3">Update Profile</button>
        </form>
    </div>
</div>

</body>
</html>