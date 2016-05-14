package com.yash.yits.form;

import java.sql.Timestamp;
import java.util.Date;

public class ApplicationIssueTypeForm {

	private int id;

	private Date createdDateTime;

	private int isActive;

	private Date lastModifiedDateTime;

	private String type;

	private ApplicationForm application;

	private ApplicationTeamMemberForm lastModifiedBy;

	private ApplicationTeamMemberForm createdBy;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ApplicationForm getApplication() {
		return application;
	}

	public void setApplication(ApplicationForm application) {
		this.application = application;
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
