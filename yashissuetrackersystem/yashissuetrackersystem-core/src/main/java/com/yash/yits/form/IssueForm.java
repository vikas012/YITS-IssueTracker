package com.yash.yits.form;

import java.util.Date;

public class IssueForm {
	
	private int issueDetailId;
	
	private String issueAffectedVersion;

	private String issueComponent;

	private String issueDescription;

	private Date issueCreationDate;

	private Date issueDueDate;

	private String issueEnvironment;

	private int issueOriginalEstimate;

	private int issueRemainingEstimate;

	private String issueSummary;

	private IssueAssignedStatusForm issueAssignedStatus;

	private UserForm user;

	private IssueTypeForm issueType;

	private ProjectForm project;

	private IssuePriorityForm issuePriority;

	private IssueStatusForm issueStatus;

//	private List<AttachmentForm> attachments;

	public IssueForm() {
		
		this.issueAssignedStatus = new IssueAssignedStatusForm();
		this.user = new UserForm();
		this.issueType = new IssueTypeForm();
		this.project = new ProjectForm();
		this.issuePriority = new IssuePriorityForm();
		this.issueStatus = new IssueStatusForm();
	}

	public int getIssueDetailId() {
		return issueDetailId;
	}

	public void setIssueDetailId(int issueDetailId) {
		this.issueDetailId = issueDetailId;
	}

	public String getIssueAffectedVersion() {
		return issueAffectedVersion;
	}

	public void setIssueAffectedVersion(String issueAffectedVersion) {
		this.issueAffectedVersion = issueAffectedVersion;
	}

	public String getIssueComponent() {
		return issueComponent;
	}

	public void setIssueComponent(String issueComponent) {
		this.issueComponent = issueComponent;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public Date getIssueCreationDate() {
		return issueCreationDate;
	}

	public void setIssueCreationDate(Date issueCreationDate) {
		this.issueCreationDate = issueCreationDate;
	}

	public Date getIssueDueDate() {
		return issueDueDate;
	}

	public void setIssueDueDate(Date issueDueDate) {
		this.issueDueDate = issueDueDate;
	}

	public String getIssueEnvironment() {
		return issueEnvironment;
	}

	public void setIssueEnvironment(String issueEnvironment) {
		this.issueEnvironment = issueEnvironment;
	}

	public int getIssueOriginalEstimate() {
		return issueOriginalEstimate;
	}

	public void setIssueOriginalEstimate(int issueOriginalEstimate) {
		this.issueOriginalEstimate = issueOriginalEstimate;
	}

	public int getIssueRemainingEstimate() {
		return issueRemainingEstimate;
	}

	public void setIssueRemainingEstimate(int issueRemainingEstimate) {
		this.issueRemainingEstimate = issueRemainingEstimate;
	}

	public String getIssueSummary() {
		return issueSummary;
	}

	public void setIssueSummary(String issueSummary) {
		this.issueSummary = issueSummary;
	}

	public IssueAssignedStatusForm getIssueAssignedStatus() {
		return issueAssignedStatus;
	}

	public void setIssueAssignedStatus(IssueAssignedStatusForm issueAssignedStatus) {
		this.issueAssignedStatus = issueAssignedStatus;
	}

	public UserForm getUser() {
		return user;
	}

	public void setUser(UserForm user) {
		this.user = user;
	}

	public IssueTypeForm getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueTypeForm issueType) {
		this.issueType = issueType;
	}

	public ProjectForm getProject() {
		return project;
	}

	public void setProject(ProjectForm project) {
		this.project = project;
	}

	public IssuePriorityForm getIssuePriority() {
		return issuePriority;
	}

	public void setIssuePriority(IssuePriorityForm issuePriority) {
		this.issuePriority = issuePriority;
	}

	public IssueStatusForm getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(IssueStatusForm issueStatus) {
		this.issueStatus = issueStatus;
	}
}