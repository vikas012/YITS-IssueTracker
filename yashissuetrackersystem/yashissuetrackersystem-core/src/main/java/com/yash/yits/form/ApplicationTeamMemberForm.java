package com.yash.yits.form;

import java.sql.Timestamp;
import java.util.Date;

public class ApplicationTeamMemberForm {

	private int id;

	private Date createdDateTime;

	private int isActive;

	private Date lastModifiedDateTime;

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

	

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
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
