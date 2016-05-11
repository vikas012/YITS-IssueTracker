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
	
	public List<Issue> showIssuesList(long memberId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Issue where issueOwner="
				+ "(Select member from ApplicationTeamMember where member="
				+ "(Select memberId from Member where memberId =?))");
		query.setParameter(0, memberId);
		
		List<Issue> issuesList = query.list();
		
		/*Criteria criteria = session.createCriteria(Issue.class);
		criteria.add(Restrictions.sqlRestriction("Select member from ApplicationTeamMember"));
		criteria.add(Restrictions.sqlRestriction("Select memberId from Member where memberId =?"));
		where member=(Select memberId from Member where memberId ="+memberId+""));		
		List<Issue> issuesList = criteria.list();*/
		return issuesList;
	}

}
