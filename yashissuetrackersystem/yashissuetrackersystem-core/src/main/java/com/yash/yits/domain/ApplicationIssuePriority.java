package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the application_issue_priority database table.
 * 
 */
@Entity
@Table(name="application_issue_priority")
@NamedQuery(name="ApplicationIssuePriority.findAll", query="SELECT a FROM ApplicationIssuePriority a")
public class ApplicationIssuePriority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="CREATED_DATETIME")
	private Timestamp createdDateTime;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDateTime;

	private String type;

	//bi-directional many-to-one association to Application
	@ManyToOne
	private Application application;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="LAST_MODIFIED_BY")
	private ApplicationTeamMember lastModifiedBy;

	//bi-directional many-to-one association to ApplicationTeamMember
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private ApplicationTeamMember createdBy;

	//bi-directional many-to-one association to ApplicationSeverity
	@ManyToOne
	@JoinColumn(name="SEVERITY_ID")
	private ApplicationSeverity applicationSeverity;

	//bi-directional many-to-one association to Issue
	@OneToMany(mappedBy="applicationIssuePriority", fetch=FetchType.EAGER)
	private List<Issue> issues;

	public ApplicationIssuePriority() {
	}

	
}