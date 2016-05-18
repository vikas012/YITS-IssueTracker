function testInterceptor() {
	return {
		request: function(config) {
			console.log("In Request Processing");
			return config;
		},
		requestError: function(config){
			console.error('Error while requesting for data');
			return config;
		},
		response: function(res) {
			console.log("In Response Processing");
			return res;
		},
		responseError: function(res) {
			console.error('Error while fetching members');
			return $q.reject(res);
		}
	}
}




var managerModule = angular.module('issueTrackingSystem.managerModule',['ngRoute','datatables']);

managerModule.factory('testInterceptor', testInterceptor)
managerModule.config(function($httpProvider) {
	$httpProvider.interceptors.push('testInterceptor');
})
managerModule.config(function($routeProvider) {


	$routeProvider

	.when('/addMember', {
		templateUrl : '../showYashForm',
		controller : 'AddMemberManagerController'
	})

	.when('/assignIssue', {
		templateUrl : '../getAssignIssueForm',
		controller : 'AssignedIssueController',
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