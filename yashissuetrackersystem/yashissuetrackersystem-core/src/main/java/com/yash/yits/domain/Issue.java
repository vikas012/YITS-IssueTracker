package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the issue database table.
 * 
 */
@Entity
@NamedQuery(name="Issue.findAll", query="SELECT i FROM Issue i")
public class Issue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="AFFECTED_VERSION")
	private String affectedVersion;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSE_DATE")
	private Date closeDate;

	private String component;

	@Column(name="CREATED_DATETIME")
	private Date createdDateTime;

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	private String description;

	@Column(name="DUE_DATE")
	private Date dueDate;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Date lastModifiedDateTime;

	@Column(name="ORIGINAL_ESTIMATE")
	private String originalEstimate;

	@Column(name="REMAINING_ESTIMATE")
	private String remainingEstimate;
	
	@Column(name="TASK_PROGRESS_UPDATE")
	private String taskProgressUpdate;

	private String summary;

	/*//bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="issue", fetch=FetchType.EAGER)
	private List<Attachment> attachments;

	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="issue", fetch=FetchType.EAGER)
	private List<Conversation> conversations;*/

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="OWNER")
	private ApplicationTeamMember issueOwner;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember lastModifiedBy;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional many-to-one association to ApplicationIssueType
	@ManyToOne
	@JoinColumn(name="APPLICATION_ISSUE_TYPE_ID")
	private ApplicationIssueType applicationIssueType;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@ManyToOne
	@JoinColumn(name="APPLICATION_ISSUE_PRIORITY_ID")
	private ApplicationIssuePriority applicationIssuePriority;

	//bi-directional many-to-one association to ApplicationEnvironment
	@ManyToOne
	@JoinColumn(name="APPLICATION_ENVIRONMENT_ID")
	private ApplicationEnvironment applicationEnvironment;

	//bi-directional many-to-one association to ApplicationRelease
	@ManyToOne
	@JoinColumn(name="APPLICATION_RELEASE_ID")
	private ApplicationRelease applicationRelease;

	//bi-directional many-to-one association to ProjectRelease
	@ManyToOne
	@JoinColumn(name="PROJECT_RELEASE_ID")
	private ProjectRelease projectRelease;

	//bi-directional many-to-one association to ApplicationIssueStatus
	@ManyToOne
	@JoinColumn(name="APPLICATION_ISSUE_STATUS_ID")
	private ApplicationIssueStatus applicationIssueStatus;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember createdBy;

/*	//bi-directional many-to-one association to IssueActivityLog
	@OneToMany(mappedBy="issue", fetch=FetchType.EAGER)
	private List<IssueActivityLog> issueActivityLogs;*/
	
	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="ASSIGNED_USER")
	private ApplicationTeamMember assignedUser;
	
	@OneToMany(mappedBy="issue")
	private List<Attachment> attachments;
	
/*	//bi-directional many-to-one association to IssuePauseReason
	@OneToMany(mappedBy="issue", fetch=FetchType.EAGER)
	private List<IssuePauseReason> issuePauseReasons;*/
	
	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Issue() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getAffectedVersion() {
		return affectedVersion;
	}

	public void setAffectedVersion(String affectedVersion) {
		this.affectedVersion = affectedVersion;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public String getRemainingEstimate() {
		return remainingEstimate;
	}

	public void setRemainingEstimate(String remainingEstimate) {
		this.remainingEstimate = remainingEstimate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	/*public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}*/

	public ApplicationTeamMember getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(ApplicationTeamMember assignedUser) {
		this.assignedUser = assignedUser;
	}

	/*public List<IssuePauseReason> getIssuePauseReasons() {
		return issuePauseReasons;
	}

	public void setIssuePauseReasons(List<IssuePauseReason> issuePauseReasons) {
		this.issuePauseReasons = issuePauseReasons;
	}*/

	public String getTaskProgressUpdate() {
		return taskProgressUpdate;
	}

	public void setTaskProgressUpdate(String taskProgressUpdate) {
		this.taskProgressUpdate = taskProgressUpdate;
	}

	public ApplicationTeamMember getIssueOwner() {
		return issueOwner;
	}

	public void setIssueOwner(ApplicationTeamMember issueOwner) {
		this.issueOwner = issueOwner;
	}

	public ApplicationTeamMember getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(ApplicationTeamMember lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ApplicationIssueType getApplicationIssueType() {
		return applicationIssueType;
	}

	public void setApplicationIssueType(ApplicationIssueType applicationIssueType) {
		this.applicationIssueType = applicationIssueType;
	}

	public ApplicationIssuePriority getApplicationIssuePriority() {
		return applicationIssuePriority;
	}

	public void setApplicationIssuePriority(ApplicationIssuePriority applicationIssuePriority) {
		this.applicationIssuePriority = applicationIssuePriority;
	}

	public ApplicationEnvironment getApplicationEnvironment() {
		return applicationEnvironment;
	}

	public void setApplicationEnvironment(ApplicationEnvironment applicationEnvironment) {
		this.applicationEnvironment = applicationEnvironment;
	}

	public ApplicationRelease getApplicationRelease() {
		return applicationRelease;
	}

	public void setApplicationRelease(ApplicationRelease applicationRelease) {
		this.applicationRelease = applicationRelease;
	}

	public ProjectRelease getProjectRelease() {
		return projectRelease;
	}

	public void setProjectRelease(ProjectRelease projectRelease) {
		this.projectRelease = projectRelease;
	}

	public ApplicationIssueStatus getApplicationIssueStatus() {
		return applicationIssueStatus;
	}

	public void setApplicationIssueStatus(ApplicationIssueStatus applicationIssueStatus) {
		this.applicationIssueStatus = applicationIssueStatus;
	}

	public ApplicationTeamMember getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMember createdBy) {
		this.createdBy = createdBy;
	}


	@Override
	public String toString() {
		return "Issue [dueDate=" + dueDate + ", summary=" + summary + ", applicationIssueType=" + applicationIssueType
				+ "]";
	}

/*	public List<IssueActivityLog> getIssueActivityLogs() {
		return issueActivityLogs;
	}

	public void setIssueActivityLogs(List<IssueActivityLog> issueActivityLogs) {
		this.issueActivityLogs = issueActivityLogs;
	}*/

}