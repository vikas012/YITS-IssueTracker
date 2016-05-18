/**
 * 
 */
package com.yash.yits.controller;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.domain.MemberType;
import com.yash.yits.form.LdapUser;
import com.yash.yits.form.LoginForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.UserForm;
import com.yash.yits.service.MemberService;

/**
 * @author somesh.kumar
 *
 */
@Controller
public class AddMemberController {
	
	@Autowired
	private MemberService memberService;
	
	LoginForm loginForm=new LoginForm();
	UserForm userForm=null;
	MemberType memberType=new MemberType();
	
	@RequestMapping(value="/showYashForm")
	public String getCreateIssueForm(){
			
		return "redirect:/static/AddMember.html" ;
	}
	
	@ResponseBody 
	@RequestMapping(value="/checkMemberInLdap" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserForm checkMemberInLdap(@RequestBody LdapUser ldapUser,HttpServletRequest httpServletRequest) throws NamingException{
			
		/*System.out.println("inside controller--------checkUserInLdap!!!!!!-----"+httpServletRequest.getSession().getAttribute("username"));*/
		
		/*loginForm.setUsername((String) httpServletRequest.getSession().getAttribute("username"));
		loginForm.setPassword((String) httpServletRequest.getSession().getAttribute("password"));*/
		
		InitialDirContext intialDirContext=(InitialDirContext) httpServletRequest.getSession().getAttribute("IntialDirContext");
		
		/*InitialDirContext intialDirContext=memberService.checkUser(loginForm);*/
		
		int position=ldapUser.getLdapEmail().indexOf("@");
		
		String username=ldapUser.getLdapEmail().substring(0,position);
		
		userForm=memberService.fetchAttributes(intialDirContext,username);
		
		return userForm;
	}
	@ResponseBody 
	@RequestMapping(value="/registerYashMember" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean registerMember(@RequestBody MemberForm memberForm){
		
		System.out.println("in side register "+ memberForm.getEmail()+"------"+memberForm.getContact());
		memberForm.setManagerEmail(userForm.getUserManagerEmail());
		memberForm.setManagerName(userForm.getUserManagerName());
		memberForm.setManagerId(userForm.getUserManagerId());
		memberType.setId(1);
		memberForm.setMemberType(memberType);
		return memberService.addMember(memberForm);
			
	}
	@ResponseBody 
	@RequestMapping(value="/registerMember" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean registerNonYashMember(@RequestBody MemberForm memberForm){
		
		memberType.setId(2);
		memberForm.setMemberType(memberType);
		return memberService.addMember(memberForm);
			
	}

}
