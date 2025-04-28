<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String photographer = request.getParameter("photographer");
    String sessionDate = request.getParameter("date");
    String selectedPackage = request.getParameter("package");
    String eventType = request.getParameter("eventType");
    String notes = request.getParameter("notes");

    String price = "100";
    if ("Standard".equals(selectedPackage)) price = "200";
    else if ("Premium".equals(selectedPackage)) price = "350";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm & Pay</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        body { background-color: #f4f6f9; }
        .main-content { flex-grow: 1; padding: 30px; background-color: white; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.05); max-width: 700px; margin: auto; }
        .card-input { background: #eee; padding: 20px; border-radius: 10px; }
        .form-control { margin-bottom: 15px; }
    </style>
</head>
<body>
<div class="main-content mt-4">
    <h4>Confirm Your Booking</h4>
    <p><strong>Photographer:</strong> <%= photographer %></p>
    <p><strong>Event Type:</strong> <%= eventType %></p>
    <p><strong>Package:</strong> <%= selectedPackage %></p>
    <p><strong>Date:</strong> <%= sessionDate %></p>
    <p><strong>Total:</strong> $<%= price %></p>

    <form action="paymentSuccess" method="post" class="card-input mt-4">
        <input type="hidden" name="photographer" value="<%= photographer %>">
        <input type="hidden" name="package" value="<%= selectedPackage %>">
        <input type="hidden" name="date" value="<%= sessionDate %>">
        <input type="hidden" name="price" value="<%= price %>">
        <input type="hidden" name="eventType" value="<%= eventType %>">
        <input type="hidden" name="notes" value="<%= notes %>">

        <label>Cardholder Name</label>
        <input type="text" name="cardName" class="form-control" placeholder="John Doe" required>

        <label>Card Number</label>
        <input type="text" name="cardNumber" class="form-control" placeholder="XXXX-XXXX-XXXX-1234" required maxlength="19">

        <div class="row">
            <div class="col-md-6">
                <label>Expiry Date</label>
                <input type="text" name="expiry" class="form-control" placeholder="MM/YY" required>
            </div>
            <div class="col-md-6">
                <label>CVV</label>
                <input type="text" name="cvv" class="form-control" placeholder="123" maxlength="4" required>
            </div>
        </div>

        <button class="btn btn-success mt-3 w-100">Pay & Confirm</button>
    </form>
</div>
</body>
</html>