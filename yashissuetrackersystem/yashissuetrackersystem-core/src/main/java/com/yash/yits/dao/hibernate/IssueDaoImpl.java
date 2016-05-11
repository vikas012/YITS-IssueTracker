package com.yash.yits.dao.hibernate;

import java.sql.Timestamp;

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

	private SessionFactory sessionFactory;
	
	public List<Issue> getDefaultIssues(Timestamp date1, Timestamp date2) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createSQLQuery("SELECT * FROM Issue WHERE created_Date_Time BETWEEN '"+date1+"' AND '"+date2+"'");
		
		List<Issue> issues=query.list();
		
		System.out.println(issues);
		
		return issues;

	}
	public List<Issue> getUnassignedIssues() {
		
		Session session=sessionFactory.getCurrentSession();
		Query criteria=session.createQuery("from Issue where assignedUser=0");
		List unassignedIssueList=criteria.list();
		return unassignedIssueList;
		

	}

}
