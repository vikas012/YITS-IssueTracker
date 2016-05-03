 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>



<style type="text/css">
	
	.container{
	
		width: 200px;
		float: center;
	}
	
	.input-group{
		width: 300px;
		float: center;
		margin-left: 350px;
	}
	
	#heading{
		background-color: darkcyan;
		color: white;
		 font-size: 18px;
		  font-family: "Times New Roman", Times, serif;
	}
	
	.btn-primary{
		background-color: darkcyan !important;
	}
	
</style>
</head>
<body>


<form>
	

	
	<div class="input-group">
            <input type="text" class="form-control" ng-model="mc.searchText" ng-change="mc.change()" placeholder="Search" name="srch-term" id="srch-term">
            <div class="input-group-btn">
                <button class="btn btn-primary" type="button" ng-click="mc.getIssues()"><i class="glyphicon glyphicon-search"></i>&nbsp; Search</button>
            </div>
        </div>
	
	
	

		<br>
		<br>
		<br>
		
		
		<table  class="table table-striped table-bordered table-hover">
			<thead class="thead-inverse">
				<tr  id="heading">
					<th>Select</th>

					<th>Issue Type</th>
					<th>Issue Creation Date</th>
					<th>Issue Priority</th>
					<th>Issue Summary</th>
					<th>Assigned To</th>
					<th>Issue Status</th>
					<th>Project Name</th>
					<th>Assigned Status</th>
					<th colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${issueList}" var="issue">
			<tr id="datatable">
				<td><input id='radio' type='radio' value="${issue.issueDetailId} "></td>

				<td>${issue.issueType.issueType}</td>
				<td>${issue.issueCreationDate}</td>
				<td>${issue.issuePriority.issuePriorityType}</td>
				<td>${issue.issueSummary}</td>
				<td>${issue.user.userName}</td>
				<td>${issue.issueStatus.issueStatusType}</td>
				<td>${issue.project.projectName}</td>
				<td>${issue.issueAssignedStatus.issueAssignmentStatus}</td>
				<td><input type="button" value="Feed" class="btn btn-primary"></td>
				<td><input type="button" value="View Details" class="btn btn-primary" ng-click="viewIssue()"></td>
			</tr>
			</c:forEach>
			</tbody>
	
			<tr  ng-repeat="issue in issues">
				<td><input type="radio" value={{issue.issueDetailId}} ></td>

				<td>{{issue.issueType.issueType}}</td>
				<td>{{issue.issueCreationDate}}</td>
				<td>{{issue.issuePriority.issuePriorityType}}</td>
				<td>{{issue.issueSummary}}</td>
				<td>{{issue.user.userName}}</td>
				<td>{{issue.issueStatus.issueStatusType}}</td>
				<td>{{issue.project.projectName}}</td>
				<td>{{issue.issueAssignedStatus.issueAssignmentStatus}}</td>
				<td><input type="button" value="Feed" class="btn btn-primary"></td>
				<td><input type="button" value="View Details" class="btn btn-primary" ng-click="viewIssue()"></td>
			</tr>

			<tbody>

			</tbody>
		</table>
		
		
</form>
</body>
</html>