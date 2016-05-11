var managerModule = angular.module('issueTrackingSystem.managerModule',
		[ 'ngRoute' ]);
managerModule.config(function($routeProvider) {
	$routeProvider.when('/addMember', {

		templateUrl : 'showYashForm',
		controller : 'managerController as mc'
	})

	.when('/showMembers', {

		templateUrl : 'showMembersPage',
		controller : 'managerController as mc'
	})

	.when('/issues', {
		templateUrl : 'issues',
		controller : 'managerController as mc',
		resolve : {
			issueList : function(managerService) {
				return managerService.getIssues();
			}
		}
	})
});