package com.yash.yits.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="member_type")
public class MemberType  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	 
	@Column(name="TYPE")
	private String memberType;
	 
	@Column(name="CREATED_DATETIME")
	private Date createdDateTime;

	private int isActive;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Date lastModifiedDateTime;
	 
	@Column(name="LAST_MODIFIED_BY")
	private int lastModifiedBy;
	 
	@Column(name="CREATED_BY")
	private int createdBy;
	
	/*//bi-directional many-to-one association to Member
	@OneToMany(mappedBy="memberTypeBean", fetch=FetchType.EAGER)
	private List<Member> members;*/
	
	public int getId() {
	return id;
	}

	public void setId(int id) {
	this.id = id;
	}

	public String getMemberType() {
	return memberType;
	}

	public void setMemberType(String memberType) {
	this.memberType = memberType;
	}

	public Date getCreatedDateTime() {
	return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
	this.createdDateTime = createdDateTime;
	}

	public int getIsActive() {
	return isActive;
	}

	public void setIsActive(int isActive) {
	this.isActive = isActive;
	}

	public Date getLastModifiedDateTime() {
	return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
	this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public int getLastModifiedBy() {
	return lastModifiedBy;
	}

	public void setLastModifiedBy(int lastModifiedBy) {
	this.lastModifiedBy = lastModifiedBy;
	}

	public int getCreatedBy() {
	return createdBy;
	}

	public void setCreatedBy(int createdBy) {
	this.createdBy = createdBy;
	}


}
