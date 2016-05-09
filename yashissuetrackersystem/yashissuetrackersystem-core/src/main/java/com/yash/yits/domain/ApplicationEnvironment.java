package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the application_environment database table.
 * 
 */
@Entity
@Table(name="application_environment")
@NamedQuery(name="ApplicationEnvironment.findAll", query="SELECT a FROM ApplicationEnvironment a")
public class ApplicationEnvironment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATE_TIME")
	private Timestamp createdDateTime;

	private String environment;

	private Object isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

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

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="applicationEnvironment", fetch=FetchType.EAGER)
	private List<Issue> issues;

	public ApplicationEnvironment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
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

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setApplicationEnvironment(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setApplicationEnvironment(null);

		return issue;
	}

}