package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the issue_activity_log database table.
 * 
 */
@Entity
@Table(name="issue_activity_log")
@NamedQuery(name="IssueActivityLog.findAll", query="SELECT i FROM IssueActivityLog i")
public class IssueActivityLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String action;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDatetime;

	private Object isactive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

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

	public IssueActivityLog() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Object getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Object isactive) {
		this.isactive = isactive;
	}

	public Timestamp getLastModifiedDatetime() {
		return this.lastModifiedDatetime;
	}

	public void setLastModifiedDatetime(Timestamp lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
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