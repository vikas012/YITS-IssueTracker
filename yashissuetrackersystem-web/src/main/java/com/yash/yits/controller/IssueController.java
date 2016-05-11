package com.yash.yits.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.domain.Issue;
import com.yash.yits.service.IssueService;

/**
 * This is a IssueController. This object will communicate with front-end.
 * This will be responsible for managing flow related to issues.
 */

@Controller
public class IssueController {
	@Autowired
	IssueService issueService;
	
	@RequestMapping(value="/showCreateIssueForm")
	public String getCreateIssueForm()
	{
		System.out.println("---getCreateIssueForm----");
		return "redirect:/static/UserCreateIssueForm.html";
	}
	
	@RequestMapping(value="/showEditIssueForm")
	public String showEditForm()
	{
		return "redirect:/static/EditIssue.html";
	}
	
	@RequestMapping(value="/defaultIssuesList",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> showIssuesList(HttpServletRequest httpServletRequest){
		System.out.println("In controller");
		long memberId=1004686;
		/*long memberId = (Long) httpServletRequest.getSession().getAttribute("memberId");*/
		System.out.println(memberId);
		List<Issue> issuesList = issueService.showIssuesList(memberId);
		return issuesList;
	}
}
