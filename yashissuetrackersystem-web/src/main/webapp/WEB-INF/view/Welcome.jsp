<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YITS</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style type="text/css">
.wrapper {
	margin-top: 105px;
}

.form-signin {
	max-width: 380px;
	padding: 15px 35px 45px;
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, 0.1);
	.
	form-signin-heading
	,
	.checkbox
	{
	margin-bottom
	:
	30px;
}

.checkbox {
	font-weight: normal;
}

.form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	@
	include
	box-sizing(border-box);
	&:
	focus
	{
	z-index
	:
	2;
}

}
input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

input[type="password"] {
	margin-bottom: 20px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

}
#wrap-color {
	box-shadow: 0 0 18px black;
	border-radius: 9px;
}

.header-color {
	background-color: #337ab7;
	margin-top: 0px;
	padding-bottom: 23px;
}

.h2-tag {
	padding-top: 22px;
	margin: 0;
	padding-left: 37px;
	color: white;
}

.h4-color {
	color: white;
	padding-left: 539px;
}
</style>


</head>
<body>

	<%-- <center>
 		<a href="./managerWelcome">Manager Dashboard</a> 
		<a href="./userWelcome">User Dashboard</a>
	</center> --%>
	
	<div class="header header-color">
		<h2 class="h2-tag">YITS</h2>
	</div>

	<div class="container">
		<div class="wrapper">
			<center>
					<c:if test="${not empty errorMessage}">
				   		<strong style="color:red"><c:out value="${errorMessage}"/></strong>
					</c:if>
			</center>
			<form class="form-signin" id="wrap-color" action="./loginUser" method="post">
				<h2 class="form-signin-heading">Login</h2>
				<div class="form-group">
					<label for="email">Email:</label> <input type="email"class="form-control" id="email" placeholder="Enter email"name="username" required>
					
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password" class="form-control" id="pwd" placeholder="Enter password"name="password" required>
					
				</div>
				<div class="checkbox">
					<label><input type="checkbox"> Remember me</label>
				</div>
				<button type="submit" class="btn btn-success">Submit</button>

			</form>
		</div>
		<!-- <div class="footer">
			<h5 class="h4-color">YASH Technologies. All Rights Reserved</h5>
		</div> -->
	</div>
	
	
</body>
</html>
