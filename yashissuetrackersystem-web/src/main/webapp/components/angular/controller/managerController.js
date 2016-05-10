angular.module('issueTrackingSystem.managerModule').controller('managerController',['$scope','$http','managerService',function($scope,$http,managerService){

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
	/*managerService.initializeSelect()
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
			managerService.submitCreateIssue(this.createIssue);
	        .then(
	                 function(d) {
	                	 
	                 },
	                  function(errResponse){
	                      console.error('Error while fetching');
	                  }
	             );
			this.createIssue={};
		};
		
		this.getIssues = function() {
			
			var searchText = this.searchText;
			if (searchText == undefined) {
				alert("Please Enter Text!")
			}
			else{
				managerService.searchIssue(searchText)
				.then(
						function(data){
							$scope.issues=data;
						},
						 function(errResponse)
						 {
							 console.error('Error while showing default search issues');
						 }
				)
					
				
			}
		};
		
		this.change=function(){
			if(this.searchText == ""){}
			else{
				$('#datatable').remove();
			}
		};
		*/
		
	}]);

