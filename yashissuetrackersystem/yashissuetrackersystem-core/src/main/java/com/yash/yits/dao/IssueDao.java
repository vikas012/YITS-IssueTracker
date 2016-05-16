package com.yash.yits.dao;


import java.util.Date;
import java.util.List;

import com.yash.yits.domain.ApplicationIssueType;

import java.util.Map;

import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.Project;
import com.yash.yits.form.MemberForm;



public interface IssueDao {

	public List<Issue> getDefaultIssues(Date date3, Date date4);

	public List<Project> getProjectNames();

	public List<Issue> getUnassignedIssues();

	public int managerCreateIssue(Issue issue,Long createdBy,Long issueOwnerMemberId);

	List<Issue> showIssuesList(long memberId);

	public int createIssue(Issue issue,Long createdBy,Long issueOwnerMemberId);

	public List<ApplicationIssueType> getDefaultIssueTypes();

	public Map<String, Object> getAllSelectFields(Project project, MemberForm member);

	public List<Issue> searchIssueByType(String type);

	public List<Application> getApplicationNames();

	public Issue fetchIssueDetails(int fetchId);

}
