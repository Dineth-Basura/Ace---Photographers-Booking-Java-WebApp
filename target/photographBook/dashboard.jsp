<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav>
    <a href="index.jsp">Home</a>
    <a href="login.jsp">Logout</a>
</nav>
<h2>Welcome to Your Dashboard!</h2>
<form method="post" action="booking">
    <h3>Book a Photographer:</h3>
    Event Date: <input type="date" name="date" required><br>
    Event Type: <input type="text" name="eventType" required><br>
    <input type="submit" value="Book">
</form>
</body>
</html>