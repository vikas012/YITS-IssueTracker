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
	private Timestamp createdDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	private int isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

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
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationProjectStatus
	@ManyToOne
	@JoinColumn(name="APPLICATION_STATUS_ID")
	private ApplicationProjectStatus applicationProjectStatus;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember2;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember applicationTeamMember3;

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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public Timestamp getLastModifiedDatetime() {
		return this.lastModifiedDatetime;
	}

	public void setLastModifiedDatetime(Timestamp lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public Issue addIssue(Issue issue) {
		getIssues().add(issue);
		issue.setProject(this);

		return issue;
	}

	public Issue removeIssue(Issue issue) {
		getIssues().remove(issue);
		issue.setProject(null);

		return issue;
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

	public ApplicationProjectStatus getApplicationProjectStatus() {
		return this.applicationProjectStatus;
	}

	public void setApplicationProjectStatus(ApplicationProjectStatus applicationProjectStatus) {
		this.applicationProjectStatus = applicationProjectStatus;
	}

	public ApplicationTeamMember getApplicationTeamMember2() {
		return this.applicationTeamMember2;
	}

	public void setApplicationTeamMember2(ApplicationTeamMember applicationTeamMember2) {
		this.applicationTeamMember2 = applicationTeamMember2;
	}

	public ApplicationTeamMember getApplicationTeamMember3() {
		return this.applicationTeamMember3;
	}

	public void setApplicationTeamMember3(ApplicationTeamMember applicationTeamMember3) {
		this.applicationTeamMember3 = applicationTeamMember3;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers() {
		return this.applicationTeamMembers;
	}

	public void setApplicationTeamMembers(List<ApplicationTeamMember> applicationTeamMembers) {
		this.applicationTeamMembers = applicationTeamMembers;
	}

	public List<ProjectRelease> getProjectReleases() {
		return this.projectReleases;
	}

	public void setProjectReleases(List<ProjectRelease> projectReleases) {
		this.projectReleases = projectReleases;
	}

	public ProjectRelease addProjectReleas(ProjectRelease projectReleas) {
		getProjectReleases().add(projectReleas);
		projectReleas.setProject(this);

		return projectReleas;
	}

	public ProjectRelease removeProjectReleas(ProjectRelease projectReleas) {
		getProjectReleases().remove(projectReleas);
		projectReleas.setProject(null);

		return projectReleas;
	}

}