<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
#rcorners2 {
    border-radius: 25px;
    border: 2px solid #73AD21;
    padding: 20px; 
    width: 800px;
    height: 100px; 
}



</style>

</head>
<body ng-controller="userController as uc">
	<center>
		<div class="container">
				<label><input type="radio" name="optradio" ng-click="showLookForm()">Yash</label>
			    &nbsp;&nbsp;&nbsp;&nbsp;   
			    &nbsp;&nbsp;&nbsp;&nbsp;   
			    &nbsp;&nbsp;&nbsp;&nbsp;
			    &nbsp;&nbsp;&nbsp;&nbsp;   
			    &nbsp;&nbsp;&nbsp;&nbsp;   
			    &nbsp;&nbsp;&nbsp;&nbsp;
			   	<label><input type="radio" name="optradio" ng-click="">Non-Yash</label>
		</div>
	</center>
	<br>
	<br>
	<br>
	<br> 			
	<center>
	<div class="container" ng-show="showLookUpForm" id="rcorners2">
	  <form class="form-inline" role="form">
	    <div class="form-group">
	      <label for="name">Name:</label>
	      <input type="text" class="form-control" id="name" placeholder="Enter name" ng-model="name">
	    </div>
	     &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
	    <div class="form-group">
	      <label for="email">Email:</label>
	      <input type="email" class="form-control" id="email" placeholder="Enter email" ng-model="email">
	    </div>
	    &nbsp;&nbsp;&nbsp;&nbsp; 
	    &nbsp;&nbsp;&nbsp;&nbsp; 
	    <button type="submit" class="btn btn-success" ng-click="checkUser()">LookUp</button>
	  </form>
	</div>
	</center>
</body>
</html>