package com.yash.yits.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
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
	
	public List<Issue> getUnassignedIssues() {
		
		Session session=sessionFactory.openSession();
		Query criteria=session.createQuery("from Issue where assignedUser=0");
		List unassignedIssueList=criteria.list();
		return unassignedIssueList;
		
	}

}
