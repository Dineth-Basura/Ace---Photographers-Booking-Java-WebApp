<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.models.Booking" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Booked Photographers</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    body { background-color: #f4f6f9; }
    .main-content { padding: 30px; flex-grow: 1; }
    .card h5 { font-size: 18px; }
    .card p { margin: 0; }
  </style>
</head>
<body>
<div class="d-flex">
  <jsp:include page="sidebar.jsp"/>
  <div class="main-content">
    <h3>My Booked Photographers</h3>

    <%
      List<Booking> userBookings = (List<Booking>) request.getAttribute("userBookings");
      System.out.println("[JSP DEBUG] userBookings: " + userBookings);
      if (userBookings == null || userBookings.isEmpty()) {
    %>
    <div class="alert alert-info mt-3">No bookings found.</div>
    <%
    } else {
      for (Booking b : userBookings) {
    %>
    <div class="card mb-3 p-3 shadow-sm">
      <h5><%= b.getPhotographer() %></h5>
      <p class="text-muted mb-1">Date: <%= b.getDate() %></p>
      <p class="text-muted mb-1">Event: <%= b.getEventType() %></p>
      <p class="text-muted mb-1">Package: <%= b.getPackageName() %></p>

      <form method="post" action="cancelBooking" class="mt-2">
        <input type="hidden" name="username" value="<%= b.getUsername() %>">
        <input type="hidden" name="date" value="<%= b.getDate() %>">
        <button class="btn btn-sm btn-danger">Cancel</button>
      </form>
    </div>
    <%  } } %>

  </div>
</div>
</body>
</html>