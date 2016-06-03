angular.module('issueTrackingSystem.managerModule').controller('managerSearchIssueController',['$scope','$http','managerSearchIssueService',function($scope, $http, managerSearchIssueService) {
	
	
		
	$scope.defaultIssueList = [];
	var issueList = $http({
		method : 'GET',
		url : '../defaultIssues'
	}).success(function(data) {

		$scope.defaultIssueList = data;

	})
	
	
	$scope.showadvsearch = function() {
								$('#advsearch').show();
								$scope.isDisabled = true;

								managerSearchIssueService
										.getList()
										.then(
												function(data) {

													$scope.applicationNames = data;

												},
												function(errResponse) {
													console
															.error('Error while searching assigned issues');
												})
							}

							$scope.calldropdowns = function() {
								var applicationid = this.application;
								$scope.isDisabled = false;
								managerSearchIssueService
										.getAllList(applicationid)
										.then(
												function(data) {
													
													$scope.issuepriorities = data.priorities;
													$scope.issuetype = data.issuetypes;
													$scope.project = data.projects;
												},
												function(errResponse) {
													console
															.error('Error while searching assigned issues');
												})
							}

							$scope.searchFilter = function() {
								var filterIssueType = $scope.advIssueType;
								var filterProjectName = this.advProject;
								var filterPriority = this.advPriority;
								managerSearchIssueService
										.getadvSearchData(filterIssueType,
												filterProjectName,
												filterPriority)
										.then(
												function(data) {

													$scope.defaultIssueList = data;
										

												},
												function(errResponse) {
													console
															.error('Error while searching assigned issues');
												})

							}
							
							$scope.viewIssue = function(id) {
								
								managerSearchIssueService
										.viewIssueDetails(id)
										.then(
												function(data) {

													$scope.issueDetails = data.issueobject;
													$scope.attachments = data.listOfAttachment;

												},
												function(errResponse) {
													console
															.error('Error while showing Issue details');
												})

							};

							$scope.download = function(id) {

								managerSearchIssueService
										.download(id)
										.then(
												function(data) {
													alert("File downloaded successfully!");

												},
												function(errResponse) {
													console
															.error('Error while showing Issue details');
												})

							};
	
}]);