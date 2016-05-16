var userModule = angular.module('issueTrackingSystem.userModule',['ngRoute','datatables']);
userModule.config(function($routeProvider){
		$routeProvider
			.when('/createIssue',{
				templateUrl:'../showCreateIssueForm',
				controller:'userController as uc'
			})

			.when('/searchIssues', {
		templateUrl : '../userIssues',
		controller : 'userController as uc',
		
	})
		

			

			.when('/editIssues',{
				templateUrl:'../displayEditIssueForm',
				controller:'userController as uc'
			})
			.when('/showConversation',{
				templateUrl:'../showConversationForm',
				controller:'userController as uc'
			})
			

	});