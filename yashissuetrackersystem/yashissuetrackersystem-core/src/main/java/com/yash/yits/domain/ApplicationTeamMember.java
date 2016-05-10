package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the application_team_member database table.
 * 
 */
@Entity
@Table(name="application_team_member")
@NamedQuery(name="ApplicationTeamMember.findAll", query="SELECT a FROM ApplicationTeamMember a")
public class ApplicationTeamMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATE_TIME")
	private Timestamp createdDateTime;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDateTime;

	//bi-directional many-to-one association to Application
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<Application> applications1;

	//bi-directional many-to-one association to Application
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<Application> applications2;

	//bi-directional many-to-one association to ApplicationEnvironment
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ApplicationEnvironment> applicationEnvironments1;

	//bi-directional many-to-one association to ApplicationEnvironment
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ApplicationEnvironment> applicationEnvironments2;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ApplicationIssuePriority> applicationIssuePriorities1;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ApplicationIssuePriority> applicationIssuePriorities2;

	//bi-directional many-to-one association to ApplicationIssueStatus
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ApplicationIssueStatus> applicationIssueStatuses1;

	//bi-directional many-to-one association to ApplicationIssueStatus
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ApplicationIssueStatus> applicationIssueStatuses2;

	//bi-directional many-to-one association to ApplicationIssueType
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ApplicationIssueType> applicationIssueTypes1;

	//bi-directional many-to-one association to ApplicationIssueType
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ApplicationIssueType> applicationIssueTypes2;

	//bi-directional many-to-one association to ApplicationProjectStatus
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ApplicationProjectStatus> applicationProjectStatuses1;

	//bi-directional many-to-one association to ApplicationProjectStatus
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ApplicationProjectStatus> applicationProjectStatuses2;

	//bi-directional many-to-one association to ApplicationRelease
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ApplicationRelease> applicationReleases1;

	//bi-directional many-to-one association to ApplicationRelease
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ApplicationRelease> applicationReleases2;

	//bi-directional many-to-one association to ApplicationSeverity
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ApplicationSeverity> applicationSeverities1;

	//bi-directional many-to-one association to ApplicationSeverity
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ApplicationSeverity> applicationSeverities2;

	//bi-directional many-to-one association to Member
	@ManyToOne
	private Member member;

	//bi-directional many-to-one association to Application
	@ManyToOne
	private Application application;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationTeamMember> applicationTeamMembers1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember applicationTeamMember2;

	//bi-directional many-to-one association to ApplicationTeamMember
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ApplicationTeamMember> applicationTeamMembers2;

	//bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<Attachment> attachments1;

	//bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<Attachment> attachments2;

	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<Conversation> conversations;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="applicationOwner", fetch=FetchType.EAGER)
	private List<Issue> issues1;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<Issue> issues2;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<Issue> issues3;

	//bi-directional many-to-one association to IssueActivityLog
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<IssueActivityLog> issueActivityLogs1;

	//bi-directional many-to-one association to IssueActivityLog
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<IssueActivityLog> issueActivityLogs2;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="applicationOwner", fetch=FetchType.EAGER)
	private List<Project> projects1;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<Project> projects2;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<Project> projects3;

	//bi-directional many-to-many association to Project
	@ManyToMany(mappedBy="applicationTeamMembers", fetch=FetchType.EAGER)
	private List<Project> projects4;

	//bi-directional many-to-one association to ProjectRelease
	@OneToMany(mappedBy="lastModifiedBy", fetch=FetchType.EAGER)
	private List<ProjectRelease> projectReleases1;

	//bi-directional many-to-one association to ProjectRelease
	@OneToMany(mappedBy="createdBy", fetch=FetchType.EAGER)
	private List<ProjectRelease> projectReleases2;

	public ApplicationTeamMember() {
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

	public List<Application> getApplications1() {
		return applications1;
	}

	public void setApplications1(List<Application> applications1) {
		this.applications1 = applications1;
	}

	public List<Application> getApplications2() {
		return applications2;
	}

	public void setApplications2(List<Application> applications2) {
		this.applications2 = applications2;
	}

	public List<ApplicationEnvironment> getApplicationEnvironments1() {
		return applicationEnvironments1;
	}

	public void setApplicationEnvironments1(List<ApplicationEnvironment> applicationEnvironments1) {
		this.applicationEnvironments1 = applicationEnvironments1;
	}

	public List<ApplicationEnvironment> getApplicationEnvironments2() {
		return applicationEnvironments2;
	}

	public void setApplicationEnvironments2(List<ApplicationEnvironment> applicationEnvironments2) {
		this.applicationEnvironments2 = applicationEnvironments2;
	}

	public List<ApplicationIssuePriority> getApplicationIssuePriorities1() {
		return applicationIssuePriorities1;
	}

	public void setApplicationIssuePriorities1(List<ApplicationIssuePriority> applicationIssuePriorities1) {
		this.applicationIssuePriorities1 = applicationIssuePriorities1;
	}

	public List<ApplicationIssuePriority> getApplicationIssuePriorities2() {
		return applicationIssuePriorities2;
	}

	public void setApplicationIssuePriorities2(List<ApplicationIssuePriority> applicationIssuePriorities2) {
		this.applicationIssuePriorities2 = applicationIssuePriorities2;
	}

	public List<ApplicationIssueStatus> getApplicationIssueStatuses1() {
		return applicationIssueStatuses1;
	}

	public void setApplicationIssueStatuses1(List<ApplicationIssueStatus> applicationIssueStatuses1) {
		this.applicationIssueStatuses1 = applicationIssueStatuses1;
	}

	public List<ApplicationIssueStatus> getApplicationIssueStatuses2() {
		return applicationIssueStatuses2;
	}

	public void setApplicationIssueStatuses2(List<ApplicationIssueStatus> applicationIssueStatuses2) {
		this.applicationIssueStatuses2 = applicationIssueStatuses2;
	}

	public List<ApplicationIssueType> getApplicationIssueTypes1() {
		return applicationIssueTypes1;
	}

	public void setApplicationIssueTypes1(List<ApplicationIssueType> applicationIssueTypes1) {
		this.applicationIssueTypes1 = applicationIssueTypes1;
	}

	public List<ApplicationIssueType> getApplicationIssueTypes2() {
		return applicationIssueTypes2;
	}

	public void setApplicationIssueTypes2(List<ApplicationIssueType> applicationIssueTypes2) {
		this.applicationIssueTypes2 = applicationIssueTypes2;
	}

	public List<ApplicationProjectStatus> getApplicationProjectStatuses1() {
		return applicationProjectStatuses1;
	}

	public void setApplicationProjectStatuses1(List<ApplicationProjectStatus> applicationProjectStatuses1) {
		this.applicationProjectStatuses1 = applicationProjectStatuses1;
	}

	public List<ApplicationProjectStatus> getApplicationProjectStatuses2() {
		return applicationProjectStatuses2;
	}

	public void setApplicationProjectStatuses2(List<ApplicationProjectStatus> applicationProjectStatuses2) {
		this.applicationProjectStatuses2 = applicationProjectStatuses2;
	}

	public List<ApplicationRelease> getApplicationReleases1() {
		return applicationReleases1;
	}

	public void setApplicationReleases1(List<ApplicationRelease> applicationReleases1) {
		this.applicationReleases1 = applicationReleases1;
	}

	public List<ApplicationRelease> getApplicationReleases2() {
		return applicationReleases2;
	}

	public void setApplicationReleases2(List<ApplicationRelease> applicationReleases2) {
		this.applicationReleases2 = applicationReleases2;
	}

	public List<ApplicationSeverity> getApplicationSeverities1() {
		return applicationSeverities1;
	}

	public void setApplicationSeverities1(List<ApplicationSeverity> applicationSeverities1) {
		this.applicationSeverities1 = applicationSeverities1;
	}

	public List<ApplicationSeverity> getApplicationSeverities2() {
		return applicationSeverities2;
	}

	public void setApplicationSeverities2(List<ApplicationSeverity> applicationSeverities2) {
		this.applicationSeverities2 = applicationSeverities2;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public ApplicationTeamMember getApplicationTeamMember1() {
		return applicationTeamMember1;
	}

	public void setApplicationTeamMember1(ApplicationTeamMember applicationTeamMember1) {
		this.applicationTeamMember1 = applicationTeamMember1;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers1() {
		return applicationTeamMembers1;
	}

	public void setApplicationTeamMembers1(List<ApplicationTeamMember> applicationTeamMembers1) {
		this.applicationTeamMembers1 = applicationTeamMembers1;
	}

	public ApplicationTeamMember getApplicationTeamMember2() {
		return applicationTeamMember2;
	}

	public void setApplicationTeamMember2(ApplicationTeamMember applicationTeamMember2) {
		this.applicationTeamMember2 = applicationTeamMember2;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers2() {
		return applicationTeamMembers2;
	}

	public void setApplicationTeamMembers2(List<ApplicationTeamMember> applicationTeamMembers2) {
		this.applicationTeamMembers2 = applicationTeamMembers2;
	}

	public List<Attachment> getAttachments1() {
		return attachments1;
	}

	public void setAttachments1(List<Attachment> attachments1) {
		this.attachments1 = attachments1;
	}

	public List<Attachment> getAttachments2() {
		return attachments2;
	}

	public void setAttachments2(List<Attachment> attachments2) {
		this.attachments2 = attachments2;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public List<Issue> getIssues1() {
		return issues1;
	}

	public void setIssues1(List<Issue> issues1) {
		this.issues1 = issues1;
	}

	public List<Issue> getIssues2() {
		return issues2;
	}

	public void setIssues2(List<Issue> issues2) {
		this.issues2 = issues2;
	}

	public List<Issue> getIssues3() {
		return issues3;
	}

	public void setIssues3(List<Issue> issues3) {
		this.issues3 = issues3;
	}

	public List<IssueActivityLog> getIssueActivityLogs1() {
		return issueActivityLogs1;
	}

	public void setIssueActivityLogs1(List<IssueActivityLog> issueActivityLogs1) {
		this.issueActivityLogs1 = issueActivityLogs1;
	}

	public List<IssueActivityLog> getIssueActivityLogs2() {
		return issueActivityLogs2;
	}

	public void setIssueActivityLogs2(List<IssueActivityLog> issueActivityLogs2) {
		this.issueActivityLogs2 = issueActivityLogs2;
	}

	public List<Project> getProjects1() {
		return projects1;
	}

	public void setProjects1(List<Project> projects1) {
		this.projects1 = projects1;
	}

	public List<Project> getProjects2() {
		return projects2;
	}

	public void setProjects2(List<Project> projects2) {
		this.projects2 = projects2;
	}

	public List<Project> getProjects3() {
		return projects3;
	}

	public void setProjects3(List<Project> projects3) {
		this.projects3 = projects3;
	}

	public List<Project> getProjects4() {
		return projects4;
	}

	public void setProjects4(List<Project> projects4) {
		this.projects4 = projects4;
	}

	public List<ProjectRelease> getProjectReleases1() {
		return projectReleases1;
	}

	public void setProjectReleases1(List<ProjectRelease> projectReleases1) {
		this.projectReleases1 = projectReleases1;
	}

	public List<ProjectRelease> getProjectReleases2() {
		return projectReleases2;
	}

	public void setProjectReleases2(List<ProjectRelease> projectReleases2) {
		this.projectReleases2 = projectReleases2;
	}

	

}