<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register | Ace Photography</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        .checkbox-custom {
            display: flex;
            align-items: center;
            gap: 8px;
            font-size: 0.95rem;
        }
        .checkbox-custom input[type="checkbox"] {
            width: 16px;
            height: 16px;
            margin: 0;
        }
    </style>
</head>
<body class="sub_page">
<div class="hero_area">
    <header class="header_section">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg custom_nav-container ">
                <a href="index.jsp" class="navbar-brand">
                    <img src="images/logo-black.png" alt="">
                </a>
                <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <div class="d-flex ml-auto flex-column flex-lg-row align-items-center">
                        <ul class="navbar-nav">
                            <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                            <li class="nav-item active"><a class="nav-link" href="register.jsp">Register</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </header>
</div>

<section class="contact_section layout_padding">
    <div class="container">
        <div class="heading_container">
            <h2>Register</h2>
            <% if ("exists".equals(request.getParameter("error"))) { %>
            <p style="color:red;">Username or Email already exists.</p>
            <% } %>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-6">
                <form method="post" action="register">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username" required />
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password" required />
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" name="email" placeholder="Email Address" required />
                    </div>
                    <div class="form-group checkbox-custom">
                        <input type="checkbox" name="isPhotographer" id="isPhotographer" value="true">
                        <label for="isPhotographer">Register as Photographer</label>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-warning">Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<section class="container-fluid footer_section">
    <p>&copy; 2025 Ace Photography Group 06</p>
</section>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>