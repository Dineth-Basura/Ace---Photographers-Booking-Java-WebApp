<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.services.PhotographerService" %>
<%@ page import="java.io.IOException" %>

<%
  // Get parameters
  String photographer = request.getParameter("photographer");
  int rating = Integer.parseInt(request.getParameter("rating"));

  // Initialize service with absolute paths
  String basePath = "D:/photoBook/photographBook/src/main/webapp/data/";
  PhotographerService photographerService = new PhotographerService(
          basePath + "photographers.txt",
          basePath + "photographer_ratings.txt"
  );

  try {
    // Update rating
    photographerService.updatePhotographerRating(photographer, rating);
%>
<!DOCTYPE html>
<html>
<head>
  <title>Rating Submitted</title>
  <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<div class="container mt-5">
  <div class="alert alert-success">
    <h2>Thank you for rating <%= photographer %>!</h2>
    <p>Your rating of <%= rating %> stars has been recorded.</p>
  </div>
</div>
</body>
</html>
<%
} catch (IOException e) {
%>
<div class="container mt-5">
  <div class="alert alert-danger">
    <h2>Error occurred while saving rating</h2>
    <p><%= e.getMessage() %></p>
  </div>
</div>
<%
    e.printStackTrace();
  }
%>
<meta http-equiv="refresh" content="3; URL=categories.jsp">