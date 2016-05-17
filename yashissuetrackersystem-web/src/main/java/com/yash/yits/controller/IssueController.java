package com.yash.yits.controller;


import java.util.List;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


import java.io.File;


import java.text.ParseException;

import java.text.SimpleDateFormat;




import java.io.FileOutputStream;

import java.io.IOException;


import java.io.InputStream;


import java.io.OutputStream;


import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.ls.LSInput;

import com.yash.yits.domain.Issue;


import com.yash.yits.form.ApplicationForm;

import com.yash.yits.form.ApplicationIssuePriorityForm;
import com.yash.yits.form.ApplicationIssueTypeForm;
import com.yash.yits.form.AttachmentForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;

import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Attachment;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;

import com.google.gson.Gson;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.service.IssueService;

/**
 * This is a IssueController. This object will communicate with front-end. This
 * will be responsible for managing flow related to issues.
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

	@RequestMapping(value = "/managerShowCreateIssueForm")
	public String getManagerCreateIssueForm() {
		System.out.println("---getCreateIssueForm----");
		return "redirect:/static/ManagerIssueCreateForm.html";
	}


	
	@RequestMapping(value="/displayEditIssueForm")
	public String showEditForm()
	{
	System.out.println("user edit issue");
		return "redirect:/static/UserEditIssue.html";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/fetchIssueDetailsConv/{id}",method=RequestMethod.POST)
	public IssueForm fetchIssueDetailsConv(@PathVariable("id") int id){
		
		System.out.println("-------in conversation--------"+id);
		IssueForm issueForm=issueService.fetchIssueDetailsConv(id);
		System.out.println(issueForm);
		return issueForm;
		/*userService.fetchIssueDetailsConv(issueId);*/
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getMemberListConv",method=RequestMethod.GET)
	public List<MemberForm> getMemberListConv(){
		List<MemberForm> issues=issueService.getMemberListConv();
		System.out.println("in get member list");

		return issues;
	}
	
	@ResponseBody
	@RequestMapping(value = "/fetchIssueDetails/{fetchId}")
	public IssueForm fetchIssueDetails(@PathVariable int fetchId) {
		IssueForm issueForm = issueService.fetchIssueDetails(fetchId);
		return issueForm;
	}
	
/*
	@ResponseBody
	@RequestMapping(value = "/getMembers")
	public List<MemberForm> getMemberList() {
		List<MemberForm> memberList = issueService.getMemberList();
		return memberList;
	}

	

	@ResponseBody
	@RequestMapping(value = "/assignIssue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void assignIssue(@RequestBody IssueForm issueForm) throws ParseException {
		int fetchId = issueForm.getAssignedUser().getMember().getId();
		System.out.println("CONTROLLER " + fetchId);
		issueService.assignIssue(issueForm, fetchId);
	}*/

	@RequestMapping(value = "/defaultIssuesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<IssueForm> showIssuesList(HttpServletRequest httpServletRequest) throws ClassNotFoundException {
		System.out.println("In controller");
		long memberId = (Long) httpServletRequest.getSession().getAttribute("memberId");
		System.out.println(memberId);
		List<IssueForm> issuesList = issueService.showIssuesList(memberId);
		return issuesList;
	}


	@RequestMapping(value="/editIssueForm")
	public String showEditIssueForm()
	{

		return "redirect:/static/ManagerEditIssue.html";
	}

	@RequestMapping(value = "/showConversationForm")
	public String showConversationForm() {
		System.out.println("---getConversationIssueForm----");
		return "redirect:/static/conversationIssueForm.html";
	}

	@RequestMapping(value = "/issues", method = RequestMethod.GET)
	public String showIssuePage() {
		System.out.println("issuePage");
		return "redirect:/static/ManagerSearchIssue.html";
	}

	@RequestMapping(value = "/userIssues", method = RequestMethod.GET)
	public String showUserIssuePage() {
		System.out.println("issuePage");
		return "redirect:/static/UserSearchIssue.html";
	}

	@ResponseBody
	@RequestMapping(value = "/defaultIssues", method = RequestMethod.GET)
	public List<IssueForm> defaultIssues() {
		List<IssueForm> issues = issueService.getDefaultIssues();

		return issues;
	}

	@ResponseBody
	@RequestMapping(value = "/conversationList", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Issue> getConversationList(HttpServletRequest httpServletRequest) {
		long createdBy = (Long) httpServletRequest.getSession().getAttribute("memberId");
		System.out.println("in metjhod");

		System.out.println("--------" + createdBy);
		List<Issue> chats = issueService.getConversationList(createdBy);
		for (Issue issue : chats) {
			System.out.println("-----------------" + issue.getApplicationIssueType().getType());
			System.out.println("-----------------" + issue.getSummary());
			System.out.println("-----memeber name" + issue.getIssueOwner().getMember().getName());
			System.out.println("-----------created date time----" + issue.getCreatedDateTime());
		}

		System.out.println("in return controller");
		System.out.println(chats);
		return chats;


	}
	@ResponseBody
	@RequestMapping(value="/getMembers")
	public List<MemberForm> getMemberList(){
		List<MemberForm> memberList=issueService.getMemberList();
		return memberList;

	}

	

	
	@ResponseBody
	@RequestMapping(value="/assignIssue",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void assignIssue(@RequestBody IssueForm issueForm)throws ParseException{
		int fetchId=issueForm.getAssignedUser().getMember().getId();
		System.out.println("CONTROLLER "+fetchId);
		issueService.assignIssue(issueForm, fetchId);
	}
	

	



	
	

	@ResponseBody
	@RequestMapping(value = "/getProjects", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProjects() {
		System.out.println("in controller for show projects");
		List<ProjectForm> projectForms = issueService.getProjectNames();
		System.out.println(projectForms);
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationForm> applicationForms = issueService.getApplicationNames();
		map.put("application", applicationForms);
		map.put("projects", projectForms);
		

		// System.out.println("Applications >>"+map.get("application"));
		return map;

	}

	@ResponseBody
	@RequestMapping(value = "/getAllSelectFields/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllSelectFields(@PathVariable("projectId") int projectId, HttpServletRequest httpServletRequest) {
		System.out.println("project Id>>>>" + projectId);
		MemberForm member = new MemberForm();
		ProjectForm projectForm = new ProjectForm();
		projectForm.setId(projectId);
		member.setMemberId((Long) httpServletRequest.getSession().getAttribute("memberId"));
		System.out.println("Member ID>>>> " + member.getMemberId());
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1 = issueService.getAllSelectFields(projectForm, member);
		System.out.println("Map value  " + map1.get("issueType"));
		map1.put("myValue1", "Hello there");
		Gson gson = new Gson();
		return gson.toJson(map1);

	}


	@RequestMapping(value="/getAssignIssueForm")
	public String getAssignIssueForm()
	{

		return "redirect:/static/ManagerAssignIssue.html";
	}

	@ResponseBody
	@RequestMapping(value = "/issue/assign")
	public List<IssueForm> getUnassignedIssues() {
		List<IssueForm> unassignedIssueList = issueService.getUnassignedIssues();
		return unassignedIssueList;
	}



/*	@ResponseBody
=======

	
	
	@ResponseBody
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker
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
		System.out.println("ISSUE OWNER MEMBER ID >>>"+issueOwnerMemberId);
		int issueId=0;
		try
		{
			issueId=issueService.createIssue(issueForm,createdBy,issueOwnerMemberId);
		} catch (Exception e) {
			System.out.println("Excption "+e);
		}
		
		System.out.println("in controller for show projects");
		return null;

	}*/
	@ResponseBody
	@RequestMapping(value = "/createIssue", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public String createIssue(@RequestBody IssueForm issueForm, HttpServletRequest httpServletRequest) {
		long createdBy = (Long) httpServletRequest.getSession().getAttribute("memberId");

		System.out.println("Create Issue " + createdBy);
		// issueForm.setCreatedBy(createdBy);
		System.out.println(issueForm);
		System.out.println("Project createIssue " + issueForm.getProject());
		System.out.println(issueForm.getApplicationIssuePriority());
		System.out.println(issueForm.getComponent());
		System.out.println(issueForm.getDescription());
		System.out.println(issueForm.getSummary());
		System.out.println("Application Issue type " + issueForm.getApplicationIssueType());
		System.out.println("in create issue");
		System.out.println("Application Issue Owner   " + issueForm.getIssueOwner().getMember());
		Long issueOwnerMemberId = issueForm.getIssueOwner().getMember().getMemberId();
		System.out.println("ISSUE OWNER MEMBER ID >>>" + issueOwnerMemberId);
		int issueId = 0;
		try {
			issueId = issueService.createIssue(issueForm, createdBy, issueOwnerMemberId);
		} catch (Exception e) {
			System.out.println("Excption " + e);
		}

		System.out.println("in controller for show projects");
		return null;

	}


	@ResponseBody
	@RequestMapping(value = "/managerCreateIssue", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public String managerCreateIssue(@RequestBody IssueForm issueForm, HttpServletRequest httpServletRequest)
			throws ParseException {
		long createdBy = (Long) httpServletRequest.getSession().getAttribute("memberId");

		// long createdBy = 1004683;
		System.out.println("Create Issue " + createdBy);
		// issueForm.setCreatedBy(createdBy);
		System.out.println(issueForm);
		System.out.println("Project createIssue " + issueForm.getProject());
		System.out.println(issueForm.getApplicationIssuePriority());
		System.out.println(issueForm.getComponent());
		System.out.println(issueForm.getDescription());
		System.out.println(issueForm.getSummary());
		System.out.println("Application Issue type " + issueForm.getApplicationIssueType());
		System.out.println("in create issue");
		System.out.println("Application Issue Owner   " + issueForm.getIssueOwner().getMember().getMemberId());
		Long issueOwnerMemberId = issueForm.getIssueOwner().getMember().getMemberId();
		System.out.println("DUE DATE  >>>" + issueForm.getDueDate());
		System.out.println("Original estimate >>>" + issueForm.getOriginalEstimate());
		int issueId = 0;
		try {
			issueId = issueService.managerCreateIssue(issueForm, createdBy, issueOwnerMemberId);
		} catch (Exception e) {
			System.out.println("Excption " + e);
		}

		System.out.println("in controller for show projects");
		return null;

	}

	@ResponseBody
	@RequestMapping(value = "/getdropdowns/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Map<String, Object> getDropDownListForAdvSearch(@PathVariable("applicationId") int applicationId) {
		// System.out.println("in controller for show projects");
		// List<ProjectForm> projectForms=issueService.getProjectNames();

		List<ApplicationIssuePriorityForm> issuePriority = issueService.getDefaultIssuePriorities(applicationId);
		List<ProjectForm> project = issueService.getDefaultProjectNames(applicationId);
		List<ApplicationIssueTypeForm> issueType = issueService.getDefaultIssueTypes(applicationId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("priorities", issuePriority);
		map.put("projects", project);
		map.put("issuetypes", issueType);

		// System.out.println("Applications >>"+map.get("application"));
		return map;

	}

	@ResponseBody
	@RequestMapping(value = "/getadvsearchdata/{issuetype}/{projectname}/{priority}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IssueForm> getFilteredFeedData(@PathVariable("issuetype") String issuetypeId,
			@PathVariable("projectname") String projectnameId, @PathVariable("priority") String issuepriorityId) {

		System.out.println("-------------------" + projectnameId);
		System.out.println(issuetypeId);

		if (issuetypeId.equals("undefined")) {
			issuetypeId = "0";
		}
		int issuetypeId1 = Integer.parseInt(issuetypeId);

		if (issuepriorityId.equals("undefined")) {
			issuepriorityId = "0";
		}
		int issuepriorityId1 = Integer.parseInt(issuepriorityId);

		if (projectnameId.equals("undefined")) {
			projectnameId = "0";
		}
		int projectnameId1 = Integer.parseInt(projectnameId);

		List<IssueForm> issueForms = issueService.getFilteredIssue(issuepriorityId1, issuetypeId1, projectnameId1);
		return issueForms;
	}

	@ResponseBody
	@RequestMapping(value = "/getApplication", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ApplicationForm> getApplicationName() {
		List<ApplicationForm> applications = issueService.getApplicationNames();
		return applications;
	}

	/**
	 * Function to upload file Takes file from front-end and passes it to
	 * service layer
	 */

	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
	public String saveFile(HttpServletRequest request,@RequestParam(value="file",required=false) MultipartFile uploadedFile) throws IOException{
		 
	     Attachment file1=new Attachment();
	     file1.setFile(uploadedFile.getBytes());
	     file1.setName(uploadedFile.getOriginalFilename());
	     file1.setLabel(request.getParameter("attachmentLabel"));
	    
	   
	     return issueService.saveFile(file1);
	        

	}

	@ResponseBody
	@RequestMapping(value = "/showIssueDetails/{id}", method = RequestMethod.GET)
	public Map<String, Object> showIssueDetails(@PathVariable("id") int id) {

		System.out.println("in controller" + id);
		Map<String, Object> issues = issueService.showIssueDetails(id);

		/*
		 * List<AttachmentForm> attachmentForms=issues.getAttachmentForms(); for
		 * (AttachmentForm attachmentForm : attachmentForms) {
		 * System.out.println("--------"+attachmentForm.getId()); }
		 */

		System.out.println("issueController" + issues);
		return issues;
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public String download(@PathVariable("id") int id, HttpServletResponse response) throws IOException {

		AttachmentForm attachmentForm = issueService.getAttachment(id);

		/*
		 * String mimeType=
		 * URLConnection.guessContentTypeFromName(attachmentForm.getName());
		 * if(mimeType==null){ System.out.println(
		 * "mimetype is not detectable, will take default"); mimeType =
		 * "application/octet-stream"; }
		 * 
		 * System.out.println("mimetype : "+mimeType);
		 * 
		 * response.setContentType(mimeType);
		 */

		response.setHeader("Content-Disposition", "attachment; filename=\"" + attachmentForm.getName() + "\"");
		OutputStream out = response.getOutputStream();
		try {
			FileCopyUtils.copy(attachmentForm.getFile(), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileOutputStream fos = new FileOutputStream(attachmentForm.getLabel() + ".jpg");
		fos.write(attachmentForm.getFile());
		fos.close();
		return "index";

	}
	
	/**
	 * Method to handle requests coming from /issue/starttask url and providing updated list of issues to view.
	 */
	@RequestMapping(value="/starttask/{id}/{dueDate}")
	@ResponseBody
	public List<IssueForm> startTask(@PathVariable("id") int id,@PathVariable("dueDate") Date dueDate, HttpServletRequest httpServletRequest) throws ParseException {
		//logger.info("inside startTask() of IssueController");
		long memberId = (Long) httpServletRequest.getSession().getAttribute("memberId");
/*		 this is current date*/		
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
		String currentddate = format2.format(calendar.getTime());			//current date
		Date currentddateparsed = format2.parse(currentddate);		//current date after parsing
	
		if((dueDate.after(currentddateparsed) || dueDate.equals(currentddateparsed))){
			List<IssueForm> issues = issueService.startTask(id,memberId);

			return issues;
		}
		else{
			List<IssueForm> issues = issueService.startTaskPending(id,memberId);

			return issues;


		}
		
	}
	/**
	 * Method to handle requests coming from /issue/stoptask url and providing updated list of issues to view.
	 */
	@RequestMapping(value="/stoptask/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<IssueForm> stopTask(@PathVariable("id") int id, HttpServletRequest httpServletRequest) {
		
		long memberId = (Long) httpServletRequest.getSession().getAttribute("memberId");
		List<IssueForm> issues = issueService.stopTask(id,memberId);
		
		return issues;
	}
	
	/**
	 * Method to handle requests coming from /issue/pause task url and providing updated list of issues to view.
	 */
	@RequestMapping(value="/pausetask/{id}/{reason}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<IssueForm> pauseTask(@PathVariable("id") int id,@PathVariable("reason") String reason, HttpServletRequest httpServletRequest) {
		
		long memberId = (Long) httpServletRequest.getSession().getAttribute("memberId");
		List<IssueForm> issues = issueService.pauseTask(id,reason,memberId);
		
		return issues;
	}
	
	
	
	@RequestMapping(value="/taskProgressUpdate/{task}/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<IssueForm> taskProgressUpdate(@PathVariable("task") String task,@PathVariable("id") int id,HttpServletRequest httpServletRequest) {
		long memberId = (Long) httpServletRequest.getSession().getAttribute("memberId");
		System.out.println(task+"of"+id);
		List<IssueForm> issueForms= issueService.updateIssueTaskProgress(task, id,memberId);
		return issueForms;
	}
	
	

}
