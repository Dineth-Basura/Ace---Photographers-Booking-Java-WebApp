<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
  <title>Book a Session</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <style>
    body { background-color: #f4f6f9; }
    .main-content { padding: 30px; flex-grow: 1; background-color: white; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.05); }
    .photographer-img { width: 70px; height: 70px; border-radius: 50%; object-fit: cover; }
    .rating { color: #f39c12; font-weight: bold; font-size: 16px; }
    .photographer-card { border: 1px solid #ddd; border-radius: 10px; padding: 15px; background: #fff; transition: 0.3s; }
    .photographer-card:hover { box-shadow: 0 4px 12px rgba(0,0,0,0.1); }
    .badge-category { font-size: 12px; margin-bottom: 4px; }
    .step-buttons .btn { margin-right: 10px; }
  </style>
</head>
<body>
<div class="d-flex">
  <jsp:include page="sidebar.jsp"/>
  <div class="main-content m-4">
    <h3>Book a Photography Session</h3>
    <div class="step-buttons my-4">
      <button class="btn btn-primary active">1. Select Photographer</button>
      <button class="btn btn-outline-secondary" disabled>2. Session Details</button>
      <button class="btn btn-outline-secondary" disabled>3. Confirm & Pay</button>
    </div>

    <%!
      public class Photographer {
        public String name;
        public String category;
        public String image;
        public double rating;
        public String location;
        public String available;

        public Photographer(String name, String category, double rating, String location, String available, String image) {
          this.name = name;
          this.category = category;
          this.rating = rating;
          this.location = location;
          this.available = available;
          this.image = image;
        }
      }
    %>

    <div class="row">
      <%
        Photographer[] photographers = {
                new Photographer("Emma Johnson", "Portrait & Wedding", 4.8, "New York", "Mon-Fri", "images/emma.jpg"),
                new Photographer("James Wilson", "Landscape & Nature", 4.9, "Seattle", "Weekends", "images/james.jpg"),
                new Photographer("Sophia Chen", "Fashion & Editorial", 4.7, "Los Angeles", "Mon-Sat", "images/sophia.jpg"),
                new Photographer("Carlos Rivera", "Event & Street", 4.6, "Chicago", "Weekdays", "images/carlos.jpg"),
                new Photographer("Lana Patel", "Travel & Wildlife", 4.9, "San Francisco", "Flexible", "images/lana.jpg"),
                new Photographer("Brian Adams", "Real Estate & Drone", 4.5, "Austin", "Mon-Fri", "images/brian.jpg")
        };

        for(Photographer p : photographers) {
      %>
      <div class="col-md-6 col-lg-4 mb-4">
        <form method="post" action="session-details.jsp">
          <div class="photographer-card">
            <div class="d-flex align-items-center mb-2">
              <img src="<%= p.image %>" class="photographer-img mr-3" alt="<%= p.name %>">
              <div>
                <h5><%= p.name %></h5>
                <span class="badge badge-info badge-category"><%= p.category %></span>
                <div class="rating">★ <%= p.rating %></div>
              </div>
            </div>
            <p class="mb-1"><strong>Location:</strong> <%= p.location %></p>
            <p class="mb-2"><strong>Availability:</strong> <%= p.available %></p>
            <input type="hidden" name="photographer" value="<%= p.name %>">
            <button type="submit" class="btn btn-sm btn-primary w-100">Select & Continue</button>
          </div>
        </form>
      </div>
      <% } %>
    </div>
  </div>
</div>
</body>
</html>