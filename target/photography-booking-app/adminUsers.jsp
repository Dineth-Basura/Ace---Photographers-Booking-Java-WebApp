<%@ page import="java.io.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Admin - Manage Users</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    body { padding: 30px; background-color: #f5f5f5; font-family: 'Poppins', sans-serif; }
    .table { background: white; box-shadow: 0 0 10px rgba(0,0,0,0.05); }
    h2 { margin-bottom: 30px; }
  </style>
</head>
<body>

<h2>Manage Users</h2>

<table class="table table-striped">
  <thead>
  <tr><th>Username</th><th>Password</th><th>Email</th><th>Action</th></tr>
  </thead>
  <tbody>
  <%
    String usersFilePath = "D:/photoBook/photographBook/src/main/webapp/data/users.txt";
    File userFile = new File(usersFilePath);
    if (userFile.exists()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
          String[] parts = line.split(",");
          if (parts.length >= 3) {
  %>
  <tr>
    <td><%= parts[0] %></td>
    <td><%= parts[1] %></td>
    <td><%= parts[2] %></td>
    <td>
      <form method="post" action="deleteUser" style="display:inline;" onsubmit="return confirm('Delete user <%= parts[0] %>?');">
        <input type="hidden" name="username" value="<%= parts[0] %>">
        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
      </form>
    </td>
  </tr>
  <%
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  %>
  </tbody>
</table>

</body>
</html>