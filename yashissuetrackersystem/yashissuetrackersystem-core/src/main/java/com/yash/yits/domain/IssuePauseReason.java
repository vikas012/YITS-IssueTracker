package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the issue_pause_reason database table.
 * 
 */
@Entity
@Table(name="issue_pause_reason")
@NamedQuery(name="IssuePauseReason.findAll", query="SELECT i FROM IssuePauseReason i")
public class IssuePauseReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String reason;

	//bi-directional many-to-one association to Issue
	@ManyToOne
	private Issue issue;

	public IssuePauseReason() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

}