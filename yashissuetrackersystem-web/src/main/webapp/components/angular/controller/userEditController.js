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
							
							$scope.startTask = function(index) {

								var id = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();

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
							
							$scope.stopTask = function() {

								var id = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();

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
							
							$scope.pauseTask = function() {

								var id = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();

								var reason = $scope.reason;

								userEditService
										.pauseTask(id, reason)
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