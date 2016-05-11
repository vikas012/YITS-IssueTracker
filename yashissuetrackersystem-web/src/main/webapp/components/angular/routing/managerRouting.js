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

				controller:'managerController as mc'
			})
			.when('/createIssueManager',{
				templateUrl:'../showCreateIssueForm',
				controller:'managerController as mc'
			})
			
			.when('/assignIssue',{
				templateUrl:'../getAssignIssueForm',
				controller:'managerController as mc',
				resolve: {
					unassignedIssueList:function(managerService){
					return managerService.getUnassignedIssues();
					}
				}
			
			})
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker.git
	});