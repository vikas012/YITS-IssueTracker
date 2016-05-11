package com.yash.yits.dao;

import java.util.List;

import com.yash.yits.domain.Issue;



public interface IssueDao {

	List<Issue> getDefaultIssues(String date1, String date2);

	
}
