package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the issuefeedcomment database table.
 * 
 */
@Entity
@NamedQuery(name="IssueFeedComment.findAll", query="SELECT i FROM IssueFeedComment i")
public class IssueFeedComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ISSUEFEEDCOMMENT_ID")
	private int issueFeedCommentId;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUEFEEDCOMMENT_DATE")
	private Date issueFeedCommentDate;

	@Lob
	@Column(name="ISSUEFEEDCOMMENT_DESCRIPTION")
	private String issueFeedCommentDescription;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	//bi-directional many-to-one association to Issuefeed
	@ManyToOne
	@JoinColumn(name="ISSUEFEED_ID")
	private IssueFeed issueFeed;

	public IssueFeedComment() {
	}

	public int getIssueFeedCommentId() {
		return this.issueFeedCommentId;
	}

	public void setIssuefeedcommentId(int issueFeedCommentId) {
		this.issueFeedCommentId = issueFeedCommentId;
	}

	public Date getIssueFeedCommentDate() {
		return this.issueFeedCommentDate;
	}

	public void setIssuefeedcommentDate(Date issueFeedCommentDate) {
		this.issueFeedCommentDate = issueFeedCommentDate;
	}

	public String getIssueFeedCommentDescription() {
		return this.issueFeedCommentDescription;
	}

	public void setIssueFeedCommentDescription(String issueFeedCommentDescription) {
		this.issueFeedCommentDescription = issueFeedCommentDescription;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IssueFeed getIssueFeed() {
		return this.issueFeed;
	}

	public void setIssueFeed(IssueFeed issueFeed) {
		this.issueFeed = issueFeed;
	}

}