<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

    <!-- Bootstrap Core CSS -->
    <link href='<spring:url value="/styles/bootstrap.min.css"></spring:url>' rel="stylesheet">

    <!-- Custom CSS -->
    <link href='<spring:url value="/styles/sb-admin.css"></spring:url>' rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href='<spring:url value="/styles/plugins/morris.css"></spring:url>' rel="stylesheet">

    <!-- Custom Fonts -->
    <link href='<spring:url value="/styles/font-awesome.min.css"></spring:url>' rel="stylesheet" type="text/css">

	<!-- angular scripts -->
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-route.js"></script>
	<%-- <script src='<spring:url value="/components/angular/routing/routing.js"></spring:url>'></script> --%>
	
	<script src='<spring:url value="/components/angular/routing/managerRouting.js"></spring:url>'></script>
	<script src='<spring:url value="/components/angular/controller/managerController.js"></spring:url>'></script>
	<script src='<spring:url value="/components/angular/service/managerService.js"></spring:url>'></script>
	

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<div ng-app="issueTrackingSystem.managerModule">
    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
           
              <jsp:include page="LeftHeader.jsp"></jsp:include>
			
            <!-- Top Menu Items -->
			   
			    <jsp:include page="RightHeader.jsp"></jsp:include>
       
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            
             <jsp:include page="ManangerSideNavBar.jsp"></jsp:include>

            <!-- /.navbar-collapse -->
        </nav>
		
        <!-- page-wrapper -->
        <div id="page-wrapper">
		
            <!-- container-fluid -->
            <div class="container-fluid" style="margin-top: 0px;padding-top: 50px;height: 100%;" ng-view="">

               

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->
		

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src='<spring:url value="/scripts/jquery.js"></spring:url>'></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<spring:url value="/scripts/bootstrap.min.css"></spring:url>"></script>

    <!-- Morris Charts JavaScript -->
    <script src='<spring:url value="/scripts/plugins/morris/raphael.min.js"></spring:url>'></script>
    <script src='<spring:url value="/scripts/plugins/morris/morris.min.js"></spring:url>'></script>
    <script src='<spring:url value="/scripts/plugins/morris/morris-data.js"></spring:url>'></script>
	<!-- footer -->
	 
	 <jsp:include page="Footer.jsp"></jsp:include>
	 
	<!-- /footer -->
	
</div>