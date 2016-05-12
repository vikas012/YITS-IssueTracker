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
	private Date createdDateTime;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Date lastModifiedDateTime;

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
	private ApplicationTeamMember lastModifiedBy;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember createdBy;

	public ProjectRelease() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ApplicationTeamMember getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(ApplicationTeamMember lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ApplicationTeamMember getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMember createdBy) {
		this.createdBy = createdBy;
	}

}