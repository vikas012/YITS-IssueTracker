package com.yash.yits.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.form.ApplicationForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;

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
	@RequestMapping(value="/defaultIssueTypes",method=RequestMethod.GET)
	public List<String> defaultIssueTypes(){
	
		List<String> issueTypes=issueService.getDefaultIssueTypes();
		
		return issueTypes;
	}
	
	@ResponseBody
	@RequestMapping(value="/getProjects",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> getProjects()
	{
		System.out.println("in controller for show projects");
		List<ProjectForm> projectForms=issueService.getProjectNames();
		System.out.println(projectForms);
		Map<String,Object> map = new HashMap<String, Object>();
		List<ApplicationForm> applicationForms = issueService.getApplicationNames();
		map.put("application",applicationForms);
		map.put("projects", projectForms);
		map.put("myValue", "Hie there");
		
		//System.out.println("Applications >>"+map.get("application"));
		return map;

	}
	
	
	@ResponseBody
	@RequestMapping(value="/getAllSelectFields/{projectId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public String getAllSelectFields(@PathVariable("projectId") int projectId,HttpServletRequest httpServletRequest )
	{
		System.out.println("project Id>>>>"+projectId);
		MemberForm member = new MemberForm();
		ProjectForm projectForm = new ProjectForm();
		projectForm.setId(projectId);
		member.setMemberId((Long)httpServletRequest.getSession().getAttribute("memberId"));
		System.out.println("Member ID>>>> "+member.getMemberId());
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1 = issueService.getAllSelectFields(projectForm,member);
		System.out.println("Map value  "+map1.get("issueType"));
		map1.put("myValue1", "Hello there");
		Gson gson= new Gson();
		return gson.toJson(map1);

	}

	@RequestMapping(value="/getAssignIssueForm")
	public String getAssignIssueForm()
	{
		return "redirect:/static/ManagerAssignIssue.html";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/issue/assign")
	public List<IssueForm> getUnassignedIssues(){
		List<IssueForm> unassignedIssueList=issueService.getUnassignedIssues();
		return unassignedIssueList;
	}
	
	@ResponseBody
	@RequestMapping(value="/fetchIssueDetails")
	public IssueForm fetchIssueDetails(@PathVariable int index){
		System.out.println("unassigned Controller");
		IssueForm issueForm=issueService.fetchIssueDetails(index);
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/createIssue",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public String createIssue(@RequestBody IssueForm issueForm,HttpServletRequest httpServletRequest){
		long createdBy=(Long)httpServletRequest.getSession().getAttribute("memberId");
		
		
		System.out.println("Create Issue "+createdBy);
		//issueForm.setCreatedBy(createdBy);
		System.out.println(issueForm);
		System.out.println("Project createIssue "+issueForm.getProject());
		System.out.println(issueForm.getApplicationIssuePriority());
		System.out.println(issueForm.getComponent());
		System.out.println(issueForm.getDescription());
		System.out.println(issueForm.getSummary());
		System.out.println("Application Issue type "+issueForm.getApplicationIssueType());
		System.out.println("in create issue");
		System.out.println("Application Issue Owner   "+issueForm.getIssueOwner().getMember());
		Long issueOwnerMemberId = issueForm.getIssueOwner().getMember().getMemberId();
		
		issueService.createIssue(issueForm,createdBy,issueOwnerMemberId);
		System.out.println("in controller for show projects");
		return null;

	}

	
	@ResponseBody
	@RequestMapping(value="/getdropdowns",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> getDropDownListForAdvSearch()
	{
		System.out.println("in controller for show projects");
		List<ProjectForm> projectForms=issueService.getProjectNames();
		System.out.println(projectForms);
		
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		List<ApplicationForm> applicationForms = issueService.getApplicationNames();
		map.put("application",applicationForms);
		map.put("projects", projectForms);
		map.put("myValue", "Hie there");
		
		//System.out.println("Applications >>"+map.get("application"));
		return map;

	}
	
	

}
