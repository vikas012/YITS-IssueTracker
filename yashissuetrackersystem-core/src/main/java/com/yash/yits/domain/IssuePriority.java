package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the issuepriority database table.
 * 
 */
@Entity
@NamedQuery(name="IssuePriority.findAll", query="SELECT i FROM IssuePriority i")
public class IssuePriority implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ISSUEPRIORITY_ID")
	private int issuePriorityId;

	@Column(name="ISSUEPRIORITY_TYPE")
	private String issuePriorityType;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="issuePriority")
	private List<Issue> issues;

	public IssuePriority() {
	}

	public int getIssuePriorityId() {
		return issuePriorityId;
	}

	public void setIssuePriorityId(int issuePriorityId) {
		this.issuePriorityId = issuePriorityId;
	}

	public String getIssuePriorityType() {
		return this.issuePriorityType;
	}

	public void setIssuePriorityType(String issuePriorityType) {
		this.issuePriorityType = issuePriorityType;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setIssuePriority(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setIssuePriority(null);

		return issue;
	}

}