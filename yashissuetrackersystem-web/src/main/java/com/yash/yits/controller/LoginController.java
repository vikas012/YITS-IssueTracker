/**
 * 
 */
package com.yash.yits.controller;

import java.io.IOException;
import java.util.Properties;

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
	
	
	

	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logOutMember(HttpServletRequest httpServletRequest){
		
		httpServletRequest.getSession().invalidate();
		return "Welcome";
	}
	
	@RequestMapping(value="/loginUser",method=RequestMethod.POST)
	public String validateUser(@ModelAttribute LoginForm loginForm,HttpServletRequest httpServletRequest) throws NamingException, IOException{
		
		
		Properties properties= new Properties();
		properties.load(getClass().getResourceAsStream("UserTitle.properties"));
		String title=(String) properties.get("Title");
		
		
		
		
		InitialDirContext intialDirContext=loginService.checkUser(loginForm);
		
		if(intialDirContext==null)
		{
			
			httpServletRequest.getSession().setAttribute("errorMessage","Incorrect Username or Password");
			return "Welcome" ; 
		}
		
		int position=loginForm.getUsername().indexOf("@");
		
		//retrieve the substring before the '@'
		String username=loginForm.getUsername().substring(0,position);
		
		UserForm userForm=loginService.fetchAttributes(intialDirContext,username);
		
		httpServletRequest.getSession().setAttribute("memberId",userForm.getUserId());
		
		httpServletRequest.getSession().setAttribute("userName",username);
		
		httpServletRequest.getSession().setAttribute("IntialDirContext",intialDirContext);
		
		
		if(userForm.getUserJobTitle().equals(title))
		{	
			return "redirect:/static/UserDashboard.html";
		}
		else
		{

			return "redirect:/static/ManagerDashboard.html";

		}
	}


}
