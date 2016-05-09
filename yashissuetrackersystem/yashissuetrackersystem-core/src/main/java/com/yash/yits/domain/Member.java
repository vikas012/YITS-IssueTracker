package com.yash.yits.domain;

import java.io.Serializable;
import javax.persistence.*;
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

	private BigInteger contact;

	@Column(name="CREATED_BY")
	private int createdBy;

	@Column(name="CREATED_DATE_TIME")
	private Timestamp createdDateTime;

	private String email;

	private int isactive;

	@Column(name="LAST_MODIFIED_BY")
	private int lastModifiedBy;

	@Column(name="LAST_MODIFIED_DATETIME")
	private Timestamp lastModifiedDatetime;

	@Column(name="MEMBER_ID")
	private int memberId;

	private String name;

	//bi-directional many-to-one association to ApplicationTeamMember
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<ApplicationTeamMember> applicationTeamMembers;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="MANAGER_ID")
	private Member member;

	//bi-directional many-to-one association to Member
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<Member> members;

	public Member() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getContact() {
		return this.contact;
	}

	public void setContact(BigInteger contact) {
		this.contact = contact;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public int getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(int lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Timestamp getLastModifiedDatetime() {
		return this.lastModifiedDatetime;
	}

	public void setLastModifiedDatetime(Timestamp lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ApplicationTeamMember> getApplicationTeamMembers() {
		return this.applicationTeamMembers;
	}

	public void setApplicationTeamMembers(List<ApplicationTeamMember> applicationTeamMembers) {
		this.applicationTeamMembers = applicationTeamMembers;
	}

	public ApplicationTeamMember addApplicationTeamMember(ApplicationTeamMember applicationTeamMember) {
		getApplicationTeamMembers().add(applicationTeamMember);
		applicationTeamMember.setMember(this);

		return applicationTeamMember;
	}

	public ApplicationTeamMember removeApplicationTeamMember(ApplicationTeamMember applicationTeamMember) {
		getApplicationTeamMembers().remove(applicationTeamMember);
		applicationTeamMember.setMember(null);

		return applicationTeamMember;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Member addMember(Member member) {
		getMembers().add(member);
		member.setMember(this);

		return member;
	}

	public Member removeMember(Member member) {
		getMembers().remove(member);
		member.setMember(null);

		return member;
	}

}