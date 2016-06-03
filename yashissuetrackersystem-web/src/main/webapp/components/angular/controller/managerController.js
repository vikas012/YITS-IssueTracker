angular.module('issueTrackingSystem.managerModule').controller('managerController',['$scope','$http','managerService',function($scope, $http, managerService, issueList) {

							$scope.issueList = [];

					


							this.createIssue = {};
							managerService.initializeSelect().then(function(d) {
								

								$scope.applications = d.applications;
								
							}

							);
							
							$scope.selectApplicationId = function() {
								alert("in select application Id");
								var applicationId = angular
										.element(
												document
														.querySelector("select[id=selectAppId]"))
										.val();

								this.appId = applicationId;
								alert(this.appId);
								this.application = {
									"id" : applicationId
								}
								managerService
										.initializeSelectAll(this.appId)
										.then(
												function(d) {
													
													$scope.projects = d.projects;
													$scope.issueTypeList = d.issueType;
													$scope.priorities = d.issuePriority;
													$scope.environments = d.applicationEnvironment;
													$scope.assignees = d.applicationTeamMembers;

												});
							}

							this.add = function() {
								alert("hello");
								alert("In submit issue...!!");
								
								var summary =this.createIssue.summary;
								var component=this.createIssue.component;
								var affectedVersion=this.createIssue.affectedVersion;
								var description=this.createIssue.description;
								var member = {
										"memberId":this.createIssue.assignedUser.member.memberId
								}
								
								var assignedUser={
										"member":member
									}
								
								var project={
										"id":this.createIssue.project.id
								}
								
								var applicationIssueType={
										"id":this.createIssue.issueType.id
								}
								
								var applicationIssuePriority={
										"id":this.createIssue.issuePriority.id
								}
								
								 var applicationEnvironment={
										"id":this.createIssue.applicationEnvironment.id
								}
								
								//var dueDate= this.createIssue.dueDate;
								alert(new Date(this.createIssue.dueDate));
								console.log(new Date(this.createIssue.dueDate));
								//var date=new Date(dueDate);
								//alert("Angular Date ");
								//alert(date);
								//var year=date.getFullYear();
								//var month=date.getMonth()+1;
								//var day=date.getDate();
								//var newDate=year+"-"+month+"-"+day;
								
								
								var originalEstimate=this.createIssue.originalEstimate;
								var dueDate=new Date(this.createIssue.dueDate);
								//alert(formData);
								
								var jsonDate ={
									"dueDate":dueDate	
								}
								
								var formData={
										"project":project,
										"applicationIssueType":applicationIssueType,
										"applicationIssuePriority":applicationIssuePriority,
										"summary":summary,
										"component":component,
										"affectedVersion":affectedVersion,
										"applicationEnvironment":applicationEnvironment,
										"description":description,
										"assignedUser":assignedUser,
										"originalEstimate":originalEstimate,
										//"dueDate":jsonDate.dueDate
									
										
								};
								
								
								
								
								managerService.submitCreateIssue(formData,dueDate)
								.then(
										function(formData) {
												alert("REgistered");
										},
										function(errResponse)
										{
										console.error('Error while searching issues');
										});
							}
							
							
							// rectify
							var issues = $http({
								method : 'GET',
								url : '../defaultIssuesList'
							}).success(function(data) {
								$scope.issueList = data;
							})
							
							// rectify
							$scope.showMemberList=function(){
								$scope.members1 = [];
								$http({
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
							}

							$scope.showLookUpForm = false;
							$scope.showRegisterForm = false;
							$scope.showNonYashRegisterForm = false;
							$scope.showRegistrationMessaage = false;
							$scope.memberAlreadyInDatabase = false;

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
								$scope.showRegisterForm = false;
								$scope.showNonYashRegisterForm = false;
								$scope.showRegistrationMessaage = false;
								$scope.memberAlreadyInDatabase = false;

							}

							$scope.showRegisterationForm = function() {

								$scope.showLookUpForm = false;
								$scope.showRegisterForm = false;
								$scope.showNonYashRegisterForm = true;
								$scope.showRegistrationMessaage = false;
								$scope.memberAlreadyInDatabase = false;
								$scope.userId = " ";
								$scope.userName = " ";
								$scope.userEmail = " ";
								$scope.userMobile = " ";
								$scope.managerEmail = " ";
							}

							$scope.fetchIssueDetails = function() {

								var index = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();
								

								if (index == null) {
									alert("Please select the entry you want to update!");
								}
								// rectify
								var fetchIssueDetails = $http({
									method : 'GET',
									url : '../fetchIssueDetails/{index}'
								}).success(function(data) {
								
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
							
							
							
							managerService.unassignedIssues().then(function(d) {
								
								alert("In AssigneIssue controller");
								$scope.unassignedIssueList = d;
								
							}

							);
							

							
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

								managerService
										.registerMember($scope.member)
										.then(
												function(d) {
													if (d == false) {
														$scope.memberAlreadyInDatabase = true;
													} else {
														$scope.showRegistrationMessaage = true;
													}

												},

												function(errResponse) {
													console
															.error('Error while fetching');
												}

										)

							}

							$scope.registerNonYashMember = function() {

								$scope.member.memberId = $scope.userId;
								$scope.member.name = $scope.userName;
								$scope.member.email = $scope.userEmail;
								$scope.member.contact = $scope.userMobile;
								$scope.member.managerEmail = $scope.managerEmail;
								$scope.showNonYashRegisterForm = false;

								managerService.registerNonYashMember(
										$scope.member).then(function(d) {

									if (d == false) {
										$scope.memberAlreadyInDatabase = true;
									} else {
										$scope.showRegistrationMessaage = true;
									}

								},

								function(errResponse) {
									console.error('Error while fetching');
								}

								)

							}
							$scope.defaultIssueList = [];
							// rectify
							var issueList = $http({
								method : 'GET',
								url : '../defaultIssues'
							}).success(function(data) {

								$scope.defaultIssueList = data;

							})

							$scope.getMemberType = [];
							// rectify
							var getMemberType = $http({
									
								method : 'GET',
								url : '../memberType'
							}).success(function(data) {

								$scope.getMemberTypes = data;

							})

							$scope.getSearchMember = function() {
								$scope.memberType=undefined;
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

														$scope.getDataAfterActiveStatus();
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
									managerService.memberActivate(memberId)
											.then(
													function(data) {

														if($scope.memberType==undefined){

															$scope.getSearchMember();
															}
															else{	
																$scope.getSearchedMemberType();
															}
													},
													function(errResponse) {
														console
																.error('Error while showing member status');
													})
								}
							}

							$scope.memberDelete = function(indexId) {

								managerService
										.memberDelete(indexId)
										.then(
												function(data) {

													$scope
															.getDataAfterActiveStatus();
												},
												function(errResponse) {
													console
															.error('Error while deleting members');
												})

							}

							$scope.showadvsearch = function() {
								$('#advsearch').show();
								$scope.isDisabled = true;

								managerService
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
								managerService
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
								managerService
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

							/**
							 * Upload file
							 */
							$scope.attachments = [ {
								attachmentFile : ''
							} ];

							$scope.getSearchedMemberType = function() {
								var memberType = $scope.memberType;
								var memberTypeId = 0;
								if (memberType == "Yash") {
									memberTypeId = 1;
								} else if (memberType == "NonYash") {
									memberTypeId = 2;
								} else {
									memberTypeId = 3;
								}
								
								managerService
										.searchMemberType(memberTypeId)
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

																			value.isActive = "Active";
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

							$scope.getTheFile1 = function($files) {

								angular.forEach($files, function(value, key) {
									$scope.file1 = $files[0];
									$scope.file1Name = $files[0].name;
									$scope.file1Size = $files[0].size;
								});
								console.log($scope.file1, $scope.file1Name,
										$scope.file1Size);
							};
							

							$scope.uploadFile1 = function() {

								var fileInput = $('#selectFile1');
								var maxSize = fileInput.data('max-size');
								var fileSize = $scope.file1Size;
								var fileName = $scope.file1Name;
								var ext = fileName.split('.').pop();

								var formData = new FormData();
								var attachmentLabel = $scope.attachmentLabel1;
								formData.append("file", $scope.file1);
								formData.append("attachmentLabel",
										attachmentLabel);

								switch (ext) {
								case 'jpg':
								case 'jpeg':
								case 'png':
								case 'gif':
								case 'doc':
								case 'docx':
								case 'txt':
								case 'pdf':
								case 'xls':
								case 'xlsx':
								case 'sql':
									/*
									 * case 'java': case 'xml':
									 */
									break;
								default:
									alert('File type not allowed.');
									$("#selectFile1").val("");
									return false;
								}

								if (fileSize > maxSize) {
									alert('File size is too big ! Size should be less than 1 MB');
									$("#selectFile1").val("");
									return false;
								}
								$scope.attachments.push({
									attachmentFile : $scope.file1Name,

								});

								managerService
										.fileUpload(formData)
										.then(
												function(data) {
													$scope.assignedIssues = data;
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

								managerService
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

								managerService
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

							$scope.assigneeList = [];

							var members = $http({
								method : 'GET',
								url : '../getMembers'
							}).success(function(data) {
								$scope.assigneeList = data;
								
							})

							this.assignIssue = {};
							this.assignIssueSave = function() {
								alert("In assign Issue");
								var id = this.issueId;
								var originalEstimate = this.assignIssue.originalEstimate;
								var dueDate=new Date(this.assignIssue.dueDate);
								
								var remainingEstimate = this.assignIssue.originalEstimate;
								
								alert("assignee");
								
								
								alert(this.assignIssue.assignedUser.member.memberId);
								
								var member = {
										"memberId":this.assignIssue.assignedUser.member.memberId
								}
								
								var assignedUser={
										"member":member
									}
								
								
								var formData = {
									"id" : id,
									"originalEstimate" : originalEstimate,
									
									"remainingEstimate" : remainingEstimate,
									"assignedUser" : assignedUser
								};
								
								//alert(formData.assignedUser.member.memberId);
								alert(dueDate);
								
								managerService.submitAssignedIssue(formData,dueDate)
										.then(function(data) {
											alert("Assigned");
										});
							};

							$scope.fetchIssueDetails = function() {

								var fetchId = angular
										.element(
												document
														.querySelector("input[id=radio]:checked"))
										.val();

								managerService
										.fetchIssueDetails(fetchId)
										.then(
												function(data) {
													$scope.fetchedIssue = data;
												},
												function(errResponse) {
													console
															.error('Error showing fetched issue');
												})
								var fetchedIssue = $http({
									method : 'GET',
									url : '../fetchIssueDetails/' + fetchId
								}).success(function(data) {
								
									$scope.fetchedIssue = data;
								})
							};

							$scope.exportData = function() {
								var blob = new Blob(
										[ document.getElementById('exportable').innerHTML ],
										{
											type : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
										});
								saveAs(blob, "ListOfMembers.xls");
							};
							
							
							 /**
							  * File Upload
							  */
							$scope.attachments=[{ attachmentFile:''}];
							$scope.getTheFile1 = function($files) {
								
							/*	angular.forEach($files, function(value, key) {*/
									 $scope.file1 = $files[0];
									 $scope.file1Name = $files[0].name; 
									 $scope.file1Size = $files[0].size;
							/*	});*/
								console.log($scope.file1,$scope.file1Name,$scope.file1Size);
							}
							
							
							$scope.uploadFile1 = function() {
								var fileInput = $('#selectFile1');
								var maxSize = fileInput.data('max-size');
								var fileSize=$scope.file1Size;
								var fileName=$scope.file1Name;
								var ext = fileName.split('.').pop();

								var attachmentLabel= $scope.attachmentLabel1;
								var formData = new FormData();
								formData.append("file", $scope.file1); 
								formData.append("attachmentLabel", attachmentLabel);
								
							
							    switch (ext) {
							        case 'jpg':
							        case 'jpeg':
							        case 'png':
							        case 'gif':
							        case 'doc':
							        case 'docx':
							        case 'txt':
							        case 'pdf':
							        case 'xls':
							        case 'xlsx':
							        case 'sql':
							        case 'java':
						        	case 'xml': 
							            break;
							        default:
							        	alert('File type not allowed.');
							        	$("#selectFile1").val("");
					                	return false;
							    }
							   
								if(fileSize>maxSize){
						                alert('File size is too big ! Size should be less than 1 MB');
						                $("#selectFile1").val("");
						                return false;
						            }
								$scope.attachments.push({ 
									attachmentFile: $scope.file1Name,
									
								});
								
								managerService.fileUpload(formData)
								.then(
												function(data) {
													alert("hgsdhgsh")
													//$scope.fetchedIssue = data;
												},
												function(errResponse) {
													console
															.error('Error showing fetched issue');
												}
								)

							
							}
							
							$scope.memberDeleteForSearch = function(memberId) {
									
								if (memberId == "") {
									alert("Please Select ID!");
								} else {
									managerService.memberDelete(memberId)
											.then(
													function(data) {
												
														if ($scope.memberType ==undefined) {
														
															$scope.getSearchMember();
														} else {
															
															$scope.getSearchedMemberType();
														}
													},
													function(errResponse) {
														console.error('Error while showing member status');
													})
									}
							}

						} ]);
