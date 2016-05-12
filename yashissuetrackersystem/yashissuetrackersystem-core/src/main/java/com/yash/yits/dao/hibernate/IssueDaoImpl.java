package com.yash.yits.dao.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Project;
import com.yash.yits.domain.Issue;

@Repository
public class IssueDaoImpl implements IssueDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Project> getProjectNames() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Project.class)
				
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		List<Project> projects= criteria.list();
		System.out.println("in dao "+projects);
		return projects;
	}

	
	public List<Issue> getDefaultIssues(Date date1, Date date2) {
		
		Session session=sessionFactory.getCurrentSession();
		/*Query query=session.createQuery("FROM Issue WHERE createdDateTime BETWEEN '"+date1+"' AND '"+date2+"'");
		*/
		//List<Issue> issues=query.list();
		
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Issue.class)
				.setProjection(Projections.projectionList()
				.add(Projections.property("id"),"id")
				.add(Projections.property("closeDate"),"closeDate")
				.add(Projections.property("createdDateTime"),"createdDateTime")
				.add(Projections.property("dueDate"),"dueDate")
				.add(Projections.property("issueOwner"),"issueOwner")
				.add(Projections.property("project"),"project")
				.add(Projections.property("applicationIssuePriority"),"applicationIssuePriority")
				.add(Projections.property("applicationIssueStatus"),"applicationIssueStatus")
				.add(Projections.property("createdBy"),"createdBy")
				.add(Projections.property("assignedUser"),"assignedUser")
				.add( Projections.property("summary"), "summary")
				.add(Projections.property("applicationIssueType"),"applicationIssueType"))
				.add(Restrictions.between("createdDateTime", date1, date2))
				.setResultTransformer(Transformers.aliasToBean(Issue.class));
				List<Issue> issues=criteria.list();
				
		//criteria.add(Restrictions.between("createdDateTime", date1, date2));
		
		//List<Issue> issues=criteria.list();
		
		/*Query query=session.createSQLQuery("SELECT * from ISSUE where created_date_time between '"+date1+"' AND '"+date2+"'");*/
		
		//List<Issue> issues=query.list();
		
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
