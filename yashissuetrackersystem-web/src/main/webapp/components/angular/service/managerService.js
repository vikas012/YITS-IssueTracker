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
		  },
		  searchMember:function(searchText){
			  alert("Please Enter Text service!");
					return $http.get(
							'./searchMember/'
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
=======
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker.git
	  
	  			getUnassignedIssues:function(){
					  alert("Prajvi")
					  var issues=$http({
						  method:'GET',
					      url:'../issue/assign' 
					  }).success(function(data){
						  alert(data);
						  return data;
					  })
	  			},
	  			
	  			registerMember:function(member){
	  			  return $http.post('./registerMember',member)
	  			  .then(
	  			  function(response){
	  			   return response.data;
	  			  },
	  			  function(errResponse){
	  			  console.error('Error while fetching users');
	  			  return $q.reject(errResponse);
	  			  }

	  			  );
	  			}
		  
		 /*
			 * initializeSelect: function() { return $http.get('./getPriority')
			 * .then( function(response){ return response.data; },
			 * function(errResponse){ console.error('Error while fetching
			 * users'); return $q.reject(errResponse); } ); },
			 * 
			 * submitCreateIssue: function(createIssue) { return
			 * $http.post('./createIssue',createIssue) .then(
			 * function(response){ alert(response.data); return response.data; },
			 * function(errResponse){ console.error('Error while fetching
			 * users'); return $q.reject(errResponse); } ); },
			 */
	      	
	      	
	      	/*
			 * searchIssue:function(searchText){ return $http.get(
			 * './getIssues/' + searchText) .then( function(response){ return
			 * response.data; }, function(errResponse){ console.error('Error
			 * while searching issues'); return $q.reject(errResponse); } ); }
			 */
	      	
	  }
}]);