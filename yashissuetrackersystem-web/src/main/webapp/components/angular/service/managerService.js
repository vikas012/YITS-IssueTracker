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

		  			
	

		  				  
			  			showAssignedIssues:function(){
			  				return $http.get('../showAssignedIssue')
						 	.then(
						 				function(response){
						 						return response.data;
						 				}, 
						 				function(errResponse){
						 						console.error('Error while retrieving assigned issues');
						 						return $q.reject(errResponse);
						 				}
			         			);
			  			 },
			  				  
			  			searchAssignedIssue:function(searchText){
			  				return $http.get('../searchAssignedIssue/'+searchText)
			  				.then(
			  							function(response){
			  								return response.data;
			  							},
			  							function(errResponse){
			  								console.error('Error while fetching assigned issues');
			  								return $q.reject(errResponse);
			  							}

			  					);
			  				
			  			},	  

			  			getList:function(){
			  				return $http.get('../getApplication')
			  				.then(
			  							function(response){
			  								return response.data;
			  							},
			  							function(errResponse){
			  								console.error('Error while fetching assigned issues');
			  								return $q.reject(errResponse);
			  							}

			  					);
			  				
			  			},	
			  			
			  			
			  			getAllList:function(applicationid){
			  				return $http.get('../getdropdowns/'+applicationid)
			  				.then(
			  							function(response){
			  								return response.data;
			  							},
			  							function(errResponse){
			  								console.error('Error while fetching assigned issues');
			  								return $q.reject(errResponse);
			  							}

			  					);
			  				
			  			},
			  			
			  			
			  			getadvSearchData:function(filterIssueType,filterProjectName,filterPriority){
			  				return $http.post('../getadvsearchdata/'+filterIssueType+'/'+filterProjectName+'/'+filterPriority)
			  				.then(
			  							function(response){
			  								return response.data;
			  							},
			  							function(errResponse){
			  								console.error('Error while fetching assigned issues');
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

			 */
	      	
	  }
	 

}]);