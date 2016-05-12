/**
 * 
 */
package com.yash.yits.service;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import com.yash.yits.domain.Member;
import com.yash.yits.form.LdapUser;

import com.yash.yits.form.MemberForm;

import com.yash.yits.form.LoginForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.UserForm;




/**
 * @author somesh.kumar
 *
 */
public interface MemberService {
	
	
	public InitialDirContext  checkUser(LoginForm loginForm);
	public UserForm fetchAttributes(InitialDirContext intialDirContext,String name) throws NamingException;
	public Member addMember(MemberForm memberForm);
	public List<Member> showMembers();
	public List<MemberForm> searchMembers(String search);
	public List<Member> deleteMember(int memberId);
	public List<Member> blockUnblockMember(Member member);


}
