angular.module('issueTrackingSystem.managerModule').controller('AssignedIssueController',['$scope','AssignedIssueService',function($scope,AssignedIssueService){

	AssignedIssueService.unassignedIssues().then(function(d) {
		
		alert("In AssigneIssue controller");
		$scope.unassignedIssueList = d;
		
	}

	);
	
	$scope.showAssignedIssues=function(){
		AssignedIssueService
			.showAssignedIssues()
			.then(
					function(data) {
						$scope.assignedIssues = data;
					},
					function(errResponse) {
						console
								.error('Error while showing assigned issues');
					})
	}
	
	$scope.getSearchAssignedIssue = function() {
		
		var searchText = $scope.searchAssignedIssueText;

		if (searchText == "") {

			alert("Please Enter Text!");
		} else {
			AssignedIssueService
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
	
}]);