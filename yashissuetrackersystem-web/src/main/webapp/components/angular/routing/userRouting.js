var userModule = angular.module('issueTrackingSystem.userModule',['ngRoute','datatables']);
userModule.config(function($routeProvider){
		$routeProvider
			.when('/createIssue',{
				templateUrl:'showCreateIssueForm',
				controller:'userController as uc'
			})
			.when('/issues', {
		templateUrl : '../searchIssues',
		controller : 'userController as uc',
		
	})
		
			
	});