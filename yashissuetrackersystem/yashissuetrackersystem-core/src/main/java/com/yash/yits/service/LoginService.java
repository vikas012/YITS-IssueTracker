package com.yash.yits.service;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import com.yash.yits.form.LoginForm;
import com.yash.yits.form.UserForm;

public interface LoginService {
	
	public InitialDirContext  checkUser(LoginForm loginForm);
	public UserForm fetchAttributes(InitialDirContext ctx,String name) throws NamingException;
	
	
}
