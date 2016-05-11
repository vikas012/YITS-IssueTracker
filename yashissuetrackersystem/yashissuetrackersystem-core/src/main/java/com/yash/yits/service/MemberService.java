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



/**
 * @author somesh.kumar
 *
 */
public interface MemberService {
	
	
	public InitialDirContext  checkUser(LdapUser ldapUser);
	
	 public void fetchAttributes(InitialDirContext intialDirContext,String name) throws NamingException;
		public Member addMember(Member member);
		public List<Member> showMembers();
		public List<Member> searchMembers(String search);
		public List<Member> deleteMember(int memberId);


}
