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
	private Timestamp createdDatetime;

	private int isactive;

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	private String name;

	//bi-directional many-to-one association to ApplicationIssuePriority
	@OneToMany(mappedBy="applicationSeverity", fetch=FetchType.EAGER)
	private List<ApplicationIssuePriority> applicationIssuePriorities;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember applicationTeamMember1;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember applicationTeamMember2;

	public ApplicationSeverity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	
	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
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

	public List<ApplicationIssuePriority> getApplicationIssuePriorities() {
		return this.applicationIssuePriorities;
	}

	public void setApplicationIssuePriorities(List<ApplicationIssuePriority> applicationIssuePriorities) {
		this.applicationIssuePriorities = applicationIssuePriorities;
	}

	public ApplicationIssuePriority addApplicationIssuePriority(ApplicationIssuePriority applicationIssuePriority) {
		getApplicationIssuePriorities().add(applicationIssuePriority);
		applicationIssuePriority.setApplicationSeverity(this);

		return applicationIssuePriority;
	}

	public ApplicationIssuePriority removeApplicationIssuePriority(ApplicationIssuePriority applicationIssuePriority) {
		getApplicationIssuePriorities().remove(applicationIssuePriority);
		applicationIssuePriority.setApplicationSeverity(null);

		return applicationIssuePriority;
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