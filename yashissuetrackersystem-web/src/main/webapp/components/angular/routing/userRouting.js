var userModule = angular.module('issueTrackingSystem.userModule',['ngRoute']);
userModule.config(function($routeProvider){
		$routeProvider
			.when('/addMember',{
				templateUrl:'showYashForm',
				controller:'userController as uc'
			})
	});