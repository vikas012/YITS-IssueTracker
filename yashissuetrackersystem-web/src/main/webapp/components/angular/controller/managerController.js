angular
		.module('issueTrackingSystem.managerModule')
		.controller(
				'managerController',
				[
						'$scope',
						'$http',
						'managerService',
						function($scope, $http, managerService, issueList) {

							$scope.members1 = [];

							var members = $http({
								method : 'GET',
								url : '../memberList'
							})
									.success(
											function(data) {

												$scope.members1 = data;

												angular
														.forEach(
																$scope.members1,
																function(value,
																		key) {

																	if (value.isActive == 0) {

																		value.isActive = "Activate";
																	} else {

																		value.isActive = "DeActivate";
																	}

																});

											})

							$scope.showLookUpForm = false;
							$scope.showRegisterForm = false;
							$scope.showNonYashRegisterForm = false;

							$scope.ldapUser = {
								ldapName : "",
								ldapEmail : ""
							};
							$scope.members = [];
							$scope.member = {
								memberId : "",
								name : "",
								email : "",
								contact : "",
								managerEmail : ""
							};
							$scope.userId = "";
							$scope.userName = "";
							$scope.userEmail = "";
							$scope.userMobile = "";

							$scope.showLookForm = function() {

								$scope.showLookUpForm = true;
								$scope.showNonYashRegisterForm = false;

							}

							$scope.showRegisterationForm = function() {

								$scope.showLookUpForm = false;
								$scope.showRegisterForm = false;
								$scope.showNonYashRegisterForm = true;
							}

							$scope.fetchIssueDetails = function() {

								var index = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();
								alert(index);

								if (index == null) {
									alert("Please select the entry you want to update!");
								}

								var fetchIssueDetails = $http({
									method : 'GET',
									url : '../fetchIssueDetails/{index}'
								}).success(function(data) {
									alert("success")
									$scope.fetchedIssue = data;
								})
							}

							$scope.checkUser = function() {

								$scope.ldapUser.ldapName = $scope.ldapName;
								$scope.ldapUser.ldapEmail = $scope.ldapEmail;

								$scope.showLookUpForm = false;
								$scope.showRegisterForm = true;
								$scope.checkUserInLdap($scope.ldapUser);

							}

							$scope.unassignedIssueList = [];

							var unassignedIssues = $http({
								method : 'GET',
								url : '../issue/assign'
							}).success(function(data) {

								$scope.unassignedIssueList = data;
							})

							$scope.members1 = [];

							var issues = $http({
								method : 'GET',
								url : '../memberList'
							})
									.success(
											function(data) {

												$scope.members1 = data;

												angular
														.forEach(
																$scope.members1,
																function(value,
																		key) {

																	if (value.isActive == 0) {

																		value.isActive = "Activate";
																	} else {

																		value.isActive = "DeActivate";
																	}

																});

											})

							$scope.checkUserInLdap = function(ldapUser) {

								managerService
										.checkUserInLdap(ldapUser)
										.then(
												function(d) {

													$scope.members = d;
													$scope.userId = $scope.members.userId;
													$scope.userName = $scope.members.userName;
													$scope.userEmail = $scope.members.userEmail;
													$scope.userMobile = $scope.members.userMobile;

												},

												function(errResponse) {
													console
															.error('Error while fetching');
												}

										)
							};

							$scope.registerMember = function() {

								$scope.member.memberId = $scope.userId;
								$scope.member.name = $scope.userName;
								$scope.member.email = $scope.userEmail;
								$scope.member.contact = $scope.userMobile;
								$scope.showRegisterForm = false;

								managerService.registerMember($scope.member);

							}

							$scope.registerNonYashMember = function() {

								$scope.member.memberId = $scope.userId;
								$scope.member.name = $scope.userName;
								$scope.member.email = $scope.userEmail;
								$scope.member.contact = $scope.userMobile;
								$scope.member.managerEmail = $scope.managerEmail;
								$scope.showNonYashRegisterForm = false;

								managerService
										.registerNonYashMember($scope.member);

							}
							$scope.defaultIssueList = [];

							var issueList = $http({
								method : 'GET',
								url : '../defaultIssues'
							}).success(function(data) {

								$scope.defaultIssueList = data;

							})

							

							$scope.getSearchMember = function() {

								var searchText = $scope.searchText;

								if (searchText == undefined) {

									alert("Please Enter Text!");
								} else {
									managerService
											.searchMember(searchText)
											.then(
													function(data) {
														$scope.members = data;

														angular
																.forEach(
																		$scope.members,
																		function(
																				value,
																				key) {

																			if (value.isActive == 0) {

																				value.isActive = "Activate";
																			} else {

																				value.isActive = "DeActivate";
																			}

																		});
													},
													function(errResponse) {
														console
																.error('Error while showing search members');
													})

								}

							}

							$scope.getDataAfterActiveStatus = function() {

								$http({
									method : 'GET',
									url : '../memberList'
								})
										.success(
												function(data) {
													// alert(data);
													// console.log(data);

													console.log(data);
													$scope.members1 = data;
													angular
															.forEach(
																	$scope.members1,
																	function(
																			value,
																			key) {

																		if (value.isActive == 0) {

																			value.isActive = "Activate";
																		} else {

																			value.isActive = "DeActivate";
																		}

																	});

												});
							}

							$scope.memberActivate = function(memberId) {

								if (memberId == "") {
									alert("Please Select ID!");
								} else {
									managerService
											.memberActivate(memberId)
											.then(
													function(data) {

														$scope
																.getDataAfterActiveStatus();
													},
													function(errResponse) {
														console
																.error('Error while showing member status');
													})
								}
							}

							$scope.memberActivateForSearch = function(memberId) {

								if (memberId == "") {
									alert("Please Select ID!");
								} else {
									managerService
											.memberActivate(memberId)
											.then(
													function(data) {

														$scope
																.getSearchMember();
													},
													function(errResponse) {
														console
																.error('Error while showing member status');
													})
								}
							}

							managerService
									.showAssignedIssues()
									.then(
											function(data) {
												$scope.assignedIssues = data;
											},
											function(errResponse) {
												console
														.error('Error while showing assigned issues');
											})

							$scope.getSearchAssignedIssue = function() {

								var searchText = $scope.searchAssignedIssueText;

								if (searchText == "") {

									alert("Please Enter Text!");
								} else {
									managerService
											.searchAssignedIssue(searchText)
											.then(
													function(data) {
														$scope.assignedIssues = data;
													},
													function(errResponse) {
														console
																.error('Error while searching assigned issues');
													})
								}
							}

							
							
							$scope.showadvsearch = function() {
								$('#advsearch').show();
								$('#issuetypesearch').hide();
								$scope.isDisabled=true;
								alert("hello");
								managerService.getList()
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
								managerService.getAllList(applicationid)
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
								alert(filterIssueType);
								alert(filterProjectName);
								alert(filterPriority);
								managerService.getadvSearchData(filterIssueType,filterProjectName,filterPriority)
								.then(
										function(data) {
											alert(data);
											$scope.defaultIssueList = data;
										
											$('#advsearch').hide();
											$('#issuetypesearch').show();

										},
										function(errResponse) {
											console
													.error('Error while searching assigned issues');
										})
								
							
							
							}
							
							
							
							


							

						} ]);