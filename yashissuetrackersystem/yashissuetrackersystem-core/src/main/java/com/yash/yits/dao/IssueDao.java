package com.yash.yits.dao;

import java.sql.Timestamp;
import java.util.List;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Project;

public interface IssueDao {

	public List<Issue> getDefaultIssues(Timestamp beforeTimestamp, Timestamp afterTimestamp);

	public List<Project> getProjectNames();

	public List<Issue> getUnassignedIssues();
	public void createIssue(Issue issue,Long createdBy);

}
