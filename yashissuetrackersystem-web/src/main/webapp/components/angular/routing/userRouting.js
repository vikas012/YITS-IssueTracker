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




var userModule = angular.module('issueTrackingSystem.userModule',['ngRoute','datatables']);

userModule.factory('testInterceptor', testInterceptor)
userModule.config(function($httpProvider) {
  $httpProvider.interceptors.push('testInterceptor');
})
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