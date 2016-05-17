angular.module('issueTrackingSystem.managerModule').factory('AddMemberManagerService',['$http',function($http,$q,$scope){
	
	var memberid=1605100;
	
	return {
		  
		  
		  		checkUserInLdap:function(ldapUser){
		  			
		  				return $http.post('../checkMemberInLdap',ldapUser)
		  					.then(
		  								function(response){
		  								  return response.data;
		  								},
		  								
		  								function(errResponse){
		  									console.error('Error while fetching users');
		  									return $q.reject(errResponse);
		  								}
		  						);
			  
		  			},
		  			registerMember:function(member){
		  				return $http.post('../registerYashMember',member)
		  				.then(
  								function(response){
  								  return response.data;
  								},
  								function(errResponse){
  									console.error('Error while fetching users');
  									return $q.reject(errResponse);
  								}

  						);
		  			},
		  			
		  			registerNonYashMember:function(member){
		  				
		  				memberid=memberid+1;
		  				member.memberId=memberid;
		  				
		  				return $http.post('../registerMember',member)
		  				.then(
		  							function(response){
		  								return response.data;
		  							},
		  							function(errResponse){
		  								console.error('Error while fetching users');
		  								return $q.reject(errResponse);
		  							}
	
		  					);
		  			},
		  			
		  		
	  			
	}

	
	
	
	
}]);