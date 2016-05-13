angular.module('issueTrackingSystem.managerModule').factory('managerService',['$http',function($http,$q,$scope){
	

	
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

		  			

		  			
		  			registerMember:function(member){
		  				return $http.post('../registerYashMember',member)
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
		  			registerNonYashMember:function(member){
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

		  			 getIssues:function(){
		  				  alert("in default")
		  				  var issues=$http({
		  				  method:'GET',
		  				      url:'../defaultIssues'
		  				  }).success(function(data){
		  				  alert(data);
		  				  return data;
		  				  })
		  				  },
		  	
		  				  
		  			memberActivate:function(memberId){
		  					
		  					var member={
		  							
		  						"memberId":memberId,	
		  					};
			  				return $http.post('../blockUnblockMember',member)
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