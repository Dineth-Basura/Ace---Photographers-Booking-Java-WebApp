<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);

  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  String error = request.getParameter("error");
  String updated = request.getParameter("updated");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Account Settings</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    body {
      background-color: #f4f6f9;
      font-family: 'Segoe UI', sans-serif;
    }
    .profile-box {
      max-width: 500px;
      margin: 50px auto;
      background: #fff;
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 0 10px rgba(0,0,0,0.08);
    }
    h2 {
      text-align: center;
      font-weight: 600;
      margin-bottom: 25px;
    }
    .profile-pic {
      width: 120px;
      height: 120px;
      object-fit: cover;
      border-radius: 50%;
      display: block;
      margin: 0 auto 20px auto;
      border: 3px solid #007bff;
    }
    .form-group label {
      font-weight: 500;
    }
    .btn-primary, .btn-danger {
      width: 100%;
    }
    .alert {
      padding: 10px;
      font-size: 14px;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>

<div class="profile-box">
  <h2>Account Settings</h2>

  <!-- Serve image via servlet for real-time updates -->
  <img src="userProfileImage?user=<%= username %>&ts=<%= System.currentTimeMillis() %>" class="profile-pic" alt="Profile Picture">

  <% if ("incorrectOldPassword".equals(error)) { %>
  <div class="alert alert-danger">Incorrect old password.</div>
  <% } else if ("mismatch".equals(error)) { %>
  <div class="alert alert-warning">New passwords do not match.</div>
  <% } else if ("updated".equals(updated)) { %>
  <div class="alert alert-success">Profile updated successfully!</div>
  <% } %>

  <form action="updateProfile" method="post" enctype="multipart/form-data">
    <input type="hidden" name="username" value="<%= username %>">

    <div class="form-group">
      <label>Old Password</label>
      <input type="password" name="oldPassword" class="form-control" required>
    </div>

    <div class="form-group">
      <label>New Password</label>
      <input type="password" name="newPassword" class="form-control">
    </div>

    <div class="form-group">
      <label>Confirm New Password</label>
      <input type="password" name="confirmPassword" class="form-control">
    </div>

    <div class="form-group">
      <label>Profile Picture</label>
      <input type="file" name="profilePic" class="form-control-file">
    </div>

    <button type="submit" class="btn btn-primary">Update Profile</button>
  </form>

  <!-- Redirect to Confirm Deletion Page -->
  <form action="confirmDelete.jsp" method="get">
    <button type="submit" class="btn btn-danger mt-3">Delete My Account</button>
  </form>

</div>

</body>
</html>