package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
	@Column(name="ATTACHMENT_ID")
	private int attachmentId;
	
	@Column(name="ATTACHMENT_NAME")
	private String attachmentName;

	@Temporal(TemporalType.DATE)
	@Column(name="ATTACHMENT_DATE")
	private Date attachmentDate;

	@Lob
	@Column(name="ATTACHMENT_FILE")
	private byte[] attachmentFile;

	@Column(name="ATTACHMENT_LABEL")
	private String attachmentLabel;

	//bi-directional many-to-many association to Issue
	@ManyToMany
	@JoinTable(
		name="issueattachement"
		, joinColumns={
			@JoinColumn(name="ATTACHMENT_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ISSUEDETAIL_ID")
			}
		)
	private List<Issue> issues;

	public Attachment() {
	}

	public int getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public Date getAttachmentDate() {
		return this.attachmentDate;
	}

	public void setAttachmentDate(Date attachmentDate) {
		this.attachmentDate = attachmentDate;
	}

	public byte[] getAttachmentFile() {
		return this.attachmentFile;
	}

	public void setAttachmentFile(byte[] attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public String getAttachmentLabel() {
		return this.attachmentLabel;
	}

	public void setAttachmentLabel(String attachmentLabel) {
		this.attachmentLabel = attachmentLabel;
	}

	public List<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

}