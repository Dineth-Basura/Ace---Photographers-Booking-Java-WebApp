<%@ page import="java.io.*, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Photographers | Admin</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        body { padding: 30px; font-family: Arial, sans-serif; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }
        th { background-color: #f0f0f0; }
        .btn-delete { background-color: #dc3545; color: white; border: none; padding: 5px 10px; border-radius: 4px; }
        .btn-delete:hover { background-color: #c82333; }
    </style>
</head>
<body>
<h2>Photographers</h2>
<table>
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    <%
        String path = "D:/photoBook/photographBook/src/main/webapp/data/Photographers.txt";
        File file = new File(path);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("Photographer:")) continue;
                String[] parts = line.substring(13).trim().split(",");
                if (parts.length >= 3) {
    %>
    <tr>
        <td><%= parts[0] %></td>
        <td><%= parts[1] %></td>
        <td><%= parts[2] %></td>
        <td>
            <form action="deletePhotographer" method="post" onsubmit="return confirm('Delete photographer <%= parts[0] %>?');">
                <input type="hidden" name="username" value="<%= parts[0] %>" />
                <button class="btn-delete" type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <%
                }
            }
            reader.close();
        }
    %>
</table>
</body>
</html>