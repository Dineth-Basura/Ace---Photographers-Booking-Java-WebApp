<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
  public class Booking {
    public String photographer;
    public String date;

    public Booking(String p, String d) {
      photographer = p;
      date = d;
    }
  }
%>
<%
  Booking[] bookings = {
          new Booking("Emma Johnson", "2023-11-15"),
          new Booking("Marcus Brown", "2023-12-01")
  };
  request.setAttribute("userBookings", java.util.Arrays.asList(bookings));
%>
<!DOCTYPE html>
<html>
<head>
  <title>Booked Photographers</title>
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
      java.util.List<Booking> userBookings = (java.util.List<Booking>) request.getAttribute("userBookings");
      if(userBookings.isEmpty()) {
    %>
    <div class="alert alert-info mt-3">No bookings found.</div>
    <% } else {
      for(Booking b : userBookings) { %>
    <div class="card mb-3 p-3 shadow-sm">
      <h5><%= b.photographer %></h5>
      <p class="text-muted">Date: <%= b.date %></p>
      <form method="post" action="cancelBooking" class="mt-2">
        <input type="hidden" name="date" value="<%= b.date %>">
        <button class="btn btn-sm btn-danger">Cancel</button>
      </form>
    </div>
    <% } } %>
  </div>
</div>
</body>
</html>