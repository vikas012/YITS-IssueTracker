package com.yash.yits.form;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationProjectStatus;
import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.ProjectRelease;

public class ProjectForm{

	private int id;

	private Timestamp createdDateTime;

	private Date endDate;

	private int isActive;

	private Timestamp lastModifiedDateTime;

	private String name;

	private Date startDate;

	private ApplicationForm application;

	private ApplicationTeamMemberForm applicationOwner;

	private ApplicationProjectStatusForm applicationProjectStatus;

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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public ApplicationForm getApplication() {
		return application;
	}

	public void setApplication(ApplicationForm application) {
		this.application = application;
	}

	public ApplicationTeamMemberForm getApplicationOwner() {
		return applicationOwner;
	}

	public void setApplicationOwner(ApplicationTeamMemberForm applicationOwner) {
		this.applicationOwner = applicationOwner;
	}

	public ApplicationProjectStatusForm getApplicationProjectStatus() {
		return applicationProjectStatus;
	}

	public void setApplicationProjectStatus(ApplicationProjectStatusForm applicationProjectStatus) {
		this.applicationProjectStatus = applicationProjectStatus;
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

	@Override
	public String toString() {
		return "ProjectForm [id=" + id + ", name=" + name + "]";
	}
	
	

}