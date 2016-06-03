angular
		.module('issueTrackingSystem.userModule')
		.controller(
				'userSearchIssueController',
				[
						'$scope',
						'$http',
						'userSearchIssueService',
						function($scope, $http, userSearchIssueService) {
							
							$scope.defaultIssueSearchList = [];

							var issueSearchList = $http({

								method : 'GET',
								url : '../defaultIssues'
							}).success(function(data) {

								$scope.defaultIssueSearchList = data;

							})

							$scope.showadvsearch = function() {
								$('#advsearch').show();

								$scope.isDisabled = true;

								userSearchIssueService
										.getList()
										.then(
												function(data) {

													$scope.applicationNames = data;

												},
												function(errResponse) {
													console
															.error('Error while searching assigned issues');
												})
							};

							$scope.calldropdowns = function() {
								var applicationid = this.application;
								$scope.isDisabled = false;
								userSearchIssueService
										.getAllList(applicationid)
										.then(
												function(data) {
													// $scope.application=data;
													$scope.issuepriorities = data.priorities;
													$scope.issuetype = data.issuetypes;
													$scope.project = data.projects;
												},
												function(errResponse) {
													console
															.error('Error while searching assigned issues');
												})
							};

							$scope.searchFilter = function() {
								var filterIssueType = $scope.advIssueType;
								var filterProjectName = this.advProject;
								var filterPriority = this.advPriority;

								userSearchIssueService
										.getadvSearchData(filterIssueType,
												filterProjectName,
												filterPriority)
										.then(
												function(data) {

													$scope.defaultIssueSearchList = data;

												},
												function(errResponse) {
													console
															.error('Error while searching assigned issues');
												})

							};

							$scope.viewIssue = function(id) {


								userSearchIssueService
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

								userSearchIssueService
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

							
							
							
							
						}])