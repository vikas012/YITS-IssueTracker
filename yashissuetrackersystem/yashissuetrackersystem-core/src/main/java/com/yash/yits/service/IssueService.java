package com.yash.yits.service;

import java.util.List;
import java.util.Map;

import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;


public interface IssueService {


	public List<IssueForm> getDefaultIssues();

	public List<IssueForm> getUnassignedIssues();
	
	public List<ProjectForm> getProjectNames();

	public Map<String, Object> getAllSelectFields(ProjectForm projectForm, MemberForm member);
	
	public void createIssue(IssueForm issueForm,Long createdBy);
	
}
