package com.yash.yits.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;

@Repository
public class IssueDaoImpl implements IssueDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Issue> getUnassignedIssues() {
		
		Session session=sessionFactory.openSession();
		Query criteria=session.createQuery("from Issue where assignedUser=0");
		List unassignedIssueList=criteria.list();
		return unassignedIssueList;
		
	}

}
