package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the conversation database table.
 * 
 */
@Entity
@NamedQuery(name="Conversation.findAll", query="SELECT c FROM Conversation c")
public class Conversation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDateTime;

	private int isActive;

	private String message;

	//bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="conversation", fetch=FetchType.EAGER)
	private List<Attachment> attachments;

	//bi-directional many-to-one association to Issue
	@ManyToOne
	private Issue issue;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember createdBy;

	public Conversation() {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public ApplicationTeamMember getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMember createdBy) {
		this.createdBy = createdBy;
	}

}