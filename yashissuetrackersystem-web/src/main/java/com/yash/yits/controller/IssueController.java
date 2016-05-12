package com.yash.yits.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.form.IssueForm;

import org.springframework.web.bind.annotation.ResponseBody;


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
	public Map<String,Object> getProjects()
	{
		System.out.println("in controller for show projects");
		List<ProjectForm> projectForms=issueService.getProjectNames();
		System.out.println(projectForms);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("projects", projectForms);
		map.put("myValue", "Hie there");
		return map;

	}
	
	
	@ResponseBody
	@RequestMapping(value="/getAllSelectFields/{projectId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> getAllSelectFields(@PathVariable("projectId") int projectId,HttpServletRequest httpServletRequest )
	{
		System.out.println("project Id>>>>"+projectId);
		Member member = new Member();
		
		member.setMemberId((Long)httpServletRequest.getSession().getAttribute("memberId"));
		System.out.println("Member ID>>>> "+member.getMemberId());
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("myValue", "Hello there");
		return map;

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
