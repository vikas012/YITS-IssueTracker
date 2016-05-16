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
								$scope.isDisabled = true;

								userService
										.getList()
										.then(
												function(data) {
													alert(data);
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
							}

							$scope.searchFilter = function() {
								var filterIssueType = $scope.advIssueType;
								var filterProjectName = this.advProject;
								var filterPriority = this.advPriority;
								userService
										.getadvSearchData(filterIssueType,
												filterProjectName,
												filterPriority)
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

												},
												function(errResponse) {
													console
															.error('Error while showing Issue details');
												})

							};
							
							alert("conversation user controller");
							var conversation = $http({
								method : 'GET',
								url : '../conversationList'
							}).success(function(data) {
								alert(data);

								$scope.conversationList = data;
							})
							
							$scope.fetchIssueDetailsConv=function(id){
								alert(id);
								userService.fetchIssueDetailsConv(id)
								.then(
										function(data){
									
										},
										 function(errResponse)
										 {
											console.error('Error while fetchIssueDetails members');
										 }
								)	
								
								
							},
							
							
							
							/**
							 * Upload file
							 */
							$scope.attachments=[{ attachmentFile:''}];
							

							$scope.getSearchedMemberType = function() {	
								var memberType = $scope.memberType;
								var memberId=0;
								if(memberType=="Yash"){
									memberId=1;	
								}
								else if(memberType=="NonYash"){
									memberId=2;
								
								}
								else{
									memberId=3;
									
								}
								alert(memberId);
								managerService.searchMemberType(memberId)
											.then(
													function(data) {
														$scope.members = data;
														console.log(members[0].memberType.id);
														/*
														angular.forEach($scope.members,function(value,key){
															
															console.log(value.memberType.id);
															
														});*/
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
								console.log($scope.file1,$scope.file1Name,$scope.file1Size);
							};
							/*$scope.getTheFile2 = function($files) {
								
								angular.forEach($files, function(value, key) {
									alert(key + " " + value);
									 $scope.file2 = $files[0];
									 $scope.file2Name = $files[0].name; 
									 $scope.file2Size = $files[0].size; 
								});
								console.log($scope.file2,$scope.file2Name,$scope.file2Size);
							};
							$scope.getTheFile3 = function($files) {
								
								angular.forEach($files, function(value, key) {
									alert(key + " " + value);
									$scope.file3 = $files[0];
									$scope.file3Name = $files[0].name; 
									$scope.file3Size = $files[0].size; 
								});
								console.log($scope.file3,$scope.file3Name,$scope.file3Size);
							};*/
							
							$scope.uploadFile1 = function() {
								
								var fileInput = $('#selectFile1');
								var maxSize = fileInput.data('max-size');
								var fileSize=$scope.file1Size;
								var fileName=$scope.file1Name;
								var ext = fileName.split('.').pop();
								
								var formData = new FormData();
								var attachmentLabel= $scope.attachmentLabel1;
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
							       /*  case 'java':
						        	case 'xml': */
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
											$scope.assignedIssues = data;
										},
										function(errResponse) {
											console
													.error('Error while searching assigned issues');
										}
								)

							/*$scope.uploadFile2 = function() {

								var fileInput = $('#selectFile2');
								var maxSize = fileInput.data('max-size');
								var fileSize=$scope.file2Size;
								var fileName=$scope.file2Name;
								var ext = fileName.split('.').pop();
								
								var formData = new FormData();
								var attachmentLabel= $scope.attachmentLabel2;
								formData.append("file", $scope.file2); 
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
						        		$("#selectFile2").val("");
					            		return false;
						    	}
								
								if(fileSize>maxSize){
						                alert(' Too big file size ! Size should be less than 1 MB');
						                $("#selectFile2").val("");
						                return false;
						            }
								
								$scope.attachments.push({ 
									attachmentFile: $scope.file2Name,
								
								});
								
								var request = {
									method : 'POST',
									url : '../uploadFile',
									data : formData,
									headers : {
										'Content-Type' : undefined
									}
								};
							
								$http(request).success(function(data, status) {
									alert("File Uploaded Successfully ... " + status);

								}).error(function(data, status) {
									
								});
							}

							$scope.uploadFile3 = function() {
								
								var fileInput = $('#selectFile3');
								var maxSize = fileInput.data('max-size');
								var fileSize=$scope.file3Size;
								var fileName=$scope.file3Name;
								var ext = fileName.split('.').pop();
								
								var formData = new FormData();
								var attachmentLabel = $scope.attachmentLabel3;
								formData.append("file", $scope.file3);
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
						        		$("#selectFile3").val("");
					            		return false;
						    	}

								if(fileSize>maxSize){
						            alert(' Too big file size ! Size should be less than 1 MB');
						            $("#selectFile3").val("");
						            return false;
						         }
								
								$scope.attachments.push({ 
									attachmentFile:$scope.file3Name,
									
								});
								
								var request = {
									method : 'POST',
									url : '../uploadFile',
									data : formData,
									headers : {
										'Content-Type' : undefined
									}
								};
								$http(request).success(function(data, status) {
									alert("File Uploaded Successfully ... " + status);
									
								}).error(function(data, status) {
									
								});*/
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