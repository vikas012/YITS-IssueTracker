angular
		.module('issueTrackingSystem.userModule')
		.controller(
				'userEditController',
				[
						'$scope',
						'$http',
						'userEditService',
						function($scope, $http, userEditService, issuesList) {
							
							$scope.issueList = [];
							var issues = $http({
								method : 'GET',
								url : '../defaultIssuesList'
							}).success(function(data) {

								$scope.issueList = data;
								alert(data);
							});

							$scope.searchAllIssues = function() {

								$http.get('../defaultIssuesList').success(
										function(data) {

											$scope.issueList = data;

										});

							};

							var editIssueId = 0;

							$scope.getEditIssueId = function(id) {
								alert("in getIssueID");
								$scope.issueList = null;

								editIssueId = id;
							}
							
							$scope.editTaskProgress = function() {

								$scope.editId = editIssueId;
								alert($scope.editId);
								alert("In edit task progress");

								var taskProgressUpdate = $scope.taskProgressUpdate;

								userEditService
										.updateTaskProgress(taskProgressUpdate,
												$scope.editId)
										.then(
												function(data) {
													alert("updated");
													$scope.searchAllIssues();
													$scope.taskProgressUpdate = "";

												},
												function(errResponse) {
													console
															.error('Error while showing stopTask members');
												});

							}
							
							$scope.startTask = function(index,id) {

								/*var id = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();*/
								
								alert(index);
								
								/*$scope.id = editIssueId;*/
								alert(id);

								var dueDate = $scope.issueList[index].dueDate;
								var date1 = new Date(dueDate);
								var dd = date1.getDate();
								var mm = date1.getMonth() + 1; // January is 0!
								var yyyy = date1.getFullYear();
								var date2 = mm + '/' + dd + '/' + yyyy;

								userEditService
										.startTask(id, dueDate)
										.then(
												function(data) {
													$scope.searchAllIssues();

												},
												function(errResponse) {
													console
															.error('Error while showing startTask members');
												})

							};
							
							$scope.stopTask = function(id) {

								/*var id = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();*/
								alert(id);

								userEditService
										.stopTask(id)
										.then(
												function(data) {
													$scope.searchAllIssues();

												},
												function(errResponse) {
													console
															.error('Error while showing stopTask members');
												});

							};
							
							var pauseId = 0;
							
							$scope.pauseId = function(id) {
								alert("In pause Id");
								pauseId = id;
								alert(pauseId);
							}

							
							$scope.pauseTask = function(index) {

								/*var id = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();*/
								
								$scope.id = pauseId;
								alert($scope.id);

								var reason = $scope.reason;

								userEditService
										.pauseTask($scope.id, reason)
										.then(
												function(data) {
													$scope.searchAllIssues();

												},
												function(errResponse) {
													console
															.error('Error while showing pauseTask members');
												});

							};
							
							$scope.refresh = function() {

								$scope.searchAllIssues();
							};
							
							$scope.exportData = function() {
								var blob = new Blob(
										[ document.getElementById('someInfo').innerHTML ],
										{
											type : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
										});
								saveAs(blob, "Report.xls");
							}


} ]);