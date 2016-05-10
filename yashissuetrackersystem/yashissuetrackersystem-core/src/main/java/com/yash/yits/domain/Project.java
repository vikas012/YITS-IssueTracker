package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDateTime;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDateTime;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<Issue> issues;

	//bi-directional many-to-one association to Application
	@ManyToOne
	private Application application;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="OWNER")
	private ApplicationTeamMember applicationOwner;

	//bi-directional many-to-one association to ApplicationProjectStatus
	@ManyToOne
	@JoinColumn(name="APPLICATION_STATUS_ID")
	private ApplicationProjectStatus applicationProjectStatus;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember lastModifiedBy;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember createdBy;

	//bi-directional many-to-many association to ApplicationTeamMember
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="project_member"
		, joinColumns={
			@JoinColumn(name="PROJECT_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="MEMBER_ID")
			}
		)
	private List<ApplicationTeamMember> applicationTeamMembers;

	//bi-directional many-to-one association to ProjectRelease
	@OneToMany(mappedBy="project", fetch=FetchType.EAGER)
	private List<ProjectRelease> projectReleases;

	public Project() {
	}

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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Timestamp getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Timestamp lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public ApplicationTeamMember getApplicationOwner() {
		return applicationOwner;
	}

	public void setApplicationOwner(ApplicationTeamMember applicationOwner) {
		this.applicationOwner = applicationOwner;
	}

	public ApplicationProjectStatus getApplicationProjectStatus() {
		return applicationProjectStatus;
	}

	public void setApplicationProjectStatus(ApplicationProjectStatus applicationProjectStatus) {
		this.applicationProjectStatus = applicationProjectStatus;
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

	public List<ApplicationTeamMember> getApplicationTeamMembers() {
		return applicationTeamMembers;
	}

	public void setApplicationTeamMembers(List<ApplicationTeamMember> applicationTeamMembers) {
		this.applicationTeamMembers = applicationTeamMembers;
	}

	public List<ProjectRelease> getProjectReleases() {
		return projectReleases;
	}

	public void setProjectReleases(List<ProjectRelease> projectReleases) {
		this.projectReleases = projectReleases;
	}

}