angular
		.module('issueTrackingSystem.userModule')
		.controller(
				'userController',
				[
						'$scope',
						'$http',
						'userService',
						function($scope, $http, userService, issuesList) {

							$scope.issueList = [];
							var issues = $http({
								method : 'GET',
								url : '../defaultIssuesList'
							}).success(function(data) {
								alert("-------in controller-------" + data);
								$scope.issueList = data;
							})

							userService.initializeSelect().then(function(d) {
								$scope.projects = d.projects;
								// alert(d.projects);
								// alert(d.myValue);
								// $scope.applications=d.application;
								// alert(d.application);

								// alert(d.data.id);
								// alert(d.data.name);

							});
							$scope.myFunc = function() {
								var projectId = angular
										.element(
												document
														.querySelector("select[id=selectId]"))
										.val();
								alert("in onchange")
								alert(projectId);
								this.pId = projectId;
								this.project = {
									id : projectId
								}
								userService
										.initializeSelectAll(this.pId)
										.then(
												function(d) {
													// alert("in success all
													// select");
													// alert("In controller
													// select all called");
													// alert(d.myValue1);
													$scope.issueTypeList = d.issueType;
													$scope.priorities = d.issuePriority;
													$scope.environments = d.applicationEnvironment;
													$scope.applicationTeamMembers = d.applicationTeamMembers;

												});
							}
							$scope.myFunction = function() {
								var applicationId = angular
										.element(
												document
														.querySelector("select[id=selectId]"))
										.val();
								alert("in onchange")
								alert(applicationId);
								/*
								 * this.pId=projectId; this.project ={
								 * id:projectId }
								 * userService.initializeSelectAll(this.pId).then(
								 * function(d) { alert("in success all select");
								 * alert("In controller select all called");
								 * alert(d.myValue1);
								 * $scope.issueTypeList=d.issueType;
								 * $scope.priorities=d.issuePriority;
								 * $scope.environments=d.applicationEnvironment;
								 * 
								 * });
								 */
							}

							this.add = function() {
								alert("hello");
								/*
								 * var projectId =
								 * this.createIssue.project.projectId; var
								 * issueId = this.createIssue.issueType.issueId;
								 * var issuePriorityId =
								 * this.createIssue.issuePriority.issuePriorityId;
								 * var summary = this.createIssue.summary; var
								 * component = this.createIssue.component; var
								 * affectedVersion =
								 * this.createIssue.affectedVersion; var
								 * applicationEnvironment =
								 * this.createIssue.applicationEnvironment; var
								 * description = this.createIssue.description;
								 */
								var summary = this.createIssue.summary;
								var component = this.createIssue.component;
								var affectedVersion = this.createIssue.affectedVersion;
								var description = this.createIssue.description;
								var member = {
									"memberId" : this.createIssue.owner.memberId
								}
								var issueOwner = {
									"member" : member
								}
								var project = {
									"id" : this.createIssue.project.id
								}
								var applicationIssueType = {
									"id" : this.createIssue.issueType.id
								}
								var applicationIssuePriority = {
									"id" : this.createIssue.issuePriority.id
								}
								var applicationEnvironment = {
									"id":this.createIssue.applicationEnvironment.id
}
var formData={
									"project" : project,
									"applicationIssueType" : applicationIssueType,
									"applicationIssuePriority" : applicationIssuePriority,
									"summary" : summary,
									"component" : component,
									"affectedVersion" : affectedVersion,
									"applicationEnvironment" : applicationEnvironment,
									"description" : description,
									"issueOwner" : issueOwner
								};
								alert(formData);
								userService
										.submitCreateIssue(formData)
										.then(
												function(formData) {
													alert("REgistered");
												},
												function(errResponse) {
													console
															.error('Error while searching issues');
												});
							}
							
							$scope.defaultIssueSearchList = [];

							var issueSearchList = $http({
								
								method : 'GET',
								url : '../defaultIssues'
							}).success(function(data) {
								alert("searchUser")
								$scope.defaultIssueSearchList = data;

							})
							
							
							$scope.showadvsearch = function() {
								$('#advsearch').show();
								$scope.isDisabled=true;
							
								userService.getList()
								.then(
										function(data) {
											alert(data);
											$scope.applicationNames=data;
										
										},
										function(errResponse) {
											console
													.error('Error while searching assigned issues');
										})
					}
							
							
							
							$scope.calldropdowns = function() {
								var applicationid=this.application;
								$scope.isDisabled=false;
								userService.getAllList(applicationid)
								.then(
										function(data) {
											//$scope.application=data;
											$scope.issuepriorities = data.priorities;
											$scope.issuetype=data.issuetypes;
											$scope.project=data.projects;
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
								userService.getadvSearchData(filterIssueType,filterProjectName,filterPriority)
								.then(
										function(data) {
											alert(data);
											$scope.defaultIssueSearchList = data;
											$('#advsearch').hide();

										},
										function(errResponse) {
											console
													.error('Error while searching assigned issues');
										})
								
							
							
							}
							
							
							/* issueService returns list to populate drop-down */
							/*
							 * userService.initializeSelect() .then( function(d) {
							 * 
							 * retrieve and assign value from list
							 * angular.forEach(d,function(value,key){
							 * switch(key){
							 * 
							 * case 0: $scope.priorities=value; break; case 1:
							 * $scope.assigneeList=value; break; case 2:
							 * $scope.issueTypeList=value; break; case 3:
							 * $scope.projects=value; break; } });
							 *  }, function(errResponse){ console.error('Error
							 * while fetching'); } ); this.createIssue={};
							 * this.add=function(){ // call service to persist
							 * in db
							 * userService.submitCreateIssue(this.createIssue);
							 * .then( function(d) {
							 * 
							 *  }, function(errResponse){ console.error('Error
							 * while fetching'); } ); this.createIssue={}; };
							 */

						} ]);