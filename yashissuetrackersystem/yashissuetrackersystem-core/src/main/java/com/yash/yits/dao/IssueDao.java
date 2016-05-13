package com.yash.yits.dao;


import java.util.Date;
import java.util.List;
import java.util.Set;

import com.yash.yits.domain.ApplicationIssueType;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Project;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;

public interface IssueDao {

	public List<Issue> getDefaultIssues(Date date3, Date date4);

	public List<Project> getProjectNames();

	public List<Issue> getUnassignedIssues();

	public void createIssue(Issue issue,Long createdBy);


	public void getAllSelectFields(Project project, MemberForm member);

	public List<ApplicationIssueType> getDefaultIssueTypes();


}
