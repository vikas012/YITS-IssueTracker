package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the application_issue_priority database table.
 * 
 */
@Entity
@Table(name="application_issue_priority")
@NamedQuery(name="ApplicationIssuePriority.findAll", query="SELECT a FROM ApplicationIssuePriority a")
public class ApplicationIssuePriority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDatetime;

	private Object isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	private String type;

	//bi-directional many-to-one association to Application
	@ManyToOne
	private Application application;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember applicationTeamMember2;

	//bi-directional many-to-one association to ApplicationSeverity
	@ManyToOne
	@JoinColumn(name="SEVERITY_ID")
	private ApplicationSeverity applicationSeverity;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="applicationIssuePriority", fetch=FetchType.EAGER)
	private List<Issue> issues;

	public ApplicationIssuePriority() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Object getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Object isactive) {
		this.isactive = isactive;
	}

	public Timestamp getLastModifiedDatetime() {
		return this.lastModifiedDatetime;
	}

	public void setLastModifiedDatetime(Timestamp lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public ApplicationTeamMember getApplicationTeamMember1() {
		return this.applicationTeamMember1;
	}

	public void setApplicationTeamMember1(ApplicationTeamMember applicationTeamMember1) {
		this.applicationTeamMember1 = applicationTeamMember1;
	}

	public ApplicationTeamMember getApplicationTeamMember2() {
		return this.applicationTeamMember2;
	}

	public void setApplicationTeamMember2(ApplicationTeamMember applicationTeamMember2) {
		this.applicationTeamMember2 = applicationTeamMember2;
	}

	public ApplicationSeverity getApplicationSeverity() {
		return this.applicationSeverity;
	}

	public void setApplicationSeverity(ApplicationSeverity applicationSeverity) {
		this.applicationSeverity = applicationSeverity;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setApplicationIssuePriority(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setApplicationIssuePriority(null);

		return issue;
	}

}