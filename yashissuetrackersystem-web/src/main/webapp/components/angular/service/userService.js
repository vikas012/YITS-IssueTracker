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
		      	
		      	fetchIssueDetailsConv:function(id){
					
					alert(id);
//					var issueId={
//							
//						"issueId":id,	
//							
//					};
					return $http.post('../fetchIssueDetailsConv/'+id)
				 	.then(
				 				function(response){
				 						return response.data;
				 				}, 
				 				function(errResponse){
				 						console.error('Error while fetchIssueDetails member');
				 						return $q.reject(errResponse);
				 				}
	         			);
					
				}, 
		      	
	      	EditIssue:function(){
	      		return $http.get('../defaultIssuesList')
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
  			
  			/**
			 * File Upload
			 */
  			fileUpload:function(formData){
  				var request = {
						method : 'POST',
						url : '../uploadFile',
						data : formData,
						headers : {
							'Content-Type' : undefined
						}
					};
					 $http(request)
					 .then(
			 				function(response){
			 					alert("File uploaded successfully");
			 				}, 
			 				function(errResponse){
			 				}
         			);
  				
  				
	  
  			},	
	  

	      	
	  }
	  

}]);