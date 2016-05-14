package com.yash.yits.dao;

import java.util.List;

import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;

/** This is an interface for MemberDaoImpl*/
public interface MemberDao {

	
	public Member addMember(Member member);
	public List<Member> showMembers();
	public List<Member> searchMembers(String search);
	public void deleteMember(Member member);
	public void blockUnblockMember(Member member);
	public List<Issue> showAssignedIssue();
	public List<Issue> searchAssignedIssue(String searchText);
	
}
