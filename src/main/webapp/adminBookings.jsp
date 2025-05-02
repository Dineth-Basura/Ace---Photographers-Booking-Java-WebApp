<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bookings | Admin</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    .text-success { color: #28a745; }
    .text-warning { color: #ffc107; }
    .text-danger { color: #dc3545; }
    .file-info { background: #f8f9fa; padding: 10px; margin-bottom: 20px; }
  </style>
</head>
<body>
<div class="container mt-4">
  <h2>All Bookings</h2>

  <div class="file-info">
    <strong>Reading from:</strong> D:/photoBook/photographBook/src/main/webapp/data/bookings.txt<br>
    <strong>Last updated:</strong> ${bookings != null ? bookings.size() : 0} records loaded
  </div>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>Date</th>
      <th>User</th>
      <th>Photographer</th>
      <th>Event</th>
      <th>Package</th>
      <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookings}" var="booking">
      <tr>
        <td>${booking.date}</td>
        <td>${booking.user}</td>
        <td>${booking.photographer}</td>
        <td>${booking.eventType}</td>
        <td>${booking.packageType}</td>
        <td class="text-${booking.status == 'Approved' ? 'success' :
                            booking.status == 'Rejected' ? 'danger' : 'warning'}">
            ${booking.status}
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>