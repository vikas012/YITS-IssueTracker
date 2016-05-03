angular.module('issueTrackingSystem.managerModule').factory('managerService',['$http',function($http){
	
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
	      	
	      	
	      	searchIssue:function(searchText){
				return $http.get(
						'./getIssues/'
						+ searchText)
			 	.then(
                	 function(response){
                     	return response.data;
                	 }, 
                	 function(errResponse){
                     	console.error('Error while searching issues');
                     	return $q.reject(errResponse);
                 	 }
         		);
		}
	      	
	  }
}]);