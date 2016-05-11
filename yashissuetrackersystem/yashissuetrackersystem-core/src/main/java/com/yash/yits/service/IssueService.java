package com.yash.yits.service;

import java.util.List;

import com.yash.yits.form.ProjectForm;
import com.yash.yits.domain.Issue;


public interface IssueService {

	public List<Issue> getUnassignedIssues();

	
	public List<ProjectForm> getProjectNames();
	
	
	
}
