angular.module('issueTrackingSystem.managerModule').controller('managerController',['$scope','$http','managerService',function($scope, $http, managerService, issueList) {

							$scope.issueList = [];

					

							this.createIssue = {};
							managerService.initializeSelect().then(function(d) {
								

								$scope.projects = d.projects;
								
							}

							);

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
								managerService
										.initializeSelectAll(this.pId)
										.then(
												function(d) {
										
													$scope.issueTypeList = d.issueType;
													$scope.priorities = d.issuePriority;
													$scope.environments = d.applicationEnvironment;
													$scope.assigneeList = d.applicationTeamMembers;

												});
							}

							this.add = function() {

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
									"id" : this.createIssue.applicationEnvironment.id
								}

								var dueDate = this.createIssue.dueDate;

								var date = new Date(dueDate);
								var year = date.getFullYear();
								var month = date.getMonth() + 1;
								var day = date.getDate();
								var newDate = year + "/" + month + "/" + day;

								var originalEstimate = this.createIssue.originalEstimate;

								var formData = {
									"project" : project,
									"applicationIssueType" : applicationIssueType,
									"applicationIssuePriority" : applicationIssuePriority,
									"summary" : summary,
									"component" : component,
									"affectedVersion" : affectedVersion,
									"applicationEnvironment" : applicationEnvironment,
									"description" : description,
									"issueOwner" : issueOwner,
									"dueDate" : newDate,
									"originalEstimate" : originalEstimate

								};

								managerService
										.submitCreateIssue(formData)
										.then(
												function(formData) {
													alert("Issue Created!!");
												},
												function(errResponse) {
													console
															.error('Error while searching issues');
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

							$scope.unassignedIssueList = [];
							// rectify
							var unassignedIssues = $http({
								method : 'GET',
								url : '../issue/assign'
							}).success(function(data) {

								$scope.unassignedIssueList = data;
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

								var searchText = $scope.searchText;

								if (searchText == undefined) {

									alert("Please Enter Text!");
								} else {
									managerService.searchMember(searchText)
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
								// rectify
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
													// $scope.application=data;
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
								
								console.log($scope.memberType);
								var memberType = $scope.memberType;
								var memberTypeId=0;
								if(memberType=="Yash"){
									memberTypeId=1;	
								}
								else if(memberType=="NonYash"){
									memberTypeId=2;
									
								}
								else{
									memberTypeId=3;
									
								}
							
								managerService.searchMemberType(memberTypeId)
								.then(
										function(data) {
											$scope.members = data;
											
										//	console.log(members);
											angular.forEach(
													$scope.members,
													function(value,key) {
														
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
										}
										
								)
								
								
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
							/*
							 * $scope.getTheFile2 = function($files) {
							 * 
							 * angular.forEach($files, function(value, key) {
							 * alert(key + " " + value); $scope.file2 =
							 * $files[0]; $scope.file2Name = $files[0].name;
							 * $scope.file2Size = $files[0].size; });
							 * console.log($scope.file2,$scope.file2Name,$scope.file2Size); };
							 * $scope.getTheFile3 = function($files) {
							 * 
							 * angular.forEach($files, function(value, key) {
							 * alert(key + " " + value); $scope.file3 =
							 * $files[0]; $scope.file3Name = $files[0].name;
							 * $scope.file3Size = $files[0].size; });
							 * console.log($scope.file3,$scope.file3Name,$scope.file3Size); };
							 */

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

								/*
								 * $scope.uploadFile2 = function() {
								 * 
								 * var fileInput = $('#selectFile2'); var
								 * maxSize = fileInput.data('max-size'); var
								 * fileSize=$scope.file2Size; var
								 * fileName=$scope.file2Name; var ext =
								 * fileName.split('.').pop();
								 * 
								 * var formData = new FormData(); var
								 * attachmentLabel= $scope.attachmentLabel2;
								 * formData.append("file", $scope.file2);
								 * formData.append("attachmentLabel",
								 * attachmentLabel);
								 * 
								 * switch (ext) { case 'jpg': case 'jpeg': case
								 * 'png': case 'gif': case 'doc': case 'docx':
								 * case 'txt': case 'pdf': case 'xls': case
								 * 'xlsx': case 'sql': case 'java': case 'xml':
								 * break; default: alert('File type not
								 * allowed.'); $("#selectFile2").val(""); return
								 * false; }
								 * 
								 * if(fileSize>maxSize){ alert(' Too big file
								 * size ! Size should be less than 1 MB');
								 * $("#selectFile2").val(""); return false; }
								 * 
								 * $scope.attachments.push({ attachmentFile:
								 * $scope.file2Name,
								 * 
								 * });
								 * 
								 * var request = { method : 'POST', url :
								 * '../uploadFile', data : formData, headers : {
								 * 'Content-Type' : undefined } };
								 * 
								 * $http(request).success(function(data, status) {
								 * alert("File Uploaded Successfully ... " +
								 * status);
								 * 
								 * }).error(function(data, status) {
								 * 
								 * }); }
								 * 
								 * $scope.uploadFile3 = function() {
								 * 
								 * var fileInput = $('#selectFile3'); var
								 * maxSize = fileInput.data('max-size'); var
								 * fileSize=$scope.file3Size; var
								 * fileName=$scope.file3Name; var ext =
								 * fileName.split('.').pop();
								 * 
								 * var formData = new FormData(); var
								 * attachmentLabel = $scope.attachmentLabel3;
								 * formData.append("file", $scope.file3);
								 * formData.append("attachmentLabel",
								 * attachmentLabel);
								 * 
								 * switch (ext) { case 'jpg': case 'jpeg': case
								 * 'png': case 'gif': case 'doc': case 'docx':
								 * case 'txt': case 'pdf': case 'xls': case
								 * 'xlsx': case 'sql': case 'java': case 'xml':
								 * break; default: alert('File type not
								 * allowed.'); $("#selectFile3").val(""); return
								 * false; }
								 * 
								 * if(fileSize>maxSize){ alert(' Too big file
								 * size ! Size should be less than 1 MB');
								 * $("#selectFile3").val(""); return false; }
								 * 
								 * $scope.attachments.push({
								 * attachmentFile:$scope.file3Name,
								 * 
								 * });
								 * 
								 * var request = { method : 'POST', url :
								 * '../uploadFile', data : formData, headers : {
								 * 'Content-Type' : undefined } };
								 * $http(request).success(function(data, status) {
								 * alert("File Uploaded Successfully ... " +
								 * status);
								 * 
								 * }).error(function(data, status) {
								 * 
								 * });
								 */
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
							this.assignIssue = function() {

								var id = this.issueId;
								var originalEstimate = this.assignIssue.originalEstimate;
								var dueDate = this.assignIssue.dueDate;
								var date = new Date(dueDate);
								var year = date.getFullYear();
								var month = date.getMonth() + 1;
								var day = date.getDate();
								var newDate = year + "/" + month + "/" + day;
								// var newDate1=new Date(newDate);
								var remainingEstimate = this.assignIssue.originalEstimate;
								var assignedUser = this.assignIssue.assignedUser;

								var formData = {
									"id" : id,
									"originalEstimate" : originalEstimate,
									"dueDate" : newDate,
									"remainingEstimate" : remainingEstimate,
									"assignedUser" : {
										"member" : {
											"id" : assignedUser
										}
									},
								};

								managerService.submitAssignedIssue(formData)
										.then(function(formData) {
											alert("Assigned");
										}/*
											 * , function(errResponse) {
											 * console.error('Error while
											 * assigning issue controller'); }
											 */);
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
								
								managerService
								.fileUpload(formData)
								.then(
										function(data) {
										},
										function(errResponse) {
											
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
