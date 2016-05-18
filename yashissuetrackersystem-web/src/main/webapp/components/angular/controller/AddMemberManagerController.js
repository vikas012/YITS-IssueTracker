angular.module('issueTrackingSystem.managerModule').controller('AddMemberManagerController',['$scope','AddMemberManagerService',function($scope,AddMemberManagerService){

	//setting form and message value true or false 
	$scope.showLookUpForm = true;
	$scope.showRegisterForm = false;
	$scope.showNonYashRegisterForm=false;
	$scope.showRegistrationMessaage=false;
	$scope.memberAlreadyInDatabase=false;

	//declare variables
	$scope.ldapUser = {ldapName : "",ldapEmail : ""};
	$scope.members = [];
	$scope.member = {memberId : "",name : "",email : "",contact : "",managerEmail : ""};
	$scope.userId = "";
	$scope.userName = "";
	$scope.userEmail = "";
	$scope.userMobile = "";

	//operation on showlookForm
	$scope.showLookForm = function() {

		$scope.showLookUpForm = true;
		$scope.showRegisterForm = false;
		$scope.showNonYashRegisterForm =false;
		$scope.showRegistrationMessaage=false;
		$scope.memberAlreadyInDatabase=false;

	}
	//operation on showregistrationForm
	$scope.showRegisterationForm = function() {

		$scope.showLookUpForm =false;
		$scope.showRegisterForm = false;
		$scope.showNonYashRegisterForm = true;
		$scope.showRegistrationMessaage=false;
		$scope.memberAlreadyInDatabase=false;
		$scope.userId =" ";
		$scope.userName =" ";
		$scope.userEmail =" ";
		$scope.userMobile =" ";
		$scope.managerEmail =" ";
	}
	
	//checkUser accept login credentials
	$scope.checkUser = function() {

		$scope.ldapUser.ldapName = $scope.ldapName;
		$scope.ldapUser.ldapEmail = $scope.ldapEmail;

		$scope.showLookUpForm = false;
		$scope.showRegisterForm = true;
		
		//call to checkUserInLdap with login credentials 
		$scope.checkUserInLdap($scope.ldapUser);

	}
	
	//this function calls checkUserInLdap method of AddMemberManagerService and passes login credentials 
	$scope.checkUserInLdap = function(ldapUser) {
		//calling checkUserInLdap method of AddMemberManagerService
		AddMemberManagerService.checkUserInLdap(ldapUser)
				.then(
						//on success
						function(d) {
							
							//getting response in members
							$scope.members = d;
							
							//setting data in register form to get prefilled
							$scope.userId = $scope.members.userId;
							$scope.userName = $scope.members.userName;
							$scope.userEmail = $scope.members.userEmail;
							$scope.userMobile = $scope.members.userMobile;

						},
						
						//on failure
						function(errResponse) {
							console.error('Error while fetching');
						}

				)
	}
	
	//this method accept the register form data
	//call registerMember method of AddMemberManagerService with data
	$scope.registerMember = function() {
		
		$scope.member.memberId = $scope.userId;
		$scope.member.name = $scope.userName;
		$scope.member.email = $scope.userEmail;
		$scope.member.contact = $scope.userMobile;
		$scope.showRegisterForm = false;
		
		//call to registerMember method of AddMemberManagerService
		AddMemberManagerService.registerMember($scope.member)
		.then(
						//on success	
						function(d) {
						
							if(d==false)
							{
								$scope.memberAlreadyInDatabase=true;
							}
							else
							{
								$scope.showRegistrationMessaage=true;
							}

						},
						// on failure
						function(errResponse) {
							console.error('Error while fetching');
						}

				)


	}
	//this method accept the register form data for non-yash Member
	//call registerNonYashMember method of AddMemberManagerService with data
	$scope.registerNonYashMember = function() {

		
		$scope.member.name = $scope.userName;
		$scope.member.email = $scope.userEmail;
		$scope.member.contact = $scope.userMobile;
		$scope.member.managerEmail = $scope.managerEmail;
		$scope.showNonYashRegisterForm = false;

		//call to registerNonYashMember method of AddMemberManagerService
		AddMemberManagerService.registerNonYashMember($scope.member)
		.then(
						// on success
						function(d) {
							
							if(d==false)
							{
								$scope.memberAlreadyInDatabase=true;
							}
							else
							{
								$scope.showRegistrationMessaage=true;
							}

						},
						//on failure
						function(errResponse) {
							console.error('Error while fetching');
						}

				)


	}
	
	
}]);