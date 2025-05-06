<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Photographer</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body style="background-color:#f8f9fa;">
<jsp:include page="sidebar.jsp" />
<div class="container p-5">
    <h3>Add Photographer</h3>
    <form method="post" action="photographer.jsp">
        <div class="form-group">
            <label for="name">Photographer Name</label>
            <input type="text" name="name" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="rating">Rating (1-5)</label>
            <input type="number" name="rating" class="form-control" min="1" max="5" required>
        </div>
        <div class="form-group">
            <label for="eventType">Event Type</label>
            <select name="eventType" class="form-control" required>
                <option value="">-- Select Event Type --</option>
                <option value="Wedding">Wedding</option>
                <option value="Portrait">Portrait</option>
                <option value="Fashion">Fashion</option>
                <option value="Travel">Travel</option>
                <option value="Event">Event</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add Photographer</button>
    </form>
</div>
</body>
</html>