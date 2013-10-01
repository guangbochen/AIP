<!-- products page displays a list of products offered by fcSportsware-->
<%@ page language="java" contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>FC Sportsware</title>
    <!-- inner class css -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/style.css">
</head>
<body>
<!-- include header -->
<%@ include file="header.jsp" %>

<div class="container">
	<!--show navi title -->
	<c:choose>
		<c:when test="${empty category}">
		    <p> <a href="index">Home</a> >> View Products </p>
		</c:when>
		<c:otherwise>
		    <p> <a href="index">Home</a> >> <a href="products">View Products</a> >> ${category} </p>
		</c:otherwise>
	</c:choose>
    <hr>
    
	<div class="p_filter">
		<!--Shows the Shopping cart -->
	    <div class="panel panel-success pull-right p_cart">
		    <div class="panel-heading">
		        <h1 class="panel-title">Your Shopping Cart</h1>
	        </div>
	        <div class="panel-body">
		    	<a href="orders"><img alt="shoppingCart" src="assets/img/shoppingcart.gif" />Check my shopping cart</a>
	        </div>
	    </div>
		<!--Shows Category filter -->
	    <div class="filter"> 
	    <h4>Category Filter</h4>
	    <form method="POST" action="products?action=category">
		    <select class="selection" name="category">
					<option value="All" > All</option>
		    	<c:forEach var="cate" items="${cateList}">
					<option value="${cate}" >${cate}</option>
			  	</c:forEach>
			</select>
			<input class="btn btn-default" type="submit" value="Fetch">
		</form>
		</div> 
	</div>
	
	<!--display available products -->
	<div class="row">
	<c:forEach var="p" items="${productList}">
  		<div class="col-sm-6 col-md-3"><br>
    		<div class="thumbnail">
      			<img data-src="holder.js/300x200" alt=" img 300x200" src="assets/img/products/${p.category}${p.code}.jpg" style="width: 300px; height: 200px;">
      			<div class="caption">
	        		<h4>Category: ${p.category}</h4>
	        		<h4>Code: ${p.code}</h4>
	        		<p class="p_desc">${p.description}</p>
	        		<form method="POST" action="products?action=order">
	        			<strong class="p_price">$ ${p.price}</strong>
			            <input type="hidden" name="id" value="${p.id }" />
			            <input type="hidden" name="category" value="${p.category }" />
			            <input type="hidden" name="code" value="${p.code }" />
			            <input type="hidden" name="description" value="${p.description }" />
			            <input type="hidden" name="price" value="${p.price }" />
			            <input type="hidden" name="quantity" value="1" />
			            <input type="submit" value="Add to cart" class="btn btn-danger addButton"/>
	        		</form>
      			</div>
    		</div>
  		</div>
  	</c:forEach>
	</div>
    
   	<%-- displays page paging --%> 
   	<div class="container">
    <ul class="pagination pull-right">
    <%--For displaying Previous link except for the 1st page --%>
    <c:choose>
    	<c:when test="${currentPage != 1}">
			<c:choose>
				<c:when test="${empty category}">
					<li><a href="products?action=products&page=${currentPage - 1}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="products?action=category&category=${category }&page=${currentPage - 1}">Previous</a></li>
				</c:otherwise>
			</c:choose>
	    </c:when>
	    <c:otherwise>
	        <li class="disabled"><a>Previous</a></li>
	    </c:otherwise>
    </c:choose>
    <%--For displaying Page numbers. The when condition does not display a link for the current page--%>
    <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="active"><a>${i}</a></li>
            </c:when>
            <c:otherwise>
			    <c:choose>
					<c:when test="${empty category}">
		                <li><a href="products?action=products&page=${i}">${i}</a></li>
					</c:when>
					<c:otherwise>
		                <li><a href="products?action=category&category=${category }&page=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <%--For displaying Next link --%>
    <c:choose>
	    <c:when test="${currentPage lt noOfPages}">
		    <c:choose>
				<c:when test="${empty category}">
			        <li><a href="products?action=products&page=${currentPage + 1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="products?action=category&category=${category }&page=${currentPage + 1}">Next</a></li>
				</c:otherwise>
			</c:choose>
	    </c:when>
	    <c:otherwise>
	        <li class="disabled"><a>Next</a></li>
	    </c:otherwise>
    </c:choose>
    </ul><%-- end of page pagination --%> 
    </div>
    
</div> <!-- end of body container -->

	<!-- include footer -->
	<%@ include file="footer.jsp" %>
</body>
</html>