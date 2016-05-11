package com.yash.yits.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

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

import com.yash.yits.form.IssueForm;
import com.yash.yits.form.LdapUser;
import com.yash.yits.service.IssueService;
import com.yash.yits.service.MemberService;

/**This is a MemberController. This object will communicate with front-end 
 * This will be responsible for managing flow related to members.*/

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="/showYashForm")
	public String getCreateIssueForm(){
			
		return "redirect:/static/AddMember.html" ;
	}
	
	@ResponseBody 
	@RequestMapping(value="/checkMemberInLdap" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void checkUserInLdap(@RequestBody LdapUser ldapUser) throws NamingException{
			
		System.out.println("inside controller--------checkUserInLdap!!!!!!-----"+ldapUser.getLdapName());
		InitialDirContext intialDirContext=memberService.checkUser(ldapUser);
			memberService.fetchAttributes(intialDirContext,ldapUser.getLdapName());
	}
	
	
}