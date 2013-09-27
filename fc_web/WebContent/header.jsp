 <!-- this is header page for the website -->
 <!-- start of nav bar -->
   <div class="navbar navbar-default">
     <div class="container">
       <div class="navbar-header"> <br>
           <a class="navbar-brand" href="index">FC Sportsware Pty. Ltd.</a>
       </div>
           <ul class="nav navbar-nav"> <br>
               <li><a href="products">View Products</a></li>
               <li><a href="viewOrder">View Order</a></li>
               <li><a href="orders">Check Shopping Cart </a></li>
           </ul>
       <div class="nav navbar-nav navbar-right">
       <c:choose>
	  <c:when test="${not empty admin}">
	  		<h2> Welcome ${admin.name }</h2> 
	  		<p> <a href="admin?action=manage">Manage orders</a> | <a href="index?action=logout">Logout</a></p>
	  </c:when>
	  <c:otherwise>
          <form method="POST" action="index?action=login" id="login">
            <button class="btn btn-primary pull-right login_button" type="submit">Log in</button>
            <div class="${haserror }">
            <input type="text" class="form-control" placeholder="${invalid } username" name="username" id="username" value="${username }">
            </div>
            <div class="${haserror }">
            <input type="password" class="form-control" placeholder="${invalid } password" name="password" id="password" value="${password }">
            </div>
            <p>${invalidLogin }</p>
	      </form>
	  </c:otherwise>
	  </c:choose>
       </div>
     </div>
   </div>
   <!-- end of nav bar -->