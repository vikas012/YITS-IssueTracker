package com.yash.yits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	

	@ResponseBody
	@RequestMapping(value="/getProjects",produces=MediaType.APPLICATION_JSON_VALUE)
	public String getProjects()
	{
		System.out.println("in controller for show projects");
		List<ProjectForm> projectForms=issueService.getProjectNames();
		System.out.println(projectForms);
		
		Gson gson= new Gson();
		return gson.toJson(projectForms);
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
	

	
}
