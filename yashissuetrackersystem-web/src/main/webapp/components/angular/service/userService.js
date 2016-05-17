angular.module('issueTrackingSystem.userModule').factory('userService',['$http',function($http,$q){
	  return {
		  
		  initializeSelect: function() {
	          return $http.get('../getProjects')
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

	  
	      	

	      	initializeSelectAll:function(projectId){
	      	
	      	
	      		return $http.get('../getAllSelectFields/'+projectId)
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
		          return $http.post('../createIssue',createIssue)

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
		      	
fetchIssueDetailsConv:function(id){
					
					
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
				
				
				getMemberListConv:function(){
					
					
					return $http.get('../getMemberListConv')
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
  			
  			download:function(id){
  				
  				return $http.get('../download/'+id)
  				.then(
  						
  						function(response){
  							
  							return response.data;
  						},
  						
  						function(errRespnse){
  							
  							console.error('Error while showing Issue details');
  							return $q.reject(errResponse);
  						}
  				);
  				
  			},
				  
  		  
  			viewIssueDetails:function(id){
  				return $http.get('../showIssueDetails/'+id)
					//return $http({  url: '../showIssueDetails', method: "GET", params: {id: id} })
	  					.then(
	  								function(response){
	  									
	  								  return response.data;
	  								},
	  								function(errResponse){
	  									console.error('Error while showing Issue details');
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
  			
  			
  			
startTask:function(id,dueDate){
	  			
	  			
	  			
	  			
	  			return $http.get('../starttask/'+id+"/"+dueDate)
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
	  
	  stopTask:function(id){
			
			return 	$http.get('../stoptask/'+id)
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
		
		pauseTask:function(id,reason){
			
			return 	$http.get('../pausetask/'+id+'/'+reason)
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
		
		
		updateTaskProgress:function(taskProgressUpdate,id){
			return 	$http.get('../taskProgressUpdate/'+taskProgressUpdate+'/'+id)
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