package com.yash.yits.form;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Conversation;
import com.yash.yits.domain.Issue;

public class AttachmentForm {

	private int id;

	private Timestamp createdDateTime;

	private byte[] file;

	private int isActive;

	private String label;

	private Timestamp lastModifiedDateTime;

	private String name;

	private ConversationForm conversation;

	private IssueForm issue;

	private ApplicationTeamMemberForm lastModifiedBy;

	private ApplicationTeamMemberForm createdBy;

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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public ConversationForm getConversation() {
		return conversation;
	}

	public void setConversation(ConversationForm conversation) {
		this.conversation = conversation;
	}

	public IssueForm getIssue() {
		return issue;
	}

	public void setIssue(IssueForm issue) {
		this.issue = issue;
	}

	public ApplicationTeamMemberForm getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(ApplicationTeamMemberForm lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ApplicationTeamMemberForm getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMemberForm createdBy) {
		this.createdBy = createdBy;
	}
	
}
