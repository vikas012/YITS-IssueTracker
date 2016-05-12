package com.yash.yits.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.form.IssueForm;

import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;
import com.google.gson.Gson;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.domain.Issue;
import com.yash.yits.service.IssueService;

/**
 * This is a IssueController. This object will communicate with front-end.
 * This will be responsible for managing flow related to issues.
 */

@Controller
public class IssueController {
	

	@Autowired
	private IssueService issueService;

	
	@RequestMapping(value="/showCreateIssueForm")
	public String getCreateIssueForm()
	{
		System.out.println("---getCreateIssueForm----");
		return "redirect:/static/UserCreateIssueForm.html";
	}
	
	

	
	

	@RequestMapping(value="/issues",method=RequestMethod.GET)
	public String showIssuePage(){
		System.out.println("issuePage");
		return"redirect:/static/ManagerSearchIssue.html";
	}
	
	@ResponseBody
	@RequestMapping(value="/defaultIssues",method=RequestMethod.GET)
	public List<IssueForm> defaultIssues(){
		List<IssueForm> issues=issueService.getDefaultIssues();

		return issues;
	}
	
	@ResponseBody
	@RequestMapping(value="/getProjects",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectForm> getProjects()
	{
		System.out.println("in controller for show projects");
		List<ProjectForm> projectForms=issueService.getProjectNames();
		System.out.println(projectForms);
		
		
		return projectForms;

	}

	@RequestMapping(value="/getAssignIssueForm")
	public String getAssignIssueForm()
	{
		return "redirect:/static/ManagerAssignIssue.html";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/issue/assign")
	public List<Issue> getUnassignedIssues(){
		System.out.println("unassigned controller");
		List unassignedIssueList=issueService.getUnassignedIssues();
		return unassignedIssueList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/createIssue",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public String createIssue(@RequestBody IssueForm issueForm,HttpServletRequest httpServletRequest){
		long createdBy=(Long)httpServletRequest.getSession().getAttribute("memberId");

		
		System.out.println(createdBy);
		//issueForm.setCreatedBy(createdBy);
		System.out.println(issueForm);
		System.out.println(issueForm.getProject());
		System.out.println(issueForm.getApplicationIssuePriority());
		System.out.println(issueForm.getComponent());
		System.out.println(issueForm.getDescription());
		System.out.println("in create issue");
		//System.out.println(issue);
		//System.out.println(issue.getComponent());
		issueService.createIssue(issueForm);
		System.out.println("in controller for show projects");
		return null;

	}

	

}
