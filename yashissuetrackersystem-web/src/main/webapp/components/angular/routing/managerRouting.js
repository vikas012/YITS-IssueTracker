var managerModule = angular.module('issueTrackingSystem.managerModule',['ngRoute','datatables']);
managerModule.config(function($routeProvider) {
	
	
	$routeProvider.when('/addMember', {

		templateUrl : '../showYashForm',
		controller : 'AddMemberManagerController'
	})


	.when('/showMembers', {
		
		templateUrl : '../showMembersPage',

		controller : 'managerController',
		
	})
	.when('/searchMembers',{
				
				templateUrl:'../showSearchMember',
				controller:'managerController'
			})

			
	
	.when('/createIssueManager', {
		templateUrl : '../managerShowCreateIssueForm',
		controller : 'managerController as mc'

	})
	.when('/issues', {
		templateUrl : '../issues',
		controller : 'managerController as mc',
		
	})
	.when('/assignIssue', {
		templateUrl : '../getAssignIssueForm',
		controller : 'managerController as mc',
	})
	
	.when('/searchMembers',{
				
				templateUrl:'../showSearchMember',
				controller:'managerController'
			})
			
	.when('/showMembers', {

		templateUrl : '../showMembersPage',
		controller : 'managerController',			
			
		
	})
	
	.when('/showIssues', {

		templateUrl : '../showAssignedIssuePage',
		controller : 'managerController',
	})
	
	.when('/editIssues',{
		templateUrl:'editIssueForm',
		controller:'managerController as mc'
	})
	
});

