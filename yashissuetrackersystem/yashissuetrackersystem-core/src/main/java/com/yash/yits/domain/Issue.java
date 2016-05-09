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

	@Column(name="AFFECTED_USER")
	private int affectedUser;

	@Column(name="AFFECTED_VERSION")
	private String affectedVersion;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSE_DATE")
	private Date closeDate;

	private String component;

	@Column(name="CREATED_DATE_TIME")
	private Timestamp createdDateTime;


	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DATE")
	private Date dueDate;

	private int isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	@Column(name="ORIGINAL_ESTIMATE")
	private int originalEstimate;

	@Column(name="REMAINING_ESTIMATE")
	private int remainingEstimate;

	private String description;
	private String summary;

	//bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="issue", fetch=FetchType.EAGER)
	private List<Attachment> attachments;

	//bi-directional many-to-one association to Conversation
	@OneToMany(mappedBy="issue", fetch=FetchType.EAGER)
	private List<Conversation> conversations;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="OWNER")
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember2;

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
	private ApplicationTeamMember applicationTeamMember3;

	//bi-directional many-to-one association to IssueActivityLog
	@OneToMany(mappedBy="issue", fetch=FetchType.EAGER)
	private List<IssueActivityLog> issueActivityLogs;

	public Issue() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAffectedUser() {
		return this.affectedUser;
	}

	public void setAffectedUser(int affectedUser) {
		this.affectedUser = affectedUser;
	}

	public String getAffectedVersion() {
		return this.affectedVersion;
	}

	public void setAffectedVersion(String affectedVersion) {
		this.affectedVersion = affectedVersion;
	}

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Timestamp getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getSummary() {
		return this.summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public int getOriginalEstimate() {
		return this.originalEstimate;
	}

	public void setOriginalEstimate(int originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public int getRemainingEstimate() {
		return this.remainingEstimate;
	}

	public void setRemainingEstimate(int remainingEstimate) {
		this.remainingEstimate = remainingEstimate;
	}


	public List<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Attachment addAttachment(Attachment attachment) {
		getAttachments().add(attachment);
		attachment.setIssue(this);

		return attachment;
	}

	public Attachment removeAttachment(Attachment attachment) {
		getAttachments().remove(attachment);
		attachment.setIssue(null);

		return attachment;
	}

	public List<Conversation> getConversations() {
		return this.conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public Conversation addConversation(Conversation conversation) {
		getConversations().add(conversation);
		conversation.setIssue(this);

		return conversation;
	}

	public Conversation removeConversation(Conversation conversation) {
		getConversations().remove(conversation);
		conversation.setIssue(null);

		return conversation;
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

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ApplicationIssueType getApplicationIssueType() {
		return this.applicationIssueType;
	}

	public void setApplicationIssueType(ApplicationIssueType applicationIssueType) {
		this.applicationIssueType = applicationIssueType;
	}

	public ApplicationIssuePriority getApplicationIssuePriority() {
		return this.applicationIssuePriority;
	}

	public void setApplicationIssuePriority(ApplicationIssuePriority applicationIssuePriority) {
		this.applicationIssuePriority = applicationIssuePriority;
	}

	public ApplicationEnvironment getApplicationEnvironment() {
		return this.applicationEnvironment;
	}

	public void setApplicationEnvironment(ApplicationEnvironment applicationEnvironment) {
		this.applicationEnvironment = applicationEnvironment;
	}

	public ApplicationRelease getApplicationRelease() {
		return this.applicationRelease;
	}

	public void setApplicationRelease(ApplicationRelease applicationRelease) {
		this.applicationRelease = applicationRelease;
	}

	public ProjectRelease getProjectRelease() {
		return this.projectRelease;
	}

	public void setProjectRelease(ProjectRelease projectRelease) {
		this.projectRelease = projectRelease;
	}

	public ApplicationIssueStatus getApplicationIssueStatus() {
		return this.applicationIssueStatus;
	}

	public void setApplicationIssueStatus(ApplicationIssueStatus applicationIssueStatus) {
		this.applicationIssueStatus = applicationIssueStatus;
	}

	public ApplicationTeamMember getApplicationTeamMember3() {
		return this.applicationTeamMember3;
	}

	public void setApplicationTeamMember3(ApplicationTeamMember applicationTeamMember3) {
		this.applicationTeamMember3 = applicationTeamMember3;
	}

	public List<IssueActivityLog> getIssueActivityLogs() {
		return this.issueActivityLogs;
	}

	public void setIssueActivityLogs(List<IssueActivityLog> issueActivityLogs) {
		this.issueActivityLogs = issueActivityLogs;
	}

	public IssueActivityLog addIssueActivityLog(IssueActivityLog issueActivityLog) {
		getIssueActivityLogs().add(issueActivityLog);
		issueActivityLog.setIssue(this);

		return issueActivityLog;
	}

	public IssueActivityLog removeIssueActivityLog(IssueActivityLog issueActivityLog) {
		getIssueActivityLogs().remove(issueActivityLog);
		issueActivityLog.setIssue(null);

		return issueActivityLog;
	}

}