package com.yash.yits.domain;

import java.io.Serializable;


import javax.persistence.*;




import org.hibernate.annotations.LazyCollection;

import org.hibernate.annotations.LazyCollectionOption;



import org.hibernate.annotations.LazyCollectionOption;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;




import java.sql.Timestamp;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Long contact;

	@Column(name="CREATED_BY")
	private int createdBy;

	@Column(name="CREATED_DATE_TIME")
	private Date createdDateTime;

	private String email;

	private int isActive;

	@Column(name="LAST_MODIFIED_BY")
	private int lastModifiedBy;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Date lastModifiedDateTime;

	@Column(name="MEMBER_ID")
	private Long memberId;
	
	@Column(name="MANAGER_ID")
	private Long managerId;
	
	@Column(name="MANAGER_NAME")
	private String managerName;
	
	@Column(name="MANAGER_EMAIL")
	private String managerEmail;

	private String name;


	public Member() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(int lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public List<ApplicationTeamMember> getApplicationTeamMembers() {
		return applicationTeamMembers;
	}

	public void setApplicationTeamMembers(List<ApplicationTeamMember> applicationTeamMembers) {
		this.applicationTeamMembers = applicationTeamMembers;
	}
*/
	

	@Override
	public String toString() {
		return "Member [contact=" + contact + ", email=" + email
				+ ", memberId=" + memberId + ", managerId=" + managerId
				+ ", managerName=" + managerName + ", managerEmail="
				+ managerEmail + ", name=" + name + "]";
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

}