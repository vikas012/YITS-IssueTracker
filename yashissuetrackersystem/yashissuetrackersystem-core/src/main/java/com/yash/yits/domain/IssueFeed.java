package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the issuefeed database table.
 * 
 */
@Entity
@NamedQuery(name="IssueFeed.findAll", query="SELECT i FROM IssueFeed i")
public class IssueFeed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ISSUEFEED_ID")
	private int issueFeedId;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUEFEED_DATE")
	private Date issueFeedDate;

	@Lob
	@Column(name="ISSUEFEED_DESCRIPTION")
	private String issueFeedDescription;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	//bi-directional many-to-one association to Issue
	@ManyToOne
	@JoinColumn(name="ISSUE_ID")
	private Issue issue;

	//bi-directional many-to-one association to Issuefeedcomment
	@OneToMany(mappedBy="issueFeed")
	private List<IssueFeedComment> issueFeedComments;

	public IssueFeed() {
	}

	public int getIssueFeedId() {
		return this.issueFeedId;
	}

	public void setIssueFeedId(int issueFeedId) {
		this.issueFeedId = issueFeedId;
	}

	public Date getIssueFeedDate() {
		return this.issueFeedDate;
	}

	public void setIssueFeedDate(Date issueFeedDate) {
		this.issueFeedDate = issueFeedDate;
	}

	public String getIssueFeedDescription() {
		return this.issueFeedDescription;
	}

	public void setIssuefeedDescription(String issueFeedDescription) {
		this.issueFeedDescription = issueFeedDescription;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public List<IssueFeedComment> getIssueFeedComments() {
		return this.issueFeedComments;
	}

	public void setIssuefeedcomments(List<IssueFeedComment> issueFeedComments) {
		this.issueFeedComments = issueFeedComments;
	}

	public IssueFeedComment addIssuefeedcomment(IssueFeedComment issueFeedComment) {
		getIssueFeedComments().add(issueFeedComment);
		issueFeedComment.setIssueFeed(this);

		return issueFeedComment;
	}

	public IssueFeedComment removeIssueFeedComment(IssueFeedComment issueFeedComment) {
		getIssueFeedComments().remove(issueFeedComment);
		issueFeedComment.setIssueFeed(null);

		return issueFeedComment;
	}

}