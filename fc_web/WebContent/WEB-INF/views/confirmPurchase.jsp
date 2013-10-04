<!-- this pages allows customer to confirm their order purchasing -->
<%@ page language="java" contentType="text/html" import="" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>FC Sportsware</title>
    <!-- inner class css -->
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/style.css">
</head>
<body>


    <div class="container confirm-dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">Purchasing My Order</h4>
	      </div>
	      <div class="modal-body">
	        <p> Are you sure you want to purchasing the order ? </p>
	      </div>
		    <div class="modal-footer">
		        <a href="purchase?action=cancle"><button type="button" class="btn btn-default" >Cancel</button></a>
		        <a href="purchase?action=process"><button type="button" class="btn btn-primary" >Proceed</button></a>
	        </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
        
	</div> <!-- end of body container -->
	
	
</body>
</html>
