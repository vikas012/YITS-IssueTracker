package com.yash.yits.service;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import com.yash.yits.form.LoginForm;
import com.yash.yits.form.UserForm;

public interface LoginService {
	
	/**
	 * checkuser  first checks the user in data base ,if not persist then insert into database
	 * @param loginForm
	 * @return IntialDircontext, connect with Ldap server
	 */
	
	public InitialDirContext  checkUser(LoginForm loginForm);
	
	/**
	 * Fetch the Attributes of the particular User
	 * @param ctx
	 * @param name
	 * @return UserForm Object to controller
	 * @throws NamingException
	 */
	
	public UserForm fetchAttributes(InitialDirContext ctx,String name) throws NamingException;

	
	
	
	
	
}
