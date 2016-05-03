package com.yash.yits.service;

import java.util.List;


import com.yash.yits.form.IssueAssignedStatusForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.IssuePriorityForm;
import com.yash.yits.form.IssueStatusForm;
import com.yash.yits.form.IssueTypeForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.form.UserForm;


public interface IssueService {

	public List<IssuePriorityForm> getPriorities();
	
	public List<ProjectForm> getProjects();
	
	public List<IssueTypeForm> getIssueType();
	
	public List<IssueAssignedStatusForm> getAssignedStatus();
	
	public List<IssueStatusForm> getIssueStatus();
	
	public List<UserForm> getAssigneeList();
	
	public int createIssue(IssueForm issueForm);

	public List<IssueForm> getDefaultIssues();

	public List<IssueForm> search(String searchText);
	
	
}
