package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the project_release database table.
 * 
 */
@Entity
@Table(name="project_release")
@NamedQuery(name="ProjectRelease.findAll", query="SELECT p FROM ProjectRelease p")
public class ProjectRelease implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATE_TIME")
	private Timestamp createdDateTime;

	private Object isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="RELEASE_DATE")
	private Date releaseDate;

	private String version;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="projectRelease", fetch=FetchType.EAGER)
	private List<Issue> issues;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember applicationTeamMember2;

	public ProjectRelease() {
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

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setProjectRelease(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setProjectRelease(null);

		return issue;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
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

}