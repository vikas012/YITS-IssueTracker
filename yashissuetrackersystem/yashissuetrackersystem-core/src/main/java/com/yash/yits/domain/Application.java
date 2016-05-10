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
	private Timestamp createdDateTime;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDateTime;

	private String name;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember lastModifiedBy;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember createdBy;

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

	public List<ApplicationEnvironment> getApplicationEnvironments() {
		return applicationEnvironments;
	}

	public void setApplicationEnvironments(List<ApplicationEnvironment> applicationEnvironments) {
		this.applicationEnvironments = applicationEnvironments;
	}

	public List<ApplicationIssuePriority> getApplicationIssuePriorities() {
		return applicationIssuePriorities;
	}

	public void setApplicationIssuePriorities(List<ApplicationIssuePriority> applicationIssuePriorities) {
		this.applicationIssuePriorities = applicationIssuePriorities;
	}

	public List<ApplicationIssueStatus> getApplicationIssueStatuses() {
		return applicationIssueStatuses;
	}

	public void setApplicationIssueStatuses(List<ApplicationIssueStatus> applicationIssueStatuses) {
		this.applicationIssueStatuses = applicationIssueStatuses;
	}

	public List<ApplicationIssueType> getApplicationIssueTypes() {
		return applicationIssueTypes;
	}

	public void setApplicationIssueTypes(List<ApplicationIssueType> applicationIssueTypes) {
		this.applicationIssueTypes = applicationIssueTypes;
	}

	public List<ApplicationProjectStatus> getApplicationProjectStatuses() {
		return applicationProjectStatuses;
	}

	public void setApplicationProjectStatuses(List<ApplicationProjectStatus> applicationProjectStatuses) {
		this.applicationProjectStatuses = applicationProjectStatuses;
	}

	public List<ApplicationRelease> getApplicationReleases() {
		return applicationReleases;
	}

	public void setApplicationReleases(List<ApplicationRelease> applicationReleases) {
		this.applicationReleases = applicationReleases;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers() {
		return applicationTeamMembers;
	}

	public void setApplicationTeamMembers(List<ApplicationTeamMember> applicationTeamMembers) {
		this.applicationTeamMembers = applicationTeamMembers;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	
}