/**
 * 
 */
package com.yash.yits.controller;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	/**
	 * @return login page
	 */
	
	@RequestMapping(value="/loginForm")
	public String getLoginForm()
	{
		
		return "/Login.html";
	}
	
	/**
	 * 
	 * @param loginForm
	 * @return UserForm Object
	 * @throws NamingException
	 */
			
	
	@ResponseBody
	@RequestMapping(value="/loginUser",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView validateUser(@ModelAttribute LoginForm loginForm,HttpServletRequest httpServletRequest) throws NamingException
	{
		ModelAndView modelAndView;
		
		
		/**
		 * loginService CheckUser first check the user in database, if not persist then insert into database
		 */
		InitialDirContext ctx=loginService.checkUser(loginForm);
		
		/**
		 *finding the index of '@' in user email 
		 */
		
		int position=loginForm.getUsername().indexOf("@");
		/**
		 * retrieve the user name before the '@' symbol
		 */
		String username=loginForm.getUsername().substring(0,position);
		
		/**
		 *loginService fetchAttributes fetch the attributes of particular user 
		 */
		
		UserForm userForm=loginService.fetchAttributes(ctx,username);
		/**
		 * returning the userForm Object
		 */
	
		httpServletRequest.getSession().setAttribute("UserFormObject",userForm);;
		if(userForm.getUserJobTitle().equals("Trainee Programmer")){
			
			 modelAndView= new ModelAndView("UserDashboard");
			 modelAndView.addObject("username",userForm.getUserName());
			 
		}
		else{
			
			modelAndView= new ModelAndView("ManagerDashboard");
			modelAndView.addObject("username",userForm.getUserName());
		}
		
	
		return modelAndView;
		
		
	}
}
