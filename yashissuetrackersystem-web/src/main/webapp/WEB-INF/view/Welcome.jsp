<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YITS</title>

<link rel="stylesheet"
	 href="<c:url value="styles/bootstrap.css"/>" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="images/favicon.ico"/>" />
<style type="text/css">
.wrapper {
	margin: 140px 0px;
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
	background-color: #F2F6FF;
	/* margin:0px 20px; */
	padding: 20px;
	/* border-bottom-left-radius:10px;
	border-bottom-right-radius:10px; */
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

.logo{
	height: 8%;
	width: 8%;
}

.footer{
	background-color: #F2F6FF;
	/* margin:0px 20px; */
	padding: 20px;
	/* border-top-left-radius:10px;
	border-top-right-radius:10px; */
	text-align: center;
}
</style>


</head>
<body>

	<%-- <center>
 		<a href="./managerWelcome">Manager Dashboard</a> 
		<a href="./userWelcome">User Dashboard</a>
	</center> --%>
	
	<div class="header-color">
		<div class="logo ">
			<img src="<c:url value="images/logo-yash.svg"/>">
		</div>
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
		
	</div>
	<div class="footer">
			<div><strong>Copyright © 2016. YASH Technologies. All Rights Reserved.</strong></div>
		</div>
	
</body>
</html>
