angular.module('issueTrackingSystem.userModule').factory('userService',['$http',function($http,$q){
	  return {
		  
		  initializeSelect: function() {
	          return $http.get('../getProjects')
	              .then(
	                      function(response){
	                    	  //alert("in service for projects");
	                          return response.data;
	                      }, 
	                      function(errResponse){
	                          console.error('Error while fetching users');
	                              return $q.reject(errResponse);
	                      }
	                );
	      	},

	  
	      	

	      	initializeSelectAll:function(projectId){
	      		alert("in service Select All");
	      		alert(projectId);
	      		return $http.get('../getAllSelectFields/'+projectId)
	              .then(
	                      function(response){
	                    	 // alert("in service for all");
	                          return response.data;
	                      }, 
	                      function(errResponse){
	                          console.error('Error while fetching users');
	                              return $q.reject(errResponse);
	                      }
	                );
	      	},
	      	submitCreateIssue: function(createIssue) {
		          return $http.post('../createIssue',createIssue)

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

	      	EditIssue:function(){
	      		return $http.get('./defaultIssuesList')
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
	  
	      	

	      	
	      	searchByIssueType:function(type){
	  			
	  			return $http.get('../searchIssue/'+type)
	  				.then(
	  						function(response){
	  							return response.data;
	  						},
	  						function(errResponse){
									console.error('Error while fetching users');
									return $q.reject(errResponse);
								}
	  				)
	  		}
	  

	      	
	  }
	  

}]);