package com.yash.yits.dao;

import java.sql.Timestamp;
import java.util.List;

import com.yash.yits.domain.Issue;

import com.yash.yits.domain.Issue;



public interface IssueDao {


	List<Issue> getDefaultIssues(Timestamp beforeTimestamp, Timestamp afterTimestamp);

	public List<Issue> getUnassignedIssues();


	
}
