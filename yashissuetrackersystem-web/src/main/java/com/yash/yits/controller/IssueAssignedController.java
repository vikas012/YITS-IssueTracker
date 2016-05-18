package com.yash.yits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.form.IssueForm;
import com.yash.yits.service.MemberService;


/**This is a IssueAssignedController. This object will communicate with front-end 
 * This will be responsible for managing flow related to assigned issues.*/

@Controller
public class IssueAssignedController {

	@Autowired
	private MemberService memberService;
	
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
