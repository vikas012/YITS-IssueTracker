angular.module('issueTrackingSystem.userModule').controller('userController',['$scope','$http','userService',function($scope,$http,userService){
	alert("in controller");
	
	
	userService.initializeSelect()
	.then(
			function(d) {
				$scope.projects=d.projects;
				alert(d.myValue);
				

			}
			
	);
	
	$scope.myFunc=function()
	{
		var projectId = angular.element(
				document.querySelector("select[id=selectId]")).val();
		alert("in onchange")
		alert(projectId);
		this.pId=projectId;
		this.project ={
				id:projectId
		}
		userService.initializeSelectAll(this.pId).then(
				function(d) {
					alert("in success all select");
					alert("In controller select all called");
					alert(d.myValue1);
					$scope.issueTypeList=d.issueType;
					$scope.priorities=d.issuePriority;
					$scope.environments=d.applicationEnvironment;

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