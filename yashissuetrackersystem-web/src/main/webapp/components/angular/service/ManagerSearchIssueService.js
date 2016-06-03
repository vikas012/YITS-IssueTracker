angular
		.module('issueTrackingSystem.managerModule')
		.factory(
				'managerSearchIssueService',
				[
						'$http',
						function($http, $q, $scope) {
							
							return{
							
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
							
							}
							
						}])