package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the issueassignedstatus database table.
 * 
 */
@Entity
@NamedQuery(name="IssueAssignedStatus.findAll", query="SELECT i FROM IssueAssignedStatus i")
public class IssueAssignedStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ISSUEASSIGNMENT_STATUS_ID")
	private int issueAssignmentStatusId;

	@Column(name="ISSUEASSIGNMENT_STATUS")
	private String issueAssignmentStatus;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="issueAssignedStatus")
	private List<Issue> issues;

	public IssueAssignedStatus() {
	}

	public int getIssueAssignmentStatusId() {
		return this.issueAssignmentStatusId;
	}

	public void setIssueassignmentStatusId(int issueAssignmentStatusId) {
		this.issueAssignmentStatusId = issueAssignmentStatusId;
	}

	public String getIssueassignmentStatus() {
		return this.issueAssignmentStatus;
	}

	public void setIssueassignmentStatus(String issueAssignmentStatus) {
		this.issueAssignmentStatus = issueAssignmentStatus;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setIssueAssignedStatus(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setIssueAssignedStatus(null);

		return issue;
	}

}