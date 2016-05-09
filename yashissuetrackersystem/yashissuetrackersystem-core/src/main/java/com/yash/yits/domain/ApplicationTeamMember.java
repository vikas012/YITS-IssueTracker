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

	private int isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	//bi-directional many-to-one association to Application
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<Application> applications1;

	//bi-directional many-to-one association to Application
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<Application> applications2;

	//bi-directional many-to-one association to ApplicationEnvironment
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationEnvironment> applicationEnvironments1;

	//bi-directional many-to-one association to ApplicationEnvironment
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ApplicationEnvironment> applicationEnvironments2;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationIssuePriority> applicationIssuePriorities1;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ApplicationIssuePriority> applicationIssuePriorities2;

	//bi-directional many-to-one association to ApplicationIssueStatus
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationIssueStatus> applicationIssueStatuses1;

	//bi-directional many-to-one association to ApplicationIssueStatus
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ApplicationIssueStatus> applicationIssueStatuses2;

	//bi-directional many-to-one association to ApplicationIssueType
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationIssueType> applicationIssueTypes1;

	//bi-directional many-to-one association to ApplicationIssueType
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ApplicationIssueType> applicationIssueTypes2;

	//bi-directional many-to-one association to ApplicationProjectStatus
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationProjectStatus> applicationProjectStatuses1;

	//bi-directional many-to-one association to ApplicationProjectStatus
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ApplicationProjectStatus> applicationProjectStatuses2;

	//bi-directional many-to-one association to ApplicationRelease
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationRelease> applicationReleases1;

	//bi-directional many-to-one association to ApplicationRelease
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ApplicationRelease> applicationReleases2;

	//bi-directional many-to-one association to ApplicationSeverity
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ApplicationSeverity> applicationSeverities1;

	//bi-directional many-to-one association to ApplicationSeverity
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
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
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<Attachment> attachments1;

	//bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<Attachment> attachments2;

	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="applicationTeamMember", fetch=FetchType.EAGER)
	private List<Conversation> conversations;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<Issue> issues1;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<Issue> issues2;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="applicationTeamMember3", fetch=FetchType.EAGER)
	private List<Issue> issues3;

	//bi-directional many-to-one association to IssueActivityLog
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<IssueActivityLog> issueActivityLogs1;

	//bi-directional many-to-one association to IssueActivityLog
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<IssueActivityLog> issueActivityLogs2;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<Project> projects1;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<Project> projects2;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="applicationTeamMember3", fetch=FetchType.EAGER)
	private List<Project> projects3;

	//bi-directional many-to-many association to Project
	@ManyToMany(mappedBy="applicationTeamMembers", fetch=FetchType.EAGER)
	private List<Project> projects4;

	//bi-directional many-to-one association to ProjectRelease
	@OneToMany(mappedBy="applicationTeamMember1", fetch=FetchType.EAGER)
	private List<ProjectRelease> projectReleases1;

	//bi-directional many-to-one association to ProjectRelease
	@OneToMany(mappedBy="applicationTeamMember2", fetch=FetchType.EAGER)
	private List<ProjectRelease> projectReleases2;

	public ApplicationTeamMember() {
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

	public List<Application> getApplications1() {
		return this.applications1;
	}

	public void setApplications1(List<Application> applications1) {
		this.applications1 = applications1;
	}

	public Application addApplications1(Application applications1) {
		getApplications1().add(applications1);
		applications1.setApplicationTeamMember1(this);

		return applications1;
	}

	public Application removeApplications1(Application applications1) {
		getApplications1().remove(applications1);
		applications1.setApplicationTeamMember1(null);

		return applications1;
	}

	public List<Application> getApplications2() {
		return this.applications2;
	}

	public void setApplications2(List<Application> applications2) {
		this.applications2 = applications2;
	}

	public Application addApplications2(Application applications2) {
		getApplications2().add(applications2);
		applications2.setApplicationTeamMember2(this);

		return applications2;
	}

	public Application removeApplications2(Application applications2) {
		getApplications2().remove(applications2);
		applications2.setApplicationTeamMember2(null);

		return applications2;
	}

	public List<ApplicationEnvironment> getApplicationEnvironments1() {
		return this.applicationEnvironments1;
	}

	public void setApplicationEnvironments1(List<ApplicationEnvironment> applicationEnvironments1) {
		this.applicationEnvironments1 = applicationEnvironments1;
	}

	public ApplicationEnvironment addApplicationEnvironments1(ApplicationEnvironment applicationEnvironments1) {
		getApplicationEnvironments1().add(applicationEnvironments1);
		applicationEnvironments1.setApplicationTeamMember1(this);

		return applicationEnvironments1;
	}

	public ApplicationEnvironment removeApplicationEnvironments1(ApplicationEnvironment applicationEnvironments1) {
		getApplicationEnvironments1().remove(applicationEnvironments1);
		applicationEnvironments1.setApplicationTeamMember1(null);

		return applicationEnvironments1;
	}

	public List<ApplicationEnvironment> getApplicationEnvironments2() {
		return this.applicationEnvironments2;
	}

	public void setApplicationEnvironments2(List<ApplicationEnvironment> applicationEnvironments2) {
		this.applicationEnvironments2 = applicationEnvironments2;
	}

	public ApplicationEnvironment addApplicationEnvironments2(ApplicationEnvironment applicationEnvironments2) {
		getApplicationEnvironments2().add(applicationEnvironments2);
		applicationEnvironments2.setApplicationTeamMember2(this);

		return applicationEnvironments2;
	}

	public ApplicationEnvironment removeApplicationEnvironments2(ApplicationEnvironment applicationEnvironments2) {
		getApplicationEnvironments2().remove(applicationEnvironments2);
		applicationEnvironments2.setApplicationTeamMember2(null);

		return applicationEnvironments2;
	}

	public List<ApplicationIssuePriority> getApplicationIssuePriorities1() {
		return this.applicationIssuePriorities1;
	}

	public void setApplicationIssuePriorities1(List<ApplicationIssuePriority> applicationIssuePriorities1) {
		this.applicationIssuePriorities1 = applicationIssuePriorities1;
	}

	public ApplicationIssuePriority addApplicationIssuePriorities1(ApplicationIssuePriority applicationIssuePriorities1) {
		getApplicationIssuePriorities1().add(applicationIssuePriorities1);
		applicationIssuePriorities1.setApplicationTeamMember1(this);

		return applicationIssuePriorities1;
	}

	public ApplicationIssuePriority removeApplicationIssuePriorities1(ApplicationIssuePriority applicationIssuePriorities1) {
		getApplicationIssuePriorities1().remove(applicationIssuePriorities1);
		applicationIssuePriorities1.setApplicationTeamMember1(null);

		return applicationIssuePriorities1;
	}

	public List<ApplicationIssuePriority> getApplicationIssuePriorities2() {
		return this.applicationIssuePriorities2;
	}

	public void setApplicationIssuePriorities2(List<ApplicationIssuePriority> applicationIssuePriorities2) {
		this.applicationIssuePriorities2 = applicationIssuePriorities2;
	}

	public ApplicationIssuePriority addApplicationIssuePriorities2(ApplicationIssuePriority applicationIssuePriorities2) {
		getApplicationIssuePriorities2().add(applicationIssuePriorities2);
		applicationIssuePriorities2.setApplicationTeamMember2(this);

		return applicationIssuePriorities2;
	}

	public ApplicationIssuePriority removeApplicationIssuePriorities2(ApplicationIssuePriority applicationIssuePriorities2) {
		getApplicationIssuePriorities2().remove(applicationIssuePriorities2);
		applicationIssuePriorities2.setApplicationTeamMember2(null);

		return applicationIssuePriorities2;
	}

	public List<ApplicationIssueStatus> getApplicationIssueStatuses1() {
		return this.applicationIssueStatuses1;
	}

	public void setApplicationIssueStatuses1(List<ApplicationIssueStatus> applicationIssueStatuses1) {
		this.applicationIssueStatuses1 = applicationIssueStatuses1;
	}

	public ApplicationIssueStatus addApplicationIssueStatuses1(ApplicationIssueStatus applicationIssueStatuses1) {
		getApplicationIssueStatuses1().add(applicationIssueStatuses1);
		applicationIssueStatuses1.setApplicationTeamMember1(this);

		return applicationIssueStatuses1;
	}

	public ApplicationIssueStatus removeApplicationIssueStatuses1(ApplicationIssueStatus applicationIssueStatuses1) {
		getApplicationIssueStatuses1().remove(applicationIssueStatuses1);
		applicationIssueStatuses1.setApplicationTeamMember1(null);

		return applicationIssueStatuses1;
	}

	public List<ApplicationIssueStatus> getApplicationIssueStatuses2() {
		return this.applicationIssueStatuses2;
	}

	public void setApplicationIssueStatuses2(List<ApplicationIssueStatus> applicationIssueStatuses2) {
		this.applicationIssueStatuses2 = applicationIssueStatuses2;
	}

	public ApplicationIssueStatus addApplicationIssueStatuses2(ApplicationIssueStatus applicationIssueStatuses2) {
		getApplicationIssueStatuses2().add(applicationIssueStatuses2);
		applicationIssueStatuses2.setApplicationTeamMember2(this);

		return applicationIssueStatuses2;
	}

	public ApplicationIssueStatus removeApplicationIssueStatuses2(ApplicationIssueStatus applicationIssueStatuses2) {
		getApplicationIssueStatuses2().remove(applicationIssueStatuses2);
		applicationIssueStatuses2.setApplicationTeamMember2(null);

		return applicationIssueStatuses2;
	}

	public List<ApplicationIssueType> getApplicationIssueTypes1() {
		return this.applicationIssueTypes1;
	}

	public void setApplicationIssueTypes1(List<ApplicationIssueType> applicationIssueTypes1) {
		this.applicationIssueTypes1 = applicationIssueTypes1;
	}

	public ApplicationIssueType addApplicationIssueTypes1(ApplicationIssueType applicationIssueTypes1) {
		getApplicationIssueTypes1().add(applicationIssueTypes1);
		applicationIssueTypes1.setApplicationTeamMember1(this);

		return applicationIssueTypes1;
	}

	public ApplicationIssueType removeApplicationIssueTypes1(ApplicationIssueType applicationIssueTypes1) {
		getApplicationIssueTypes1().remove(applicationIssueTypes1);
		applicationIssueTypes1.setApplicationTeamMember1(null);

		return applicationIssueTypes1;
	}

	public List<ApplicationIssueType> getApplicationIssueTypes2() {
		return this.applicationIssueTypes2;
	}

	public void setApplicationIssueTypes2(List<ApplicationIssueType> applicationIssueTypes2) {
		this.applicationIssueTypes2 = applicationIssueTypes2;
	}

	public ApplicationIssueType addApplicationIssueTypes2(ApplicationIssueType applicationIssueTypes2) {
		getApplicationIssueTypes2().add(applicationIssueTypes2);
		applicationIssueTypes2.setApplicationTeamMember2(this);

		return applicationIssueTypes2;
	}

	public ApplicationIssueType removeApplicationIssueTypes2(ApplicationIssueType applicationIssueTypes2) {
		getApplicationIssueTypes2().remove(applicationIssueTypes2);
		applicationIssueTypes2.setApplicationTeamMember2(null);

		return applicationIssueTypes2;
	}

	public List<ApplicationProjectStatus> getApplicationProjectStatuses1() {
		return this.applicationProjectStatuses1;
	}

	public void setApplicationProjectStatuses1(List<ApplicationProjectStatus> applicationProjectStatuses1) {
		this.applicationProjectStatuses1 = applicationProjectStatuses1;
	}

	public ApplicationProjectStatus addApplicationProjectStatuses1(ApplicationProjectStatus applicationProjectStatuses1) {
		getApplicationProjectStatuses1().add(applicationProjectStatuses1);
		applicationProjectStatuses1.setApplicationTeamMember1(this);

		return applicationProjectStatuses1;
	}

	public ApplicationProjectStatus removeApplicationProjectStatuses1(ApplicationProjectStatus applicationProjectStatuses1) {
		getApplicationProjectStatuses1().remove(applicationProjectStatuses1);
		applicationProjectStatuses1.setApplicationTeamMember1(null);

		return applicationProjectStatuses1;
	}

	public List<ApplicationProjectStatus> getApplicationProjectStatuses2() {
		return this.applicationProjectStatuses2;
	}

	public void setApplicationProjectStatuses2(List<ApplicationProjectStatus> applicationProjectStatuses2) {
		this.applicationProjectStatuses2 = applicationProjectStatuses2;
	}

	public ApplicationProjectStatus addApplicationProjectStatuses2(ApplicationProjectStatus applicationProjectStatuses2) {
		getApplicationProjectStatuses2().add(applicationProjectStatuses2);
		applicationProjectStatuses2.setApplicationTeamMember2(this);

		return applicationProjectStatuses2;
	}

	public ApplicationProjectStatus removeApplicationProjectStatuses2(ApplicationProjectStatus applicationProjectStatuses2) {
		getApplicationProjectStatuses2().remove(applicationProjectStatuses2);
		applicationProjectStatuses2.setApplicationTeamMember2(null);

		return applicationProjectStatuses2;
	}

	public List<ApplicationRelease> getApplicationReleases1() {
		return this.applicationReleases1;
	}

	public void setApplicationReleases1(List<ApplicationRelease> applicationReleases1) {
		this.applicationReleases1 = applicationReleases1;
	}

	public ApplicationRelease addApplicationReleases1(ApplicationRelease applicationReleases1) {
		getApplicationReleases1().add(applicationReleases1);
		applicationReleases1.setApplicationTeamMember1(this);

		return applicationReleases1;
	}

	public ApplicationRelease removeApplicationReleases1(ApplicationRelease applicationReleases1) {
		getApplicationReleases1().remove(applicationReleases1);
		applicationReleases1.setApplicationTeamMember1(null);

		return applicationReleases1;
	}

	public List<ApplicationRelease> getApplicationReleases2() {
		return this.applicationReleases2;
	}

	public void setApplicationReleases2(List<ApplicationRelease> applicationReleases2) {
		this.applicationReleases2 = applicationReleases2;
	}

	public ApplicationRelease addApplicationReleases2(ApplicationRelease applicationReleases2) {
		getApplicationReleases2().add(applicationReleases2);
		applicationReleases2.setApplicationTeamMember2(this);

		return applicationReleases2;
	}

	public ApplicationRelease removeApplicationReleases2(ApplicationRelease applicationReleases2) {
		getApplicationReleases2().remove(applicationReleases2);
		applicationReleases2.setApplicationTeamMember2(null);

		return applicationReleases2;
	}

	public List<ApplicationSeverity> getApplicationSeverities1() {
		return this.applicationSeverities1;
	}

	public void setApplicationSeverities1(List<ApplicationSeverity> applicationSeverities1) {
		this.applicationSeverities1 = applicationSeverities1;
	}

	public ApplicationSeverity addApplicationSeverities1(ApplicationSeverity applicationSeverities1) {
		getApplicationSeverities1().add(applicationSeverities1);
		applicationSeverities1.setApplicationTeamMember1(this);

		return applicationSeverities1;
	}

	public ApplicationSeverity removeApplicationSeverities1(ApplicationSeverity applicationSeverities1) {
		getApplicationSeverities1().remove(applicationSeverities1);
		applicationSeverities1.setApplicationTeamMember1(null);

		return applicationSeverities1;
	}

	public List<ApplicationSeverity> getApplicationSeverities2() {
		return this.applicationSeverities2;
	}

	public void setApplicationSeverities2(List<ApplicationSeverity> applicationSeverities2) {
		this.applicationSeverities2 = applicationSeverities2;
	}

	public ApplicationSeverity addApplicationSeverities2(ApplicationSeverity applicationSeverities2) {
		getApplicationSeverities2().add(applicationSeverities2);
		applicationSeverities2.setApplicationTeamMember2(this);

		return applicationSeverities2;
	}

	public ApplicationSeverity removeApplicationSeverities2(ApplicationSeverity applicationSeverities2) {
		getApplicationSeverities2().remove(applicationSeverities2);
		applicationSeverities2.setApplicationTeamMember2(null);

		return applicationSeverities2;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
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

	public List<ApplicationTeamMember> getApplicationTeamMembers1() {
		return this.applicationTeamMembers1;
	}

	public void setApplicationTeamMembers1(List<ApplicationTeamMember> applicationTeamMembers1) {
		this.applicationTeamMembers1 = applicationTeamMembers1;
	}

	public ApplicationTeamMember addApplicationTeamMembers1(ApplicationTeamMember applicationTeamMembers1) {
		getApplicationTeamMembers1().add(applicationTeamMembers1);
		applicationTeamMembers1.setApplicationTeamMember1(this);

		return applicationTeamMembers1;
	}

	public ApplicationTeamMember removeApplicationTeamMembers1(ApplicationTeamMember applicationTeamMembers1) {
		getApplicationTeamMembers1().remove(applicationTeamMembers1);
		applicationTeamMembers1.setApplicationTeamMember1(null);

		return applicationTeamMembers1;
	}

	public ApplicationTeamMember getApplicationTeamMember2() {
		return this.applicationTeamMember2;
	}

	public void setApplicationTeamMember2(ApplicationTeamMember applicationTeamMember2) {
		this.applicationTeamMember2 = applicationTeamMember2;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers2() {
		return this.applicationTeamMembers2;
	}

	public void setApplicationTeamMembers2(List<ApplicationTeamMember> applicationTeamMembers2) {
		this.applicationTeamMembers2 = applicationTeamMembers2;
	}

	public ApplicationTeamMember addApplicationTeamMembers2(ApplicationTeamMember applicationTeamMembers2) {
		getApplicationTeamMembers2().add(applicationTeamMembers2);
		applicationTeamMembers2.setApplicationTeamMember2(this);

		return applicationTeamMembers2;
	}

	public ApplicationTeamMember removeApplicationTeamMembers2(ApplicationTeamMember applicationTeamMembers2) {
		getApplicationTeamMembers2().remove(applicationTeamMembers2);
		applicationTeamMembers2.setApplicationTeamMember2(null);

		return applicationTeamMembers2;
	}

	public List<Attachment> getAttachments1() {
		return this.attachments1;
	}

	public void setAttachments1(List<Attachment> attachments1) {
		this.attachments1 = attachments1;
	}

	public Attachment addAttachments1(Attachment attachments1) {
		getAttachments1().add(attachments1);
		attachments1.setApplicationTeamMember1(this);

		return attachments1;
	}

	public Attachment removeAttachments1(Attachment attachments1) {
		getAttachments1().remove(attachments1);
		attachments1.setApplicationTeamMember1(null);

		return attachments1;
	}

	public List<Attachment> getAttachments2() {
		return this.attachments2;
	}

	public void setAttachments2(List<Attachment> attachments2) {
		this.attachments2 = attachments2;
	}

	public Attachment addAttachments2(Attachment attachments2) {
		getAttachments2().add(attachments2);
		attachments2.setApplicationTeamMember2(this);

		return attachments2;
	}

	public Attachment removeAttachments2(Attachment attachments2) {
		getAttachments2().remove(attachments2);
		attachments2.setApplicationTeamMember2(null);

		return attachments2;
	}

	public List<Conversation> getConversations() {
		return this.conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public Conversation addConversation(Conversation conversation) {
		getConversations().add(conversation);
		conversation.setApplicationTeamMember(this);

		return conversation;
	}

	public Conversation removeConversation(Conversation conversation) {
		getConversations().remove(conversation);
		conversation.setApplicationTeamMember(null);

		return conversation;
	}

	public List<Issue> getIssues1() {
		return this.issues1;
	}

	public void setIssues1(List<Issue> issues1) {
		this.issues1 = issues1;
	}

	public Issue addIssues1(Issue issues1) {
		getIssues1().add(issues1);
		issues1.setApplicationTeamMember1(this);

		return issues1;
	}

	public Issue removeIssues1(Issue issues1) {
		getIssues1().remove(issues1);
		issues1.setApplicationTeamMember1(null);

		return issues1;
	}

	public List<Issue> getIssues2() {
		return this.issues2;
	}

	public void setIssues2(List<Issue> issues2) {
		this.issues2 = issues2;
	}

	public Issue addIssues2(Issue issues2) {
		getIssues2().add(issues2);
		issues2.setApplicationTeamMember2(this);

		return issues2;
	}

	public Issue removeIssues2(Issue issues2) {
		getIssues2().remove(issues2);
		issues2.setApplicationTeamMember2(null);

		return issues2;
	}

	public List<Issue> getIssues3() {
		return this.issues3;
	}

	public void setIssues3(List<Issue> issues3) {
		this.issues3 = issues3;
	}

	public Issue addIssues3(Issue issues3) {
		getIssues3().add(issues3);
		issues3.setApplicationTeamMember3(this);

		return issues3;
	}

	public Issue removeIssues3(Issue issues3) {
		getIssues3().remove(issues3);
		issues3.setApplicationTeamMember3(null);

		return issues3;
	}

	public List<IssueActivityLog> getIssueActivityLogs1() {
		return this.issueActivityLogs1;
	}

	public void setIssueActivityLogs1(List<IssueActivityLog> issueActivityLogs1) {
		this.issueActivityLogs1 = issueActivityLogs1;
	}

	public IssueActivityLog addIssueActivityLogs1(IssueActivityLog issueActivityLogs1) {
		getIssueActivityLogs1().add(issueActivityLogs1);
		issueActivityLogs1.setApplicationTeamMember1(this);

		return issueActivityLogs1;
	}

	public IssueActivityLog removeIssueActivityLogs1(IssueActivityLog issueActivityLogs1) {
		getIssueActivityLogs1().remove(issueActivityLogs1);
		issueActivityLogs1.setApplicationTeamMember1(null);

		return issueActivityLogs1;
	}

	public List<IssueActivityLog> getIssueActivityLogs2() {
		return this.issueActivityLogs2;
	}

	public void setIssueActivityLogs2(List<IssueActivityLog> issueActivityLogs2) {
		this.issueActivityLogs2 = issueActivityLogs2;
	}

	public IssueActivityLog addIssueActivityLogs2(IssueActivityLog issueActivityLogs2) {
		getIssueActivityLogs2().add(issueActivityLogs2);
		issueActivityLogs2.setApplicationTeamMember2(this);

		return issueActivityLogs2;
	}

	public IssueActivityLog removeIssueActivityLogs2(IssueActivityLog issueActivityLogs2) {
		getIssueActivityLogs2().remove(issueActivityLogs2);
		issueActivityLogs2.setApplicationTeamMember2(null);

		return issueActivityLogs2;
	}

	public List<Project> getProjects1() {
		return this.projects1;
	}

	public void setProjects1(List<Project> projects1) {
		this.projects1 = projects1;
	}

	public Project addProjects1(Project projects1) {
		getProjects1().add(projects1);
		projects1.setApplicationTeamMember1(this);

		return projects1;
	}

	public Project removeProjects1(Project projects1) {
		getProjects1().remove(projects1);
		projects1.setApplicationTeamMember1(null);

		return projects1;
	}

	public List<Project> getProjects2() {
		return this.projects2;
	}

	public void setProjects2(List<Project> projects2) {
		this.projects2 = projects2;
	}

	public Project addProjects2(Project projects2) {
		getProjects2().add(projects2);
		projects2.setApplicationTeamMember2(this);

		return projects2;
	}

	public Project removeProjects2(Project projects2) {
		getProjects2().remove(projects2);
		projects2.setApplicationTeamMember2(null);

		return projects2;
	}

	public List<Project> getProjects3() {
		return this.projects3;
	}

	public void setProjects3(List<Project> projects3) {
		this.projects3 = projects3;
	}

	public Project addProjects3(Project projects3) {
		getProjects3().add(projects3);
		projects3.setApplicationTeamMember3(this);

		return projects3;
	}

	public Project removeProjects3(Project projects3) {
		getProjects3().remove(projects3);
		projects3.setApplicationTeamMember3(null);

		return projects3;
	}

	public List<Project> getProjects4() {
		return this.projects4;
	}

	public void setProjects4(List<Project> projects4) {
		this.projects4 = projects4;
	}

	public List<ProjectRelease> getProjectReleases1() {
		return this.projectReleases1;
	}

	public void setProjectReleases1(List<ProjectRelease> projectReleases1) {
		this.projectReleases1 = projectReleases1;
	}

	public ProjectRelease addProjectReleases1(ProjectRelease projectReleases1) {
		getProjectReleases1().add(projectReleases1);
		projectReleases1.setApplicationTeamMember1(this);

		return projectReleases1;
	}

	public ProjectRelease removeProjectReleases1(ProjectRelease projectReleases1) {
		getProjectReleases1().remove(projectReleases1);
		projectReleases1.setApplicationTeamMember1(null);

		return projectReleases1;
	}

	public List<ProjectRelease> getProjectReleases2() {
		return this.projectReleases2;
	}

	public void setProjectReleases2(List<ProjectRelease> projectReleases2) {
		this.projectReleases2 = projectReleases2;
	}

	public ProjectRelease addProjectReleases2(ProjectRelease projectReleases2) {
		getProjectReleases2().add(projectReleases2);
		projectReleases2.setApplicationTeamMember2(this);

		return projectReleases2;
	}

	public ProjectRelease removeProjectReleases2(ProjectRelease projectReleases2) {
		getProjectReleases2().remove(projectReleases2);
		projectReleases2.setApplicationTeamMember2(null);

		return projectReleases2;
	}

}