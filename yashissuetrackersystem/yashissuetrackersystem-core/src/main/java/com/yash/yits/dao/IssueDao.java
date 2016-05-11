package com.yash.yits.dao;

import java.util.List;

import com.yash.yits.domain.Issue;



public interface IssueDao {

	public List<Issue> getUnassignedIssues();

	
}
