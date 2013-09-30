<!-- this is welcome page for the website that shows links and all customer information -->
<%@ page language="java" contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>FC Sportsware</title>
    <!-- inner class css -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/style.css">
</head>
<body>
<!-- import page header -->
<%@ include file="header.jsp" %>


    <div class="container body-wrapper">
    	<br> <br> <br> <br>
        <div class="jumbotron">
        <h1>Welcome to FC Sportsware Pty. Ltd.</h1>
        <p>FC Sportsware Pty. Ltd. has decided to launch an Online shop front to sell its range of soccer sportsware and equipment which they expect to both expand their business and cut costs associated with traditional "bricks and mortar" shop fronts. The Online shop front will sell a range of products including jerseys, shorts, socks, balls, goals and nets.</p>
        <p>
          <a class="btn btn-lg btn-primary" href="products">View Products</a>
        </p>
      </div>
	</div> <!-- end of body container -->
	
	
<!-- import page footer -->
<%@ include file="footer.jsp" %>
</body>
</html>