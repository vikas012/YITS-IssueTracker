package com.yash.yits.domain;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;
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
	@GeneratedValue
	@Column(name="ISSUEDETAIL_ID")
	private int issueDetailId;

	@Column(name="ISSUE_AFFECTEDVERSION")
	private String issueAffectedVersion;

	@Column(name="ISSUE_COMPONENT")
	private String issueComponent;

	@Column(name="ISSUE_DESCRIPTION")
	private String issueDescription;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_CREATIONDATE")
	private Date issueCreationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DUEDATE")
	private Date issueDueDate;

	@Column(name="ISSUE_ENVIRONMENT")
	private String issueEnvironment;

	@Column(name="ISSUE_ORIGINALESTIMATE")
	private int issueOriginalEstimate;

	@Column(name="ISSUE_REMAININGESTIMATE")
	private int issueRemainingEstimate;

	@Column(name="ISSUE_SUMMARY")
	private String issueSummary;

	//bi-directional many-to-one association to IssueAssignedStatus
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ISSUEASSIGNED_STATUS")
	/*@Column(name="ISSUEASSIGNED_STATUS")*/
	private IssueAssignedStatus issueAssignedStatus;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ISSUE_ASSIGNEEID")
	private User user;

	//bi-directional many-to-one association to Issuetype
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ISSUE_TYPE_ID")
	/*@Column(name="ISSUE_TYPE_ID")*/
	private IssueType issueType;

	//bi-directional many-to-one association to Project
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ISSUE_PROJECT_ID")
	/*@Column(name="ISSUE_PROJECT_ID")*/
	private Project project;

	//bi-directional many-to-one association to Issuepriority
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ISSUE_PRIORITY_ID")
	/*@Column(name="ISSUE_PRIORITY_ID")*/
	private IssuePriority issuePriority;

	//bi-directional many-to-one association to Issuestatus
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ISSUE_STATUS_ID")
	/*@Column(name="ISSUE_STATUS_ID")*/
	private IssueStatus issueStatus;

	//bi-directional many-to-many association to Attachment
	@ManyToMany(mappedBy="issues")
	private List<Attachment> attachments;

	//bi-directional many-to-one association to Issuefeed
	@OneToMany(mappedBy="issue")
	private List<IssueFeed> issueFeeds;

	public Issue() {
		
		this.issueAssignedStatus = new IssueAssignedStatus();
		this.user = new User();
		this.issueType = new IssueType();
		this.project = new Project();
		this.issuePriority = new IssuePriority();
		this.issueStatus = new IssueStatus();
	}

	public int getIssueDetailId() {
		return this.issueDetailId;
	}

	public void setIssueDetailId(int issueDetailId) {
		this.issueDetailId = issueDetailId;
	}

	public String getIssueAffectedVersion() {
		return this.issueAffectedVersion;
	}

	public void setIssueAffectedVersion(String issueAffectedVersion) {
		this.issueAffectedVersion = issueAffectedVersion;
	}

	public String getIssueComponent() {
		return this.issueComponent;
	}

	public void setIssueComponent(String issueComponent) {
		this.issueComponent = issueComponent;
	}

	public String getIssueDescription() {
		return this.issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
	
	public Date getIssueCreationDate() {
		return this.issueCreationDate;
	}

	public void setIssueCreationDate(Date issueCreationDate) {
		this.issueCreationDate = issueCreationDate;
	}

	public Date getIssueDueDate() {
		return this.issueDueDate;
	}

	public void setIssueDueDate(Date issueDueDate) {
		this.issueDueDate = issueDueDate;
	}

	public String getIssueEnvironment() {
		return this.issueEnvironment;
	}

	public void setIssueEnvironment(String issueEnvironment) {
		this.issueEnvironment = issueEnvironment;
	}

	public int getIssueOriginalEstimate() {
		return this.issueOriginalEstimate;
	}

	public void setIssueOriginalEstimate(int issueOriginalEstimate) {
		this.issueOriginalEstimate = issueOriginalEstimate;
	}

	public int getIssueRemainingEstimate() {
		return this.issueRemainingEstimate;
	}

	public void setIssueRemainingEstimate(int issueRemainingEstimate) {
		this.issueRemainingEstimate = issueRemainingEstimate;
	}

	public String getIssueSummary() {
		return this.issueSummary;
	}

	public void setIssueSummary(String issueSummary) {
		this.issueSummary = issueSummary;
	}

	public IssueAssignedStatus getIssueAssignedStatus() {
		return this.issueAssignedStatus;
	}

	public void setIssueAssignedStatus(IssueAssignedStatus issueAssignedStatus) {
		this.issueAssignedStatus = issueAssignedStatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IssueType getIssueType() {
		return this.issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public IssuePriority getIssuePriority() {
		return this.issuePriority;
	}

	public void setIssuePriority(IssuePriority issuePriority) {
		this.issuePriority = issuePriority;
	}

	public IssueStatus getIssueStatus() {
		return this.issueStatus;
	}

	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}

	public List<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<IssueFeed> getIssueFeeds() {
		return this.issueFeeds;
	}

	public void setIssueFeeds(List<IssueFeed> issueFeeds) {
		this.issueFeeds = issueFeeds;
	}

	public IssueFeed addIssueFeed(IssueFeed issueFeed) {
		getIssueFeeds().add(issueFeed);
		issueFeed.setIssue(this);

		return issueFeed;
	}

	public IssueFeed removeIssueFeed(IssueFeed issueFeed) {
		getIssueFeeds().remove(issueFeed);
		issueFeed.setIssue(null);

		return issueFeed;
	}

}