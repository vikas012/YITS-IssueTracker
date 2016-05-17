/**
 * 
 */
package com.yash.yits.service;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import com.yash.yits.domain.Member;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.LoginForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.UserForm;




/**
 * @author somesh.kumar
 *
 */
public interface MemberService {
	
	public UserForm fetchAttributes(InitialDirContext intialDirContext,String name) throws NamingException;
	public boolean addMember(MemberForm memberForm);
	public List<MemberForm> showMembers();
	public List<MemberForm> searchMembers(String search);
	public void deleteMember(MemberForm memberForm);
	public void blockUnblockMember(MemberForm memberForm);
	public List<IssueForm> showAssignedIssue();
	public List<IssueForm> searchAssignedIssue(String searchText);
	public List<String> memberType();
	public List<MemberForm> searchMemberType(int memberId);
}
