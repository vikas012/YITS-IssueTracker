angular.module('issueTrackingSystem.userModule').controller('userController',['$scope','$http','userService',function($scope,$http,userService){
	
	$scope.showLookUpForm=false;
	$scope.ldapUser={name:"",email:""};
	
	$scope.showLookForm=function(){
		
		$scope.showLookUpForm=true;
		
	}
	
	$scope.checkUser=function(){
		
		alert("Inside Check User");
		$scope.ldapUser.name=$scope.name;
		$scope.ldapUser.email=$scope.email;
		alert($scope.ldapUser.name+"---------"+$scope.ldapUser.email);
		$scope.checkUserInLdap($scope.ldapUser);
		
	}
	$scope.checkUserInLdap=function(ldapUser){
		alert("inside checkUserInLdap");
		
		
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