angular.module('issueTrackingSystem.managerModule').controller('managerController',['$scope','$http','managerService',function($scope, $http, managerService, issueList,unassignedIssueList) {
							
							
							
							$scope.showLookUpForm = false;
							$scope.showRegisterForm = false;
							$scope.showNonYashRegisterForm=false;
							
							$scope.ldapUser = {ldapName : "",ldapEmail : ""};
							$scope.members = [];
							$scope.member = {memberId : "",name : "",email : "",contact : "",managerEmail:""};
							$scope.userId = "";
							$scope.userName = "";
							$scope.userEmail = "";
							$scope.userMobile = "";
							
							$scope.showLookForm = function() {

								$scope.showLookUpForm = true;
								$scope.showNonYashRegisterForm =false;

							}
							$scope.showRegisterationForm=function(){
								
								$scope.showLookUpForm =false;
								$scope.showRegisterForm = false;
								$scope.showNonYashRegisterForm = true;
							}
							$scope.checkUser = function() {

								$scope.ldapUser.ldapName = $scope.ldapName;
								$scope.ldapUser.ldapEmail = $scope.ldapEmail;

								$scope.showLookUpForm = false;
								$scope.showRegisterForm = true;
								$scope.checkUserInLdap($scope.ldapUser);

							}
							$scope.members1 = [];
							
							 var issues=$http({
								  method:'GET',
								      url:'../memberList' 
								  }).success(function(data){
									  
									  $scope.members1=data;
								  })
							$scope.checkUserInLdap = function(ldapUser) {

								managerService.checkUserInLdap(ldapUser)
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

							}
							$scope.registerMember = function() {

								$scope.member.memberId = $scope.userId;
								$scope.member.name = $scope.userName;
								$scope.member.email = $scope.userEmail;
								$scope.member.contact = $scope.userMobile;
								$scope.showRegisterForm = false;
								
								managerService.registerMember($scope.member);

							}
							$scope.registerNonYashMember=function(){
								
								$scope.member.memberId = $scope.userId;
								$scope.member.name = $scope.userName;
								$scope.member.email = $scope.userEmail;
								$scope.member.contact = $scope.userMobile;
								$scope.member.managerEmail=$scope.managerEmail;
								$scope.showNonYashRegisterForm=false;
							
								managerService.registerNonYashMember($scope.member);
								
								
								
							}
							$scope.deleteMember=function(){
								
								
								
							}
							
							
							$scope.getSearchMember = function() {
								alert("Please Enter Text controller!");
								alert($scope.searchText);
								var searchText = $scope.searchText;
								
								if (searchText == undefined) {
									alert("Please Enter Text!");
								}
								else{
									managerService.searchMember(searchText)
									.then(
											function(data){
												$scope.members=data;
											},
											 function(errResponse)
											 {
												 console.error('Error while showing search members');
											 }
									)	
								}
							};

							/* issueService returns list to populate drop-down */
							/*
							 * managerService.initializeSelect() .then(
							 * function(d) {
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
							 * while fetching'); } );
							 * 
							 * this.createIssue={}; this.add=function(){
							 *  // call service to persist in db
							 * managerService.submitCreateIssue(this.createIssue);
							 * .then( function(d) {
							 *  }, function(errResponse){ console.error('Error
							 * while fetching'); } ); this.createIssue={}; };
							 * 
							 * this.getIssues = function() {
							 * 
							 * var searchText = this.searchText; if (searchText ==
							 * undefined) { alert("Please Enter Text!") } else{
							 * managerService.searchIssue(searchText) .then(
							 * function(data){ $scope.issues=data; },
							 * function(errResponse) { console.error('Error
							 * while showing default search issues'); } )
							 * 
							 *  } };
							 * 
							 * this.change=function(){ if(this.searchText ==
							 * ""){} else{ $('#datatable').remove(); } };
							 */

						} ]);