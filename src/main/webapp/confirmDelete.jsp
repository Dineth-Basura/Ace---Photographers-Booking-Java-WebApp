<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Confirm Account Deletion</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    body {
      background-color: #f4f6f9;
      font-family: 'Segoe UI', sans-serif;
    }
    .confirmation-box {
      max-width: 400px;
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
    .alert {
      padding: 10px;
      font-size: 14px;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>

<div class="confirmation-box">
  <h2>Are you sure you want to delete your account?</h2>

  <form action="deleteProfile" method="post" onsubmit="return confirm('This action is irreversible. Do you really want to delete your account?');">
    <input type="hidden" name="username" value="<%= username %>">
    <button type="submit" class="btn btn-danger">Confirm Deletion</button>
  </form>

  <a href="profileUpdate.jsp" class="btn btn-secondary mt-3">Cancel</a>
</div>

</body>
</html>