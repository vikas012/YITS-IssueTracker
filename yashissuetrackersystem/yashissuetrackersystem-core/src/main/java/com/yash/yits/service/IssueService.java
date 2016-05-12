package com.yash.yits.service;

import java.util.List;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.domain.Issue;


public interface IssueService {


	public List<IssueForm> getDefaultIssues();

	public List<Issue> getUnassignedIssues();
	
	public List<ProjectForm> getProjectNames();
	
	
	
}
