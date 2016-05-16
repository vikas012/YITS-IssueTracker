package com.yash.yits.service;

import java.text.ParseException;
import java.util.List;
import com.yash.yits.domain.Issue;

import java.util.Set;
import java.util.Map;

import com.yash.yits.form.ApplicationForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.Member;



public interface IssueService {


	List<IssueForm> showIssuesList(long memberId);

	public int managerCreateIssue(IssueForm issueForm,Long createdBy,Long issueOwnerMemberId)throws ParseException ;

	public List<IssueForm> getDefaultIssues();


	public List<IssueForm> getUnassignedIssues();

	
	public List<ProjectForm> getProjectNames();

	public Map<String, Object> getAllSelectFields(ProjectForm projectForm, MemberForm member);
	

	public int createIssue(IssueForm issueForm,Long createdBy,Long issueOwnerMemberId);
	
	public List<ApplicationForm> getApplicationNames();

	public List<String> getDefaultIssueTypes();

	public List<IssueForm> searchIssueByType(String type);

	public IssueForm fetchIssueDetails(int fetchId);


	
}
