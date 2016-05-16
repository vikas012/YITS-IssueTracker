package com.yash.yits.form;

import java.util.Date;
import java.util.List;


public class IssueForm {
	
	private int id;

	private ApplicationTeamMemberForm assignedUser;

	private String affectedVersion;

	private Date closeDate;

	private String component;

	private Date createdDateTime;

	private String description;
	
	private String taskProgressUpdate;

	private String dueDate;

	private int isActive;

	private Date lastModifiedDateTime;

	private String originalEstimate;

	private String remainingEstimate;

	private String summary;

	private ApplicationTeamMemberForm issueOwner;

	private ApplicationTeamMemberForm lastModifiedBy;

	private ProjectForm project;

	private ApplicationIssueTypeForm applicationIssueType;

	private ApplicationIssuePriorityForm applicationIssuePriority;

	private ApplicationEnvironmentForm applicationEnvironment;

	private ApplicationReleaseForm applicationRelease;

	private ProjectReleaseForm projectRelease;

	private ApplicationIssueStatusForm applicationIssueStatus;

	private ApplicationTeamMemberForm createdBy;
	
	private List<AttachmentForm> attachmentForms;

	public List<AttachmentForm> getAttachmentForms() {
		return attachmentForms;
	}

	public void setAttachmentForms(List<AttachmentForm> attachmentForms) {
		this.attachmentForms = attachmentForms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ApplicationTeamMemberForm getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(ApplicationTeamMemberForm assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getAffectedVersion() {
		return affectedVersion;
	}

	public void setAffectedVersion(String affectedVersion) {
		this.affectedVersion = affectedVersion;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskProgressUpdate() {
		return taskProgressUpdate;
	}

	public void setTaskProgressUpdate(String taskProgressUpdate) {
		this.taskProgressUpdate = taskProgressUpdate;
	}

	

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public String getRemainingEstimate() {
		return remainingEstimate;
	}

	public void setRemainingEstimate(String remainingEstimate) {
		this.remainingEstimate = remainingEstimate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ApplicationTeamMemberForm getIssueOwner() {
		return issueOwner;
	}

	public void setIssueOwner(ApplicationTeamMemberForm issueOwner) {
		this.issueOwner = issueOwner;
	}

	public ApplicationTeamMemberForm getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(ApplicationTeamMemberForm lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ProjectForm getProject() {
		return project;
	}

	public void setProject(ProjectForm project) {
		this.project = project;
	}

	public ApplicationIssueTypeForm getApplicationIssueType() {
		return applicationIssueType;
	}

	public void setApplicationIssueType(ApplicationIssueTypeForm applicationIssueType) {
		this.applicationIssueType = applicationIssueType;
	}

	public ApplicationIssuePriorityForm getApplicationIssuePriority() {
		return applicationIssuePriority;
	}

	public void setApplicationIssuePriority(ApplicationIssuePriorityForm applicationIssuePriority) {
		this.applicationIssuePriority = applicationIssuePriority;
	}

	public ApplicationEnvironmentForm getApplicationEnvironment() {
		return applicationEnvironment;
	}

	public void setApplicationEnvironment(ApplicationEnvironmentForm applicationEnvironment) {
		this.applicationEnvironment = applicationEnvironment;
	}

	public ApplicationReleaseForm getApplicationRelease() {
		return applicationRelease;
	}

	public void setApplicationRelease(ApplicationReleaseForm applicationRelease) {
		this.applicationRelease = applicationRelease;
	}

	public ProjectReleaseForm getProjectRelease() {
		return projectRelease;
	}

	public void setProjectRelease(ProjectReleaseForm projectRelease) {
		this.projectRelease = projectRelease;
	}

	public ApplicationIssueStatusForm getApplicationIssueStatus() {
		return applicationIssueStatus;
	}

	public void setApplicationIssueStatus(ApplicationIssueStatusForm applicationIssueStatus) {
		this.applicationIssueStatus = applicationIssueStatus;
	}

	public ApplicationTeamMemberForm getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMemberForm createdBy) {
		this.createdBy = createdBy;
	}

}