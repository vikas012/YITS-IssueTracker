angular.module('issueTrackingSystem.managerModule').factory('managerService',['$http','$q',function($http,$q){
	
	  return {
		  
		  
		  		checkUserInLdap:function(ldapUser){
		  				alert("inside service");
		  				alert("Name---in service "+ldapUser.ldapName);
		  				return $http.post('./checkMemberInLdap',ldapUser)
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
		  			
	  getMembers:function(){
		  alert("shraddha");
		  var issues=$http({
		  method:'GET',
		      url:'./memberList' //spring controller call, use @ResponseBody
		  }).success(function(data){
		  alert("Succeess");
		  return data;
		  })
		  }
		 /* initializeSelect: function() {
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
		      	},*/
	      	
	      	
	      	/*searchIssue:function(searchText){
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
		}*/
	      	
	  }
}]);