<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!--   <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
 -->
<style>
	.clear{
		clear: both;
	}
	.dropDown{
		height: 36px;
		border-color: silver;
		border-radius: 4px;
	}
	.submitButton{
		margin-left: 31%;
	}
	.container{
		height:50%;
		width:50%;
	}
</style>

<div class="container">
  <form role="form" ng-submit="uc.add()">
		<div class="box1">
			<div class="form-group col-sm-4">
				<label for="issueProject"><spring:message code="issue.project"/></label>
				<select ng-model="uc.createIssue.project.projectId" class="dropDown form-control">
					<option ng-repeat="project in projects" value="{{project.projectId}}" >{{project.projectName}}</option>
				</select>
		   </div>
		   <div class="form-group col-sm-4">
				<label for="issueType"><spring:message code="issue.type"/></label>
				<select ng-model="uc.createIssue.issueType.issueId" class="dropDown form-control">
					<option ng-repeat="issueType in issueTypeList" value="{{issueType.issueId}}" >{{issueType.issueType}}</option>
				</select>
		   </div>
		   <div class="form-group col-sm-4">
				<label for="issuePriority"><spring:message code="issue.priority"/></label>
				<select ng-model="uc.createIssue.issuePriority.issuePriorityId" class="dropDown form-control">
					<option ng-repeat="priority in priorities" value="{{priority.issuePriorityId}}">{{priority.issuePriorityType}}</option>
				</select>
		   </div>
		</div>
		<div class="form-group col-sm-12">
			<label for="summary"><spring:message code="issue.summary"/></label>
			<input type="text" ng-model="uc.createIssue.issueSummary" class="form-control" />
	   </div>
	  <div class="box2 ">
		   <div class="form-group col-sm-6">
			<label for="creationDate"><spring:message code="issue.creationDate"/></label>
			<input type="date" ng-model="uc.createIssue.issueCreationDate" class="form-control" disabled/>
		  </div>
		  <div class="form-group col-sm-6">
			<label for="dueDate"><spring:message code="issue.dueDate"/></label>
			<input type="date" ng-model="uc.createIssue.issueDueDate" class="form-control" />
		  </div>
	  </div>
	  <div class="box3 ">
		   <div class="form-group col-sm-4">
			<label for="component"><spring:message code="issue.component"/></label>
			<input type="text" ng-model="uc.createIssue.issueComponent" class="form-control" />
		  </div>
		  <div class="form-group col-sm-4">
			<label for="affectedVersion"><spring:message code="issue.affectedVersion"/></label>
			<input type="text" ng-model="uc.createIssue.issueAffectedVersion" class="form-control" />
		  </div>
		  <div class="form-group col-sm-4">
			<label for="environment"><spring:message code="issue.environment"/></label>
			<input type="text" ng-model="uc.createIssue.issueEnvironment" class="form-control" />
		  </div>
	  </div>
	  <div class="box4">
			<div class="form-group col-sm-12">
				<label for="description"><spring:message code="issue.Description"/></label>
				<textarea class="form-control" rows="5" ng-model="uc.createIssue.issueDescription" class="form-control" ></textarea>
			</div>
	  </div>
	  <%-- <div class="box5">
		   <!-- <div class="form-group col-sm-6">
			<label for="email">Email address:</label>
			<input type="email" class="form-control" id="email">
		  </div> -->
		  <div class="form-group col-sm-4">
				<label for="issueProject"><spring:message code="issue.assignee"/></label>
				<select class="dropDown form-control" ng-model="uc.createIssue.user.userId" class="form-control" >
					<option value="{{assignee.userId}}" ng-repeat="assignee in assigneeList" value="{{assignee.userId}}">{{assignee.userName}}</option>
				</select>
		   </div>
	  </div> --%>
	  <div class="form-group submitButton">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary" value="<spring:message code="issue.save"/>"><spring:message code="issue.save"/></button>
      </div>
      </div>
	</form>
</div>