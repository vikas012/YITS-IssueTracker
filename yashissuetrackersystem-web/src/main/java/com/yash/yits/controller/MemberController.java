package com.yash.yits.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yash.yits.domain.Member;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.LdapUser;



import com.yash.yits.form.LoginForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.UserForm;

import com.yash.yits.service.IssueService;
import com.yash.yits.service.MemberService;

/**This is a MemberController. This object will communicate with front-end 
 * This will be responsible for managing flow related to members.*/

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	LoginForm loginForm=new LoginForm();
	UserForm userForm=null;
	
	@RequestMapping(value="/showYashForm")
	public String getCreateIssueForm(){
			
		return "redirect:/static/AddMember.html" ;
	}
	
	@RequestMapping(value="/showMembersPage")
	public String showMembersPage(){
			
		return "redirect:/static/ShowMember.html" ;
	}
	
	@ResponseBody
	@RequestMapping(value="/memberList",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Member> showMembersList(){
		System.out.println("for member list");
	List<Member> membersList=memberService.showMembers();
		
		return membersList;
		//return "succes";
	}
	@ResponseBody 
	@RequestMapping(value="/checkMemberInLdap" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserForm checkMemberInLdap(@RequestBody LdapUser ldapUser,HttpServletRequest httpServletRequest) throws NamingException{
			
		System.out.println("inside controller--------checkUserInLdap!!!!!!-----"+httpServletRequest.getSession().getAttribute("username"));
		loginForm.setUsername((String) httpServletRequest.getSession().getAttribute("username"));
		loginForm.setPassword((String) httpServletRequest.getSession().getAttribute("password"));
		
		InitialDirContext intialDirContext=memberService.checkUser(loginForm);
		
		int position=ldapUser.getLdapEmail().indexOf("@");
		
		String username=ldapUser.getLdapEmail().substring(0,position);
		
		userForm=memberService.fetchAttributes(intialDirContext,username);
		
		return userForm;
	}
	@ResponseBody 
	@RequestMapping(value="/registerMember" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void registerMember(@RequestBody MemberForm memberForm){
		
		System.out.println("in side register "+ memberForm.getEmail()+"------"+memberForm.getContact());
		memberForm.setManagerEmail(userForm.getUserManagerEmail());
		memberForm.setManagerName(userForm.getUserManagerName());
		memberForm.setManagerId(userForm.getUserManagerId());
		memberService.addMember(memberForm);
			
	}
	
	
}