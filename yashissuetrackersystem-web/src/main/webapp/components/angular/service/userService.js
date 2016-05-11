angular.module('issueTrackingSystem.userModule').factory('userService',['$http',function($http){
	alert(" n service");
	  return {
		  
		  initializeSelect: function() {
	          return $http.get('./getPriority')
	              .then(
	                      function(response){
	                          return response.data;
	                      }, 
	                      function(errResponse){
	                          console.error('Error while fetching users');
	                              return $q.reject(errResponse);
	                      }
	                );
	      	},
	  
	      	submitCreateIssue: function(createIssue) {
		          return $http.post('./createIssue',createIssue)
		              .then(
		                      function(response){
		                    	  alert(response.data);
		                          return response.data;
		                      }, 
		                      function(errResponse){
		                          console.error('Error while fetching users');
		                              return $q.reject(errResponse);
		                      }
		                );
		      	},
	      	
	      	 getIssuesList:function(){
	      		 alert("Khushi")
	      		 var issues=$http({
	      		 method:'GET',
	      		     url:'./defaultIssuesList' //spring controller call, use @ResponseBody
	      		 }).success(function(data){
	      		 alert(data);
	      		 $scope.issuesData = data;
	      		 return data;
	      		 })
	      		 }
	  }
}]);