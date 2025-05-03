<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String photographer = request.getParameter("photographer");
    if (photographer == null) photographer = "Unknown";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Session Details</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        body { background-color: #f4f6f9; }
        .main-content { flex-grow: 1; padding: 30px; background-color: #fff; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.05); }
        .form-section { max-width: 600px; margin: auto; }
        .form-control, .form-select { margin-bottom: 15px; }
    </style>
</head>
<body>
<div class="d-flex">
    <jsp:include page="sidebar.jsp"/>
    <div class="main-content m-4">
        <h3>Session Details for <strong><%= photographer %></strong></h3>

        <form method="post" action="confirm.jsp" class="form-section">
            <input type="hidden" name="photographer" value="<%= photographer %>">

            <label>Select Event Type</label>
            <select name="eventType" class="form-select" required>
                <option value="Wedding">Wedding</option>
                <option value="Birthday">Birthday Party</option>
                <option value="Corporate">Corporate Event</option>
                <option value="Graduation">Graduation</option>
                <option value="Other">Other</option>
            </select>

            <label>Select Package</label>
            <select name="package" class="form-select" required>
                <option value="Basic">Basic - $100 (1 Hour, 10 Edited Photos)</option>
                <option value="Standard">Standard - $200 (2 Hours, 20 Edited Photos + Prints)</option>
                <option value="Premium">Premium - $350 (Full Day, Album, Unlimited Shots)</option>
            </select>

            <label>Select Session Date</label>
            <input type="date" name="date" class="form-control" required>

            <label>Special Notes</label>
            <textarea name="notes" rows="3" class="form-control" placeholder="Any additional requests?"></textarea>

            <button class="btn btn-primary mt-2 w-100">Proceed to Payment</button>
        </form>
    </div>
</div>
</body>
</html>