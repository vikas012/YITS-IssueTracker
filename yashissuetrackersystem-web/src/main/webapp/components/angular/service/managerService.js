angular.module('issueTrackingSystem.managerModule').factory('managerService',['$http','$q',function($http,$q,$scope){
	

	
	  return {
		  
		  
		  		checkUserInLdap:function(ldapUser){
		  				return $http.post('../checkMemberInLdap',ldapUser)
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


		  /*getMembers:function(){
		  alert("in service for data");
		  var issues=$http({
		  method:'GET',
		      url:'../memberList' 
		  }).success(function(data){
			  
		  alert(data+"service");	
		  
		  return data;
		  })
		  },*/

		  		getMembers:function(){
		              alert("shraddha");
		              var issues=$http({
		            	  				method:'GET',
		            	  				url:'./memberList' //spring controller call, use @ResponseBody
		              				})
		             .success(function(data){
		            	 	alert("Succeess");
		            	 	return data;
		             })
		  		},


		  searchMember:function(searchText){
			  alert("Please Enter Text service!");
					return $http.get('../searchMember/'+ searchText)
				 	.then(
				 				function(response){
				 						return response.data;
				 				}, 
				 				function(errResponse){
				 						console.error('Error while searching issues');
				 						return $q.reject(errResponse);
				 				}
	         			);
			},

		  			
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
		  				return $http.post('../registerMember',member)
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

			 * searchIssue:function(searchText){ return $http.get(
			 * './getIssues/' + searchText) .then( function(response){ return
			 * response.data; }, function(errResponse){ console.error('Error
			 * while searching issues'); return $q.reject(errResponse); } ); }
			 */
	      	
	  }
	 

}]);