package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the application database table.
 * 
 */
@Entity
@NamedQuery(name="Application.findAll", query="SELECT a FROM Application a")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDatetime;

	private int isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	private String name;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember applicationTeamMember2;

	//bi-directional many-to-one association to ApplicationEnvironment
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<ApplicationEnvironment> applicationEnvironments;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<ApplicationIssuePriority> applicationIssuePriorities;

	//bi-directional many-to-one association to ApplicationIssueStatus
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<ApplicationIssueStatus> applicationIssueStatuses;

	//bi-directional many-to-one association to ApplicationIssueType
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<ApplicationIssueType> applicationIssueTypes;

	//bi-directional many-to-one association to ApplicationProjectStatus
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<ApplicationProjectStatus> applicationProjectStatuses;

	//bi-directional many-to-one association to ApplicationRelease
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<ApplicationRelease> applicationReleases;

	//bi-directional many-to-one association to ApplicationTeamMember
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<ApplicationTeamMember> applicationTeamMembers;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="application", fetch=FetchType.EAGER)
	private List<Project> projects;

	public Application() {
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

	public List<ApplicationEnvironment> getApplicationEnvironments() {
		return this.applicationEnvironments;
	}

	public void setApplicationEnvironments(List<ApplicationEnvironment> applicationEnvironments) {
		this.applicationEnvironments = applicationEnvironments;
	}

	public ApplicationEnvironment addApplicationEnvironment(ApplicationEnvironment applicationEnvironment) {
		getApplicationEnvironments().add(applicationEnvironment);
		applicationEnvironment.setApplication(this);

		return applicationEnvironment;
	}

	public ApplicationEnvironment removeApplicationEnvironment(ApplicationEnvironment applicationEnvironment) {
		getApplicationEnvironments().remove(applicationEnvironment);
		applicationEnvironment.setApplication(null);

		return applicationEnvironment;
	}

	public List<ApplicationIssuePriority> getApplicationIssuePriorities() {
		return this.applicationIssuePriorities;
	}

	public void setApplicationIssuePriorities(List<ApplicationIssuePriority> applicationIssuePriorities) {
		this.applicationIssuePriorities = applicationIssuePriorities;
	}

	public ApplicationIssuePriority addApplicationIssuePriority(ApplicationIssuePriority applicationIssuePriority) {
		getApplicationIssuePriorities().add(applicationIssuePriority);
		applicationIssuePriority.setApplication(this);

		return applicationIssuePriority;
	}

	public ApplicationIssuePriority removeApplicationIssuePriority(ApplicationIssuePriority applicationIssuePriority) {
		getApplicationIssuePriorities().remove(applicationIssuePriority);
		applicationIssuePriority.setApplication(null);

		return applicationIssuePriority;
	}

	public List<ApplicationIssueStatus> getApplicationIssueStatuses() {
		return this.applicationIssueStatuses;
	}

	public void setApplicationIssueStatuses(List<ApplicationIssueStatus> applicationIssueStatuses) {
		this.applicationIssueStatuses = applicationIssueStatuses;
	}

	public ApplicationIssueStatus addApplicationIssueStatus(ApplicationIssueStatus applicationIssueStatus) {
		getApplicationIssueStatuses().add(applicationIssueStatus);
		applicationIssueStatus.setApplication(this);

		return applicationIssueStatus;
	}

	public ApplicationIssueStatus removeApplicationIssueStatus(ApplicationIssueStatus applicationIssueStatus) {
		getApplicationIssueStatuses().remove(applicationIssueStatus);
		applicationIssueStatus.setApplication(null);

		return applicationIssueStatus;
	}

	public List<ApplicationIssueType> getApplicationIssueTypes() {
		return this.applicationIssueTypes;
	}

	public void setApplicationIssueTypes(List<ApplicationIssueType> applicationIssueTypes) {
		this.applicationIssueTypes = applicationIssueTypes;
	}

	public ApplicationIssueType addApplicationIssueType(ApplicationIssueType applicationIssueType) {
		getApplicationIssueTypes().add(applicationIssueType);
		applicationIssueType.setApplication(this);

		return applicationIssueType;
	}

	public ApplicationIssueType removeApplicationIssueType(ApplicationIssueType applicationIssueType) {
		getApplicationIssueTypes().remove(applicationIssueType);
		applicationIssueType.setApplication(null);

		return applicationIssueType;
	}

	public List<ApplicationProjectStatus> getApplicationProjectStatuses() {
		return this.applicationProjectStatuses;
	}

	public void setApplicationProjectStatuses(List<ApplicationProjectStatus> applicationProjectStatuses) {
		this.applicationProjectStatuses = applicationProjectStatuses;
	}

	public ApplicationProjectStatus addApplicationProjectStatus(ApplicationProjectStatus applicationProjectStatus) {
		getApplicationProjectStatuses().add(applicationProjectStatus);
		applicationProjectStatus.setApplication(this);

		return applicationProjectStatus;
	}

	public ApplicationProjectStatus removeApplicationProjectStatus(ApplicationProjectStatus applicationProjectStatus) {
		getApplicationProjectStatuses().remove(applicationProjectStatus);
		applicationProjectStatus.setApplication(null);

		return applicationProjectStatus;
	}

	public List<ApplicationRelease> getApplicationReleases() {
		return this.applicationReleases;
	}

	public void setApplicationReleases(List<ApplicationRelease> applicationReleases) {
		this.applicationReleases = applicationReleases;
	}

	public ApplicationRelease addApplicationReleas(ApplicationRelease applicationReleas) {
		getApplicationReleases().add(applicationReleas);
		applicationReleas.setApplication(this);

		return applicationReleas;
	}

	public ApplicationRelease removeApplicationReleas(ApplicationRelease applicationReleas) {
		getApplicationReleases().remove(applicationReleas);
		applicationReleas.setApplication(null);

		return applicationReleas;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers() {
		return this.applicationTeamMembers;
	}

	public void setApplicationTeamMembers(List<ApplicationTeamMember> applicationTeamMembers) {
		this.applicationTeamMembers = applicationTeamMembers;
	}

	public ApplicationTeamMember addApplicationTeamMember(ApplicationTeamMember applicationTeamMember) {
		getApplicationTeamMembers().add(applicationTeamMember);
		applicationTeamMember.setApplication(this);

		return applicationTeamMember;
	}

	public ApplicationTeamMember removeApplicationTeamMember(ApplicationTeamMember applicationTeamMember) {
		getApplicationTeamMembers().remove(applicationTeamMember);
		applicationTeamMember.setApplication(null);

		return applicationTeamMember;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setApplication(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setApplication(null);

		return project;
	}

}