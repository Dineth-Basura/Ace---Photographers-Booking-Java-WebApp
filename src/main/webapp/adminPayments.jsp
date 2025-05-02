<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Payments | Admin</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    .status-completed { color: #28a745; }
    .status-pending { color: #ffc107; }
    .status-failed { color: #dc3545; }
    .file-info { background: #f8f9fa; padding: 10px; margin-bottom: 20px; }
  </style>
</head>
<body>
<div class="container mt-4">
  <h2>Payment Management</h2>

  <div class="file-info">
    <strong>Reading from:</strong> D:/photoBook/photographBook/src/main/webapp/data/payments.txt<br>
    <strong>Last updated:</strong> ${payments != null ? payments.size() : 0} records loaded
  </div>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>User</th>
      <th>Photographer</th>
      <th>Package</th>
      <th>Amount</th>
      <th>Date</th>
      <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${payments}" var="payment">
      <tr>
        <td>${payment.user}</td>
        <td>${payment.photographer}</td>
        <td>${payment.packageType}</td>
        <td>$${payment.price}</td>
        <td>${payment.date}</td>
        <td class="status-${payment.status}">
            ${payment.status}
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>