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
import com.yash.yits.domain.MemberType;
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
	/**
	 * This method searches member list .
	 */
	@ResponseBody
	@RequestMapping(value="/memberList",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MemberForm> showMembersList(){
		
		List<MemberForm> membersList=memberService.showMembers();
		
	/*for (MemberForm member : membersList) {
		System.out.println(member.getName());
	}*/
		return membersList;
		
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
	 * This method deletes member.
	 */
	@ResponseBody
	@RequestMapping(value="/deleteMember",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteMember(@RequestBody MemberForm memberForm){
		
		memberService.deleteMember(memberForm);
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/memberType",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> memberType(){
		List<String> memberTypes=memberService.memberType();
		
		return memberTypes;
	
	}
	@ResponseBody
	@RequestMapping(value="/searchMemberType/{memberId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MemberForm> searchMemberType(@PathVariable("memberId") int memberId){
		System.out.println("---MemberId---"+ memberId);
		List<MemberForm> memberTypeList=memberService.searchMemberType(memberId);
		
		return memberTypeList;

	}
}