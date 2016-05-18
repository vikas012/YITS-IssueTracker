angular.module('issueTrackingSystem.managerModule').factory('AssignedIssueService',['$http',function($http, $q, $scope) {

							return {
								
								//get list of assigned issues
								showAssignedIssues : function() {
									return $http
											.get('../showAssignedIssue')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while retrieving assigned issues');
														return $q
																.reject(errResponse);
													});
								},

								//search list of assigned issues
								searchAssignedIssue : function(searchText) {
									return $http
											.get(
													'../searchAssignedIssue/'
															+ searchText)
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
							
							}
							
							}]);