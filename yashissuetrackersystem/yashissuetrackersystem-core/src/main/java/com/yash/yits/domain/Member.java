package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


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
	private Timestamp createdDateTime;

	private String email;

	private int isActive;

	@Column(name="LAST_MODIFIED_BY")
	private int lastModifiedBy;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDateTime;

	@Column(name="MEMBER_ID")
	private int memberId;
	
	@Column(name="MANAGER_ID")
	private int managerId;
	
	@Column(name="MANAGER_NAME")
	private String managerName;
	
	@Column(name="MANAGER_EMAIL")
	private String managerEmail;

	private String name;

	//bi-directional many-to-one association to ApplicationTeamMember
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<ApplicationTeamMember> applicationTeamMembers;

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

	

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Timestamp getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Timestamp lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers() {
		return applicationTeamMembers;
	}

	public void setApplicationTeamMembers(List<ApplicationTeamMember> applicationTeamMembers) {
		this.applicationTeamMembers = applicationTeamMembers;
	}

}