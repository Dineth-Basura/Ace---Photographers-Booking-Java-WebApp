<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String photographer = request.getParameter("photographer");
    String rating = request.getParameter("rating");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Rating Submitted</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <meta http-equiv="refresh" content="3; URL=dashboard.jsp">
    <style>
        body { background: #f4f6f9; }
        .card {
            max-width: 500px;
            margin: 80px auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            text-align: center;
        }
    </style>
</head>
<body>
<div class="card">
    <h4 class="text-success">Thank you!</h4>
    <p>You rated <strong><%= photographer %></strong> with <strong><%= rating %> star(s)</strong>.</p>
    <p class="text-muted">Redirecting to dashboard...</p>
</div>
</body>
</html>