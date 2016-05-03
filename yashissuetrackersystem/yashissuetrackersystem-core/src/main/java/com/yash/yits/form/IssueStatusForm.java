package com.yash.yits.form;

public class IssueStatusForm{
	
	private int issueStatusId;

	private String issueStatusType;

	public IssueStatusForm() {
	}

	public int getIssueStatusId() {
		return this.issueStatusId;
	}

	public void setIssueStatusId(int issueStatusId) {
		this.issueStatusId = issueStatusId;
	}

	public String getIssueStatusType() {
		return this.issueStatusType;
	}

	public void setIssueStatusType(String issueStatusType) {
		this.issueStatusType = issueStatusType;
	}
}