package com.yash.yits.form;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationEnvironment;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.ApplicationIssueStatus;
import com.yash.yits.domain.ApplicationIssueType;
import com.yash.yits.domain.ApplicationProjectStatus;
import com.yash.yits.domain.ApplicationRelease;
import com.yash.yits.domain.ApplicationSeverity;
import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Attachment;
import com.yash.yits.domain.Conversation;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.IssueActivityLog;
import com.yash.yits.domain.Member;
import com.yash.yits.domain.Project;
import com.yash.yits.domain.ProjectRelease;

public class ApplicationTeamMemberForm {

	private int id;

	private Timestamp createdDateTime;

	private int isActive;

	private Timestamp lastModifiedDateTime;

	private MemberForm member;

	private ApplicationForm application;

	private ApplicationTeamMemberForm applicationTeamMember1;

	private ApplicationTeamMemberForm applicationTeamMember2;

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

	public MemberForm getMember() {
		return member;
	}

	public void setMember(MemberForm member) {
		this.member = member;
	}

	public ApplicationForm getApplication() {
		return application;
	}

	public void setApplication(ApplicationForm application) {
		this.application = application;
	}

	public ApplicationTeamMemberForm getApplicationTeamMember1() {
		return applicationTeamMember1;
	}

	public void setApplicationTeamMember1(ApplicationTeamMemberForm applicationTeamMember1) {
		this.applicationTeamMember1 = applicationTeamMember1;
	}

	public ApplicationTeamMemberForm getApplicationTeamMember2() {
		return applicationTeamMember2;
	}

	public void setApplicationTeamMember2(ApplicationTeamMemberForm applicationTeamMember2) {
		this.applicationTeamMember2 = applicationTeamMember2;
	}

}
