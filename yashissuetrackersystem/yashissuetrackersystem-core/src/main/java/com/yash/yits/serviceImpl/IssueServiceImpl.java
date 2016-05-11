package com.yash.yits.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.service.IssueService;

@Service
@Transactional
public class IssueServiceImpl implements IssueService{
	@Autowired
	IssueDao issueDao;
	
	public List<Issue> showIssuesList(long memberId) {
		List<Issue> issuesList = issueDao.showIssuesList(memberId);
		return issuesList;
	}

	
	
	
}
