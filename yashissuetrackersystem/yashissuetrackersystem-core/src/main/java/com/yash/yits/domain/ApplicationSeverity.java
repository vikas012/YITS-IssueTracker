package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the application_severity database table.
 * 
 */
@Entity
@Table(name="application_severity")
@NamedQuery(name="ApplicationSeverity.findAll", query="SELECT a FROM ApplicationSeverity a")
public class ApplicationSeverity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDateTime;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDateTime;

	private String name;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@OneToMany(mappedBy="applicationSeverity", fetch=FetchType.EAGER)
	private List<ApplicationIssuePriority> applicationIssuePriorities;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember lastModifiedBy;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember createdBy;

	public ApplicationSeverity() {
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

	public List<ApplicationIssuePriority> getApplicationIssuePriorities() {
		return applicationIssuePriorities;
	}

	public void setApplicationIssuePriorities(List<ApplicationIssuePriority> applicationIssuePriorities) {
		this.applicationIssuePriorities = applicationIssuePriorities;
	}

	public ApplicationTeamMember getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(ApplicationTeamMember lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ApplicationTeamMember getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMember createdBy) {
		this.createdBy = createdBy;
	}

}