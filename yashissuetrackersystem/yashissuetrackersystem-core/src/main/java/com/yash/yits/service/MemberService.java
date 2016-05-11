/**
 * 
 */
package com.yash.yits.service;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import com.yash.yits.form.LdapUser;



/**
 * @author somesh.kumar
 *
 */
public interface MemberService {
	
	public InitialDirContext  checkUser(LdapUser ldapUser);
	
	 public void fetchAttributes(InitialDirContext intialDirContext,String name) throws NamingException;

}
