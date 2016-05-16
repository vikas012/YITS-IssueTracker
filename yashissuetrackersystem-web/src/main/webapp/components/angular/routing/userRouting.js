var userModule = angular.module('issueTrackingSystem.userModule',['ngRoute']);
userModule.config(function($routeProvider){
		$routeProvider
			.when('/createIssue',{
				templateUrl:'../showCreateIssueForm',
				controller:'userController as uc'
			})

			.when('/searchIssues', {
		templateUrl : '../issues',
		controller : 'userController as uc',
		
	})
		

			

			.when('/editIssues',{
				templateUrl:'showEditIssueForm',
				controller:'userController as uc'
			})
			

	});