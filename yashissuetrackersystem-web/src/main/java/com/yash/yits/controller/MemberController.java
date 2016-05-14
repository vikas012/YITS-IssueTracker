package com.yash.yits.controller;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.domain.Member;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.LdapUser;
import com.yash.yits.form.LoginForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.UserForm;
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

	@RequestMapping(value="/showSearchMember")
	public String showSearchMember(){
			
		return "redirect:/static/showSearchMember.html" ;
	}
	
	@RequestMapping(value="/showAssignedIssuePage")
	public String showAssignedIssuePage(){
			
		return "redirect:/static/ShowAssignedIssue.html" ;
	}

	@ResponseBody
	@RequestMapping(value="/searchMember/{searchText}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MemberForm> searchMember(@PathVariable("searchText") String searchText){
			
		System.out.println("in controller" +searchText);
			List<MemberForm> members=memberService.searchMembers(searchText);
			for(MemberForm membersList:members){
				
				System.out.println(membersList.getContact());
				
			}
		return members; 
	}
	
	@ResponseBody
	@RequestMapping(value="/memberList",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Member> showMembersList(){
		System.out.println("for member list");
		List<Member> membersList=memberService.showMembers();
		
	for (Member member : membersList) {
		System.out.println(member.getName());
	}
		return membersList;
		
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
	@RequestMapping(value="/registerYashMember" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void registerMember(@RequestBody MemberForm memberForm){
		
		System.out.println("in side register "+ memberForm.getEmail()+"------"+memberForm.getContact());
		memberForm.setManagerEmail(userForm.getUserManagerEmail());
		memberForm.setManagerName(userForm.getUserManagerName());
		memberForm.setManagerId(userForm.getUserManagerId());
		memberService.addMember(memberForm);
			
	}
	@ResponseBody 
	@RequestMapping(value="/registerMember" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void registerNonYashMember(@RequestBody MemberForm memberForm){
		
		System.out.println("in side register "+ memberForm.getEmail()+"------"+memberForm.getContact());
		memberService.addMember(memberForm);
			
	}
	
	/**
	 *blockUnblockMember method is used to block or unblock the member
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/blockUnblockMember")
	public void blockUnblockMember(@RequestBody MemberForm memberForm) {
		
		System.out.println("in block unblock controller"+memberForm.getMemberId());
		memberService.blockUnblockMember(memberForm);
		
	}
	
	/**
	 * This method returns list of issues assigned to a members
	 */
	@ResponseBody
	@RequestMapping(value="/showAssignedIssue")
	public List<IssueForm> showAssignedIssue(){
		return memberService.showAssignedIssue();
	}
	
	/**
	 * This method searches list of issues assigned to a members
	 */
	@ResponseBody
	@RequestMapping(value="/searchAssignedIssue/{searchText}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<IssueForm> searchAssignedIssue(@PathVariable("searchText") String searchText){
		return memberService.searchAssignedIssue(searchText);
	}
}