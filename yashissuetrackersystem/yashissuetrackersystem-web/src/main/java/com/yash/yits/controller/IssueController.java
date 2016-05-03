package com.yash.yits.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.form.IssueForm;
import com.yash.yits.service.IssueService;

/**This is a IssueController. This object will communicate with front-end.
 * This will be responsible for managing flow related to issues.*/
@Controller
public class IssueController {

	@Autowired
	IssueService issueService;
	
	@RequestMapping(value="/createUserIssueView",method=RequestMethod.GET)
	public String userIssueForm(){
		return"UserCreateIssueForm";
	}
	
	@RequestMapping(value="/createManagerIssueView",method=RequestMethod.GET)
	public String managerIssueForm(){
		return"ManagerCreateIssueForm";
	}
	
	@RequestMapping(value="/getPriority",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getPriority(){
		List<Object> formList = new ArrayList<Object>();
		formList.add(issueService.getPriorities());
		formList.add(issueService.getAssigneeList());
		formList.add(issueService.getIssueType());
		formList.add(issueService.getProjects());
		return formList;
	}
	
	
	
	/**
	* showDetails method will display the issues which are created in the current week. This method will 
	* call the IssueSearch service method to get the issues.
	* @return String
	*/

	@RequestMapping(value="/showIssues",method=RequestMethod.GET)
	public String showDetais(Model model){
		List<IssueForm> issues=issueService.getDefaultIssues();
		model.addAttribute("issueList",issues);
		return"SearchIssue";
	}
	
	@RequestMapping(value="/createIssue",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String submitCreateIssueForm(@RequestBody IssueForm issueForm){

		System.out.println(issueForm.getIssueDueDate());
		System.out.println(issueForm.getIssueAssignedStatus().getIssueAssignmentStatusId());//
		System.out.println(issueForm.getIssueStatus().getIssueStatusId());//
		
		
		issueService.createIssue(issueForm);
		return "success";
		
	}
	
	
	/**
	* searchIssues method will display the issues with respect to the search value. This method will 
	* call the IssueSearch service method to get the issues.
	* @return issuesList
	*/
	@ResponseBody
	@RequestMapping(value="/getIssues/{searchText}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<IssueForm> searchIssues(@PathVariable("searchText") String searchText){

			List<IssueForm> issues=issueService.search(searchText);
		
		return issues; 
	}
}