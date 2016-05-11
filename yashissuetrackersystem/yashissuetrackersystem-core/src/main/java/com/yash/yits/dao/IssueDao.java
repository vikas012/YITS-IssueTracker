package com.yash.yits.dao;

import java.util.List;

import com.yash.yits.domain.Project;
import com.yash.yits.domain.Issue;

public interface IssueDao {

	public List<Project> getProjectNames();

	public List<Issue> getUnassignedIssues();


}
