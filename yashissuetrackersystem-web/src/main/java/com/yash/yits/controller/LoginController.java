/**
 * 
 */
package com.yash.yits.controller;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yash.yits.form.LoginForm;
import com.yash.yits.form.UserForm;
import com.yash.yits.service.LoginService;

/**
 * @author somesh.kumar
 *
 */
@Controller

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	
	@RequestMapping(value="/loginForm")
	public String getLoginForm(){
		
		return "redirect:/static/Login.html" ;
	}
	
	@RequestMapping(value="/loginUser",method=RequestMethod.POST)
	public String validateUser(@ModelAttribute LoginForm loginForm,HttpServletRequest httpServletRequest) throws NamingException{
		
		System.out.println("in controller");
		
		InitialDirContext intialDirContext=loginService.checkUser(loginForm);
		
		int position=loginForm.getUsername().indexOf("@");
		//retrieve the substring before the '@'
		String username=loginForm.getUsername().substring(0,position);
		
		UserForm userForm=loginService.fetchAttributes(intialDirContext,username);
		
		httpServletRequest.getSession().setAttribute("memberId",userForm.getUserId());
		httpServletRequest.getSession().setAttribute("username",loginForm.getUsername());
		httpServletRequest.getSession().setAttribute("password",loginForm.getPassword());
		
		System.out.println(userForm.getUserJobTitle());
		if(userForm.getUserJobTitle().equals("Trainee Programmer"))
		{	
			return "redirect:/static/UserDashboard.html";
		}
		else
		{
			return "redirect:/static/ManagerDashboard";
		}
	}

}
