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
								
								$scope.issueList = data;
							})

							userService.initializeSelect().then(function(d) {
								$scope.projects = d.projects;
							

							});
							$scope.myFunc = function() {
								var projectId = angular
										.element(
												document
														.querySelector("select[id=selectId]"))
										.val();
								
								
								this.pId = projectId;
								this.project = {
									id : projectId
								}
								userService
										.initializeSelectAll(this.pId)
										.then(
												function(d) {
											
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
								
								userService
										.submitCreateIssue(formData)
										.then(
												function(formData) {
													alert("Registered");
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
								
								$scope.defaultIssueSearchList = data;

							})

							$scope.showadvsearch = function() {
								$('#advsearch').show();

								$scope.isDisabled=true;
							
								userService.getList()
								.then(
										function(data) {
											
											$scope.applicationNames=data;
										
										},
										function(errResponse) {
											console
													.error('Error while searching assigned issues');
										})
					};
							
							
							

							

							$scope.calldropdowns = function() {
								var applicationid = this.application;
								$scope.isDisabled = false;
								userService
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

								userService.getadvSearchData(filterIssueType,filterProjectName,filterPriority)
								.then(
										function(data) {
											
											$scope.defaultIssueSearchList = data;
									


												},
												function(errResponse) {
													console
															.error('Error while searching assigned issues');
												})

							}

							$scope.viewIssue = function() {

								var id = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();

								userService
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

								userService
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
							
							
							var conversation = $http({
								method : 'GET',
								url : '../conversationList'
							}).success(function(data) {
								
								$scope.conversationList = data;
							})
							

							$scope.viewIssue = function(){
								
								var id = angular.element(document.querySelector("input[id=radio]:checked")).val();
								
								
								userService.viewIssueDetails(id)
								.then(
									function(data){
										
										
										$scope.issueDetails=data.issueobject;
										$scope.attachments=data.listOfAttachment;
										
									},
									 function(errResponse){
												 console.error('Error while showing Issue details');
											 }
									)	
								
								
							};
							
							$scope.download = function(id){
								
								
								
									userService.download(id)
									.then(
										function(data){
									
											
										},
										 function(errResponse){
													 console.error('Error while showing Issue details');
												 }
										)	
									
									
								};
							

								$scope.fetchIssueDetailsConv=function(id){
								
									userService.fetchIssueDetailsConv(id)
									.then(
											function(data){
											
												$scope.fetchedIssue = data;
									
												userService.getMemberListConv()
												.then(
														function(data){
										
															$scope.fetchedMember = data;
														},	
														 function(errResponse)
														 {
															console.error('Error while getMemberListConv members');
														 }
												)	
											},
											 function(errResponse)
											 {
												console.error('Error while fetchIssueDetails members');
											 }
									)	
								};

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
							 * $scope.projects=value; break; } }); },
							 * function(errResponse){ console.error('Error while
							 * fetching'); } ); this.createIssue={};
							 * this.add=function(){ // call service to persist
							 * in db
							 * userService.submitCreateIssue(this.createIssue);
							 * .then( function(d) {
							 *  }, function(errResponse){ console.error('Error
							 * while fetching'); } ); this.createIssue={}; };
							 */

						} ]);