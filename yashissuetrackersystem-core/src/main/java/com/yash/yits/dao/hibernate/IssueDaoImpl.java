package com.yash.yits.dao.hibernate;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.IssueAssignedStatus;
import com.yash.yits.domain.IssuePriority;
import com.yash.yits.domain.IssueStatus;
import com.yash.yits.domain.IssueType;
import com.yash.yits.domain.Project;
import com.yash.yits.domain.User;


/**This is a IssueDao. This object will communicate with db.
 * This will be responsible for issue related operations.*/
@Repository
public class IssueDaoImpl implements IssueDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<IssuePriority> getPriorities() {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(IssuePriority.class);
		List<IssuePriority> priorities = criteria.list();
		
		return priorities;
	}

	public List<Project> getProjects() {
	
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Project.class);
		List<Project> projects = criteria.list();
		return projects;
	}

	public List<IssueType> getIssueType() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(IssueType.class);
		List<IssueType> types = criteria.list();
		return types;
	}

	public List<IssueAssignedStatus> getAssignedStatus() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(IssueAssignedStatus.class);
		List<IssueAssignedStatus> assignedStatus = criteria.list();
		return assignedStatus;
	}

	public List<IssueStatus> getIssueStatus() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(IssueStatus.class);
		List<IssueStatus> issueStatus = criteria.list();
		return issueStatus;
	}
	
	public List<User> getAssigneeList() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		List<User> assigneeList = criteria.list();
		return assigneeList;
	}

	public int createIssue(Issue issue) {
		
		System.out.println(issue.getUser().getUserId());
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(issue);
		return 1;		// alter
	}
	
	/** search method is responsible for getting default objects from Issue class
	 * @return Issue
	 * */
	
	public List<Issue> getDefaultIssues(String date1, String date2) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("From Issue where issueCreationDate BETWEEN '"+date1+"' AND '"+date2+"'");
		
		List<Issue> issues=query.list();
		
		session.close();
		return issues;
	}
	
	/**
	 * search method is responsible for searching objects in Issue class with respect to String 'searchText'
	 * @return Issue
	 */
	public List<Issue> search(String searchText) {
		Session session = sessionFactory.getCurrentSession();
		Query query=session.createQuery("FROM Issue where "
				+ "issueType=(Select issueId from IssueType where issueType LIKE '"+searchText+"%') OR "
				+ "issuePriority=(Select issuePriorityId from IssuePriority where issuePriorityType LIKE '"+searchText+"%') OR "
				+ "user=(Select userId from User where userName LIKE '"+searchText+"%') OR "
				+ "issueStatus=(Select issueStatusId from IssueStatus where issueStatusType LIKE '"+searchText+"%') OR "
				+ "project=(Select projectId from Project where projectName LIKE '"+searchText+"%') OR "
				+ " issueAssignedStatus=(Select issueAssignmentStatusId from IssueAssignedStatus where issueAssignmentStatus LIKE '"+searchText+"%')");
		
		List<Issue> issues=query.list();
		
		return issues;
	}
}
