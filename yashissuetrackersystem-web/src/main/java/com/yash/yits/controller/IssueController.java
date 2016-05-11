package com.yash.yits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.form.IssueForm;
import com.yash.yits.service.IssueService;

/**
 * This is a IssueController. This object will communicate with front-end.
 * This will be responsible for managing flow related to issues.
 */

@Controller
public class IssueController {
	
	
	@RequestMapping(value="/showCreateIssueForm")
	public String getCreateIssueForm()
	{
		return "redirect:/static/UserCreateIssueForm.html";
	}
	
	@Autowired
	private IssueService issueService;
	
	@RequestMapping(value="/issues",method=RequestMethod.GET)
	public String showIssuePage(){
		//List<IssueForm> issues=issueService.getDefaultIssues();
		//model.addAttribute("issueList",issues);
		return"redirect:/static/ManagerSearchIssue.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/defaultIssues",method=RequestMethod.GET)
	public List<IssueForm> defaultIssues(){
		//List<IssueForm> issues=issueService.getDefaultIssues();
		//model.addAttribute("issueList",issues);
		
		return null;
	}
}
