package com.yash.yits.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService{
	
	@Autowired
	IssueDao issueDao;
	
	public List<Issue> getUnassignedIssues() {
		List unassignedIssueList=issueDao.getUnassignedIssues();
		return unassignedIssueList;
	}

	
	
	
}
