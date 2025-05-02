<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.services.PhotographerService" %>
<%@ page import="com.example.models.PhotographerRating" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>

<%
    // Initialize service with absolute paths
    String basePath = "D:/photoBook/photographBook/src/main/webapp/data/";
    PhotographerService photographerService = new PhotographerService(
            basePath + "photographers.txt",
            basePath + "photographer_ratings.txt"
    );

    try {
        // Get sorted photographers with ratings
        List<PhotographerRating> photographerRatings = photographerService.getSortedPhotographerRatings();
        request.setAttribute("photographerRatings", photographerRatings);
    } catch (IOException e) {
        e.printStackTrace();
        request.setAttribute("error", "Error loading photographer ratings: " + e.getMessage());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Top Rated Photographers</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        body { background: #000000; }
        .main-content { flex-grow: 1; padding: 30px; }
        .photographer-card {
            background: white;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            margin-bottom: 20px;
        }
        .rating-stars input { display: none; }
        .rating-stars label {
            font-size: 20px;
            color: #ccc;
            cursor: pointer;
        }
        .rating-stars input:checked ~ label,
        .rating-stars label:hover,
        .rating-stars label:hover ~ label {
            color: #f39c12;
        }
        .photographer-card img {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 50%;
            margin-bottom: 10px;
        }
        .btn-delete {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="d-flex">
    <jsp:include page="sidebar.jsp"/>
    <div class="main-content">
        <h3>Top Rated Photographers (Sorted by Rating)</h3>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="row">
            <c:forEach var="photog" items="${photographerRatings}">
                <div class="col-md-4">
                    <div class="photographer-card">
                        <%
                            // Generate image filename from photographer name
                            String imgName = ((PhotographerRating)pageContext.getAttribute("photog")).getName()
                                    .toLowerCase().replace(" ", "-") + ".jpg";
                            String imgPath = "images/profiles/" + imgName;
                            String defaultImg = "images/profiles/default-profile.jpg";

                            // Check if image exists
                            String imgToUse = new java.io.File(
                                    "D:/photoBook/photographBook/src/main/webapp/" + imgPath).exists()
                                    ? imgPath : defaultImg;
                        %>
                        <img src="<%= imgToUse %>" alt="${photog.name}">
                        <h5>${photog.name}</h5>
                        <div>
                            <c:forEach begin="1" end="5" var="i">
                                <c:choose>
                                    <c:when test="${i <= photog.rating}">★</c:when>
                                    <c:otherwise>☆</c:otherwise>
                                </c:choose>
                            </c:forEach>
                            (${photog.rating})
                        </div>
                        <form method="post" action="submitRating.jsp">
                            <input type="hidden" name="photographer" value="${photog.name}">
                            <div class="rating-stars d-flex justify-content-center my-2">
                                <input type="radio" id="${photog.name}-star5" name="rating" value="5" required>
                                <label for="${photog.name}-star5">★</label>
                                <input type="radio" id="${photog.name}-star4" name="rating" value="4">
                                <label for="${photog.name}-star4">★</label>
                                <input type="radio" id="${photog.name}-star3" name="rating" value="3">
                                <label for="${photog.name}-star3">★</label>
                                <input type="radio" id="${photog.name}-star2" name="rating" value="2">
                                <label for="${photog.name}-star2">★</label>
                                <input type="radio" id="${photog.name}-star1" name="rating" value="1">
                                <label for="${photog.name}-star1">★</label>
                            </div>
                            <button class="btn btn-sm btn-outline-primary">Submit Rating</button>
                        </form>

                        <!-- Delete Rating Button -->
                        <form method="post" action="DeleteRatingServlet" class="mt-2">
                            <input type="hidden" name="photographer" value="${photog.name}">
                            <button class="btn btn-sm btn-outline-danger btn-delete">Delete Rating</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>