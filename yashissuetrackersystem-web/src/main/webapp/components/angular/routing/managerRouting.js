var managerModule = angular.module('issueTrackingSystem.managerModule',['ngRoute']);
managerModule.config(function($routeProvider){
	alert("routing");
		$routeProvider
			.when('/addMember',{
				
				templateUrl:'showYashForm',
				controller:'managerController as mc'
			})
			
			
			
			.when('/showMembers',{
				templateUrl:'showMembersPage',
				controller:'managerController as mc',
				resolve: {
					memberList:function(managerService){
						return managerService.getMembers();
					}
				}
				})
	});