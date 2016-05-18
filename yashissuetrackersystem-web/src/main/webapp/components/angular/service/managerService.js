angular
		.module('issueTrackingSystem.managerModule')
		.factory(
				'managerService',
				[
						'$http',
						function($http, $q, $scope) {

							return {

								
								getMembers : function() {

									var issues = $http({
										method : 'GET',
										url : './memberList'
									}).success(function(data) {

										return data;
									})
								},

								getMembers : function() {

									var issues = $http({
										method : 'GET',
										url : './memberList'
									}).success(function(data) {
									
										return data;
									})
								},

								fetchIssueDetails : function(fetchId) {

									return $http
											.get(
													'../fetchIssueDetails/{fetchId}')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching issue');
														return $q
																.reject(errResponse);
													});
								},
								submitAssignedIssue : function(assignedIssue) {

									return $http.post('../assignIssue',
											assignedIssue).then(
											function(response) {
									
												return response.data;
											}/*
												 * , function(errResponse){
												 * console.error('Error while
												 * assigning issue'); return
												 * $q.reject(errResponse); }
												 */
									);
								},

								searchMember : function(searchText) {
									return $http
											.get(
													'../searchMember/'
															+ searchText)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while searching issues');
														return $q
																.reject(errResponse);
													});
								},

							

								memberActivate : function(memberId) {

									var member = {

										"memberId" : memberId,
									};
									return $http
											.post('../blockUnblockMember',
													member)
											.then(
													function(response) {

														return response.data;

													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													}

											);

								},

								

								initializeSelect : function() {
								
									return $http
											.get('../getProjects')
											.then(
													function(response) {

														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													});
								},
								initializeSelectAll : function(projectId) {

									return $http
											.get(
													'../getAllSelectFields/'
															+ projectId)
											.then(
													function(response) {

														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													});
								},

								submitCreateIssue : function(createIssue) {
									return $http
											.post('../managerCreateIssue',
													createIssue)

											.then(
													function(response) {

														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													});

								},

								getList : function() {
									return $http
											.get('../getApplication')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching assigned issues');
														return $q
																.reject(errResponse);
													}

											);

								},

								getAllList : function(applicationid) {
									return $http
											.get(
													'../getdropdowns/'
															+ applicationid)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching assigned issues');
														return $q
																.reject(errResponse);
													}

											);

								},

								getadvSearchData : function(filterIssueType,
										filterProjectName, filterPriority) {
									return $http
											.post(
													'../getadvsearchdata/'
															+ filterIssueType
															+ '/'
															+ filterProjectName
															+ '/'
															+ filterPriority)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching assigned issues');
														return $q
																.reject(errResponse);
													}

											);

								},

								memberDelete : function(indexId) {

									var member = {

										"memberId" : indexId,

									};
									return $http
											.post('../deleteMember/', member)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while deleting member');
														return $q
																.reject(errResponse);
													});

								},

								searchMemberType : function(memberId) {
									return $http
											.get(
													'../searchMemberType/'
															+ memberId)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while getting member type in service');
														return $q
																.reject(errResponse);
													}

											);

								},

								/* file upload */
								fileUpload : function(formData) {

									var request = {
										method : 'POST',
										url : '../uploadFile',
										data : formData,
										headers : {
											'Content-Type' : undefined
										}
									};
									$http(request).then(function(response) {
										alert("File uploaded successfully");
									}, function(errResponse) {
									});

								},

								download : function(id) {

									return $http
											.get('../download/' + id)
											.then(

													function(response) {

														return response.data;
													},

													function(errRespnse) {

														console
																.error('Error while showing Issue details');
														return $q
																.reject(errResponse);
													});

								},

								viewIssueDetails : function(id) {
									return $http
											.get('../showIssueDetails/' + id)
											// return $http({ url:
											// '../showIssueDetails', method:
											// "GET", params: {id: id} })
											.then(
													function(response) {

														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while showing Issue details');
														return $q
																.reject(errResponse);
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
						  			
						  			

							/*
							 * initializeSelect: function() { return
							 * $http.get('./getPriority') .then(
							 * function(response){ return response.data; },
							 * function(errResponse){ console.error('Error while
							 * fetching users'); return $q.reject(errResponse); } ); },
							 * 
							 *  /* initializeSelect: function() { return
							 * $http.get('./getPriority') .then(
							 * function(response){ return response.data; },
							 * function(errResponse){ console.error('Error while
							 * fetching users'); return $q.reject(errResponse); } ); },
							 */

							}

						} ]);
