package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the issuestatus database table.
 * 
 */
@Entity
@NamedQuery(name="IssueStatus.findAll", query="SELECT i FROM IssueStatus i")
public class IssueStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ISSUESTATUS_ID")
	private int issueStatusId;

	@Column(name="ISSUESTATUS_TYPE")
	private String issueStatusType;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="issueStatus")
	private List<Issue> issues;

	public IssueStatus() {
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

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setIssueStatus(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setIssueStatus(null);

		return issue;
	}

}