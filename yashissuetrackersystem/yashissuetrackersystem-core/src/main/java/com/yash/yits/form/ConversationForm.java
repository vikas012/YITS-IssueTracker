package com.yash.yits.form;

import java.sql.Timestamp;

public class ConversationForm {
	
	private int id;

	private Timestamp createdDateTime;

	private int isActive;

	private String message;

	private IssueForm issue;

	private ApplicationTeamMemberForm createdBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IssueForm getIssue() {
		return issue;
	}

	public void setIssue(IssueForm issue) {
		this.issue = issue;
	}

	public ApplicationTeamMemberForm getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMemberForm createdBy) {
		this.createdBy = createdBy;
	}
}
