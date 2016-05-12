angular.module('issueTrackingSystem.userModule').controller('userController',['$scope','$http','userService',function($scope,$http,userService){
	alert("in controller");
	
	userService.initializeSelect()
	.then(
			function(d) {
				$scope.projects=d;
				alert(d);
				

				//alert(d.data.id);
				//alert(d.data.name);
				



			}
			
	);

	$scope.myFunc=function()
	{
		var index = angular.element(
				document.querySelector("select[id=selectId]")).val();
		alert("in onchange")
		alert(index);
		userService.initializeSelect().then(
				function(d) {
					$scope.projects=d;
					alert(d);
					

				});
	}


	this.add = function() {
		alert("hello");
		/*var projectId = this.createIssue.project.projectId;
		var issueId = this.createIssue.issueType.issueId;
		var issuePriorityId = this.createIssue.issuePriority.issuePriorityId;
		var summary = this.createIssue.summary;
		var component = this.createIssue.component;
		var affectedVersion = this.createIssue.affectedVersion;
		var applicationEnvironment = this.createIssue.applicationEnvironment;
		var description = this.createIssue.description;*/
		var summary =this.createIssue.summary;
		var component=this.createIssue.component;
		var affectedVersion=this.createIssue.affectedVersion;
		var description=this.createIssue.description;
		
		var project={
				"id":1
		}
		
		var applicationIssueType={
				"id":1
		}
		
		var applicationIssuePriority={
				"id":1
		}
		 var applicationEnvironment={
				"id":1
		}
		
		
		
		
		var formData={
				"project":project,
				"applicationIssueType":applicationIssueType,
				"applicationIssuePriority":applicationIssuePriority,
				"summary":summary,
				"component":component,
				"affectedVersion":affectedVersion,
				"applicationEnvironment":applicationEnvironment,
				"description":description
		};
		alert(formData);
		
		
		userService.submitCreateIssue(formData)
		.then(
				function(formData) {
						alert("REgistered");
				},
				function(errResponse)
				{
				console.error('Error while searching issues');
				});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		/*issueService returns list to populate drop-down*/
	/*userService.initializeSelect()
        .then(
                 function(d) {
                	 
                	 retrieve and assign value from list
                	 angular.forEach(d,function(value,key){
            			 switch(key){
            			 
            			 	case 0:
            					 $scope.priorities=value;
            					 break;
            			 	case 1:
            			 		$scope.assigneeList=value;
            			 		break;
            			 	case 2:
            			 		$scope.issueTypeList=value;
            			 		break;
            			 	case 3:
            			 		$scope.projects=value;
            			 		break;
            			 }
            		 });
                	 
                 },
                  function(errResponse){
                      console.error('Error while fetching');
                  }
             );
		
		this.createIssue={};
		this.add=function(){
			
			// call service to persist in db
			userService.submitCreateIssue(this.createIssue);
	        .then(
	                 function(d) {
	                	 
	                 },
	                  function(errResponse){
	                      console.error('Error while fetching');
	                  }
	             );
			this.createIssue={};
		};*/
	}]);