package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the attachment database table.
 * 
 */
@Entity
@NamedQuery(name="Attachment.findAll", query="SELECT a FROM Attachment a")
public class Attachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="ATTACHMENT_DATE")
	private Date attachmentDate;

	@Column(name="ATTACHMENT_FILE")
	private Object attachmentFile;

	@Column(name="ATTACHMENT_ID")
	private int attachmentId;

	@Column(name="ATTACHMENT_LABEL")
	private String attachmentLabel;

	@Column(name="ATTACHMENT_NAME")
	private String attachmentName;

	@Column(name="CREATED_DATE_TIME")
	private Timestamp createdDateTime;

	private Object file;

	private Object isactive;

	@Column(name="`LABEL`")
	private String label;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	private String name;

	//bi-directional many-to-one association to Conversation
	@ManyToOne
	private Conversation conversation;

	//bi-directional many-to-one association to Issue
	@ManyToOne
	private Issue issue;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember applicationTeamMember2;

	public Attachment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAttachmentDate() {
		return this.attachmentDate;
	}

	public void setAttachmentDate(Date attachmentDate) {
		this.attachmentDate = attachmentDate;
	}

	public Object getAttachmentFile() {
		return this.attachmentFile;
	}

	public void setAttachmentFile(Object attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public int getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentLabel() {
		return this.attachmentLabel;
	}

	public void setAttachmentLabel(String attachmentLabel) {
		this.attachmentLabel = attachmentLabel;
	}

	public String getAttachmentName() {
		return this.attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public Timestamp getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Object getFile() {
		return this.file;
	}

	public void setFile(Object file) {
		this.file = file;
	}

	public Object getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Object isactive) {
		this.isactive = isactive;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public Conversation getConversation() {
		return this.conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
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

}