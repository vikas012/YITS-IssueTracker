package com.yash.yits.form;

import java.sql.Timestamp;
import java.util.Date;

public class IssueActivityLogForm {
	
	private int id;

	private String action;

	private Date createdDateTime;

	private int isActive;

	private Date lastModifiedDateTime;

	private IssueForm issue;

	private ApplicationTeamMemberForm lastModifiedBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	public IssueForm getIssue() {
		return issue;
	}

	public void setIssue(IssueForm issue) {
		this.issue = issue;
	}

	public ApplicationTeamMemberForm getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(ApplicationTeamMemberForm lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
}
