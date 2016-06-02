angular
		.module('issueTrackingSystem.userModule')
		.factory(
				'userEditService',
				[
						'$http',
						function($http, $q) {
							return {

								EditIssue : function() {
									return $http
											.get('./defaultIssuesList')
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

								updateTaskProgress : function(
										taskProgressUpdate, id) {
									return $http
											.get(
													'../taskProgressUpdate/'
															+ taskProgressUpdate
															+ '/' + id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													})
								},

								startTask : function(id, dueDate) {

									return $http
											.get(
													'../starttask/' + id + "/"
															+ dueDate)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													})

								},

								stopTask : function(id) {

									return $http
											.get('../stoptask/' + id)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													})
								},

								pauseTask : function(id, reason) {

									return $http
											.get(
													'../pausetask/' + id + '/'
															+ reason)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching users');
														return $q
																.reject(errResponse);
													})
								},

							}
} ]);