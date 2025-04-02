<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.io.File" %>
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);

  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  String relativeImagePath = "images/user_profiles/" + username + ".jpg";
  String absoluteImagePath = application.getRealPath("/") + relativeImagePath;
  File profileImageFile = new File(absoluteImagePath);
  boolean hasImage = profileImageFile.exists();
  String randomQuery = "?v=" + UUID.randomUUID().toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Account Settings</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    body {
      background-color: #f5f5f5;
      font-family: Arial, sans-serif;
      padding: 40px;
    }

    .profile-container {
      max-width: 500px;
      margin: auto;
      background: white;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 0 8px rgba(0, 0, 0, 0.05);
    }

    .profile-pic {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      border: 3px solid #007bff;
      object-fit: cover;
      margin-bottom: 20px;
    }

    .btn-update {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 10px 20px;
      margin-top: 10px;
    }

    .btn-update:hover {
      background-color: #0056b3;
    }

    label {
      font-weight: bold;
    }
  </style>
</head>
<body>

<div class="profile-container">
  <h2 class="text-center mb-4">Account Settings</h2>

  <div class="text-center">
    <% if (hasImage) { %>
    <img src="<%= relativeImagePath + randomQuery %>" class="profile-pic" alt="Profile Picture">
    <% } else { %>
    <img src="images/user_profiles/default-profile.jpg" class="profile-pic" alt="Default Profile">
    <% } %>
  </div>

  <form method="post" action="updateProfile" enctype="multipart/form-data">
    <input type="hidden" name="username" value="<%= username %>">

    <div class="form-group">
      <label>New Password</label>
      <input type="password" name="newPassword" class="form-control" placeholder="Enter new password">
    </div>

    <div class="form-group">
      <label>Profile Picture</label>
      <input type="file" name="profilePic" class="form-control">
    </div>

    <div class="text-center">
      <button type="submit" class="btn btn-update">Update Profile</button>
    </div>
  </form>
</div>

</body>
</html>