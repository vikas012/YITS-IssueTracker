var userModule = angular.module('issueTrackingSystem.userModule',['ngRoute']);
userModule.config(function($routeProvider){
		$routeProvider
			.when('/createIssue',{
				templateUrl:'showCreateIssueForm',
				controller:'userController as uc'
			})
	});