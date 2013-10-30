<!-- this is welcome page of fc_sportsware that shows a welcome message and relevant links -->
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
        <h2>Our History</h2>
        <p>
			FC Sportsware Pty can trace its history back to 1985 when the first store was opened in Bankstown NSW.
			
			Since then, Fc_sportsware has grown to become the leading retailer of sporting and leisure equipment, apparel and footwear in Australia.
			
			We offer our customers an incomparable range of the biggest brands and latest products.
			
			As a Group, we have more than 90 stores across Australia and employ over 4500 employees.
			
			At fc_sportsware, we have a genuine passion for a healthy lifestyle and providing our customers with what they need. Our culture is based on delivering outstanding Customer Focus as driven by our core values.
			
			We are part of Sports Retail, which is a proud division of the Super Retail Group.
        </p>
        <p>
          <a class="btn btn-lg btn-primary" href="products">View Products</a>
        </p>
      </div>
	</div> <!-- end of body container -->
	
	
<!-- import page footer -->
<%@ include file="footer.jsp" %>
</body>
</html>