package com.yash.yits.dao;


import java.util.Date;
import java.util.List;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Project;

public interface IssueDao {

	public List<Issue> getDefaultIssues(Date date3, Date date4);

	public List<Project> getProjectNames();

	public List<Issue> getUnassignedIssues();

}
