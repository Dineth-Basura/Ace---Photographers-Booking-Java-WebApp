<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment History</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        body { background: #f4f6f9; }
        .payment-card {
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<div class="d-flex">
    <jsp:include page="sidebar.jsp"/>
    <div class="main-content m-4">
        <h3>Your Payment History</h3>

        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <c:choose>
            <c:when test="${empty payments}">
                <div class="alert alert-info">No payment history found</div>
            </c:when>
            <c:otherwise>
                <c:forEach var="payment" items="${payments}">
                    <div class="payment-card">
                        <h5>${payment.photographer}</h5>
                        <p><strong>Package:</strong> ${payment.packageType} ($${payment.price})</p>
                        <p><strong>Event Type:</strong> ${payment.eventType}</p>
                        <p><strong>Session Date:</strong> ${payment.date}</p>
                        <p><strong>Booked On:</strong>
                            <c:catch var="parseError">
                                <fmt:formatDate value="${Long.parseLong(payment.timestamp) * 1000}"
                                                pattern="MMM dd, yyyy hh:mm a"/>
                            </c:catch>
                            <c:if test="${not empty parseError}">
                                ${payment.timestamp}
                            </c:if>
                        </p>
                        <form method="post" action="deletePayment">
                            <input type="hidden" name="timestamp" value="${payment.timestamp}">
                            <button type="submit" class="btn btn-sm btn-outline-danger">Delete</button>
                        </form>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>