angular.module('issueTrackingSystem.managerModule').controller('managerController',['$scope','$http','managerService',function($scope,$http,managerService,issueList){
	$scope.issueList=issueList.data;
	$scope.showLookUpForm=false;
	$scope.ldapUser={ldapName:"",ldapEmail:""};
	
	$scope.showLookForm=function(){
		
		$scope.showLookUpForm=true;
		
	}
	
	$scope.checkUser=function(){
		
		alert("Inside Check User");
		$scope.ldapUser.ldapName=$scope.ldapName;
		$scope.ldapUser.ldapEmail=$scope.ldapEmail;
		alert($scope.ldapUser.ldapName+"---------"+$scope.ldapUser.ldapEmail);
		$scope.checkUserInLdap($scope.ldapUser);
		
	}
	$scope.checkUserInLdap=function(ldapUser){
		alert("inside checkUserInLdap");
		alert("Name---"+ldapUser.ldapName);
		managerService.checkUserInLdap(ldapUser);
		
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

