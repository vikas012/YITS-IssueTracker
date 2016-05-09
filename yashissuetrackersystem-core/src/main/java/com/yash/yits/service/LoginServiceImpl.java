/**
 * 
 */
package com.yash.yits.service;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.LoginDao;
import com.yash.yits.form.LoginForm;
import com.yash.yits.form.UserForm;

/**
 * @author somesh.kumar
 *
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	public InitialDirContext checkUser(LoginForm loginForm) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserForm fetchAttributes(InitialDirContext ctx, String name) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
