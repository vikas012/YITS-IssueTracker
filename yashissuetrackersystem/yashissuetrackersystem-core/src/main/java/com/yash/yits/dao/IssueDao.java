package com.yash.yits.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Project;
import com.yash.yits.form.MemberForm;

public interface IssueDao {

	public List<Issue> getDefaultIssues(Timestamp beforeTimestamp, Timestamp afterTimestamp);

	public List<Project> getProjectNames();

	public List<Issue> getUnassignedIssues();

	public Map<String, Object> getAllSelectFields(Project project, MemberForm member);

}
