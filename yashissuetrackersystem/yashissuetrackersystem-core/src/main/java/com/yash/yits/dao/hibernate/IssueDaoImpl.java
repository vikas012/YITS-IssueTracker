package com.yash.yits.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;

@Repository
public class IssueDaoImpl implements IssueDao {

	public List<Issue> getDefaultIssues(String date1, String date2) {
		// TODO Auto-generated method stub
		return null;
	}

}
