package com.yash.yits.dao.hibernate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.PreparedStatement;
import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Project;

import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;

import com.yash.yits.form.MemberForm;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationEnvironment;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.ApplicationIssueStatus;
import com.yash.yits.domain.ApplicationIssueType;
import com.yash.yits.domain.Issue;


@Repository
public class IssueDaoImpl implements IssueDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public List<Issue> showIssuesList(long memberId) {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT * FROM Issue WHERE OWNER=(SELECT member_Id FROM application_team_member WHERE member_id=(SELECT Id FROM member WHERE member_Id="+memberId+"))");
		
			Iterator iterator=query.list().iterator();
			List<Issue> listOfIssues=new ArrayList<Issue>();
			
			List<Member> listOfMember=new ArrayList<Member>();
			
			Member member3=null;
			
			while(iterator.hasNext()){
				
				Object[] object=(Object[])iterator.next();
				Issue issue=new Issue();
				
				issue.setId((Integer)object[0]);
				
				issue.setSummary((String)object[1]);
				
				issue.setOriginalEstimate((String)object[6]);
				issue.setRemainingEstimate((String)object[7]);
				
				Member member=new Member();
				int memberId1=(Integer)object[8];
				Query query3=session.createSQLQuery("Select member_Id  from member where Id=(Select member_Id from application_team_member where id="+memberId1 +")");
				
				Iterator iterator2=query3.list().iterator();
				
				while(iterator2.hasNext()){
					
					BigInteger bigInteger=(BigInteger)iterator2.next();
					
					System.out.println(bigInteger.longValue());
					
					member.setMemberId(bigInteger.longValue());
					
				}
				
				
				ApplicationTeamMember applicationTeamMember= new ApplicationTeamMember();
				applicationTeamMember.setMember(member);
				
				issue.setIssueOwner(applicationTeamMember);
				
				issue.setDueDate((Date)object[2]);
				
				issue.setTaskProgressUpdate((String)object[11]);
				
				ApplicationIssueType applicationIssueType= new ApplicationIssueType();
				
				int id=(Integer)object[13];
				
				Query query1=session.createQuery("from ApplicationIssueType where Id=?");
				
				query1.setInteger(0, id);
				
				List<ApplicationIssueType> listOfApplicationIssueType=query1.list();
				for (ApplicationIssueType applicationIssueType2 : listOfApplicationIssueType) {
					
					applicationIssueType.setType(applicationIssueType2.getType());
					
				}
				issue.setApplicationIssueType(applicationIssueType);
				
				ApplicationIssuePriority applicationIssuePriority=new ApplicationIssuePriority();
				int prirorityId=(Integer)object[14];
				
				Query query2=session.createQuery("from ApplicationIssuePriority where Id=?");
				
				query2.setInteger(0, prirorityId);
				
				List<ApplicationIssuePriority> listOfApplicationIssuePriority=query2.list();
				for (ApplicationIssuePriority applicationIssuePriority2 : listOfApplicationIssuePriority) {
					
					applicationIssuePriority.setType(applicationIssuePriority2.getType());
					
				}
				
				issue.setApplicationIssuePriority(applicationIssuePriority);
				
				int statusId = (Integer)object[16];
				Query query4 = session.createQuery("from ApplicationIssueStatus where id=?");
				query4.setParameter(0, statusId);
				
				ApplicationIssueStatus applicationIssueStatus = new ApplicationIssueStatus();
				
				List<ApplicationIssueStatus> listOfApplicationIssueStatus=query4.list();
				for (ApplicationIssueStatus applicationIssueStatus2 : listOfApplicationIssueStatus) {
					applicationIssueStatus.setStatus(applicationIssueStatus2.getStatus());
				}
				
				issue.setApplicationIssueStatus(applicationIssueStatus);
				
				listOfIssues.add(issue);
				
			}
	
		return listOfIssues;
	}


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
		
		
		/*Criteria criteria=session.createCriteria(Issue.class);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.isNull("a"));
		criteria.add(disjunction);*/
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Issue.class)
				.setProjection(Projections.projectionList()
						.add( Projections.property("summary"), "summary")
						.add(Projections.property("applicationIssueType"),"applicationIssueType")
						.add(Projections.property("project"),"project")					
											
						.add(Projections.property("applicationIssuePriority"),"applicationIssuePriority"))
						.add(Restrictions.isNull("assignedUser"))
				.setResultTransformer(Transformers.aliasToBean(Issue.class));
		List<Issue> unassignedIssueList=criteria.list();
		List<Issue> list=new ArrayList<Issue>();
		Iterator<Issue> iterator=unassignedIssueList.iterator();
		return unassignedIssueList;

}
	public List<Issue> getDefaultIssues(Timestamp date1, Timestamp date2) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createSQLQuery("SELECT * FROM Issue WHERE created_Date_Time BETWEEN '"+date1+"' AND '"+date2+"'");
		List<Issue> issueList=new ArrayList<Issue>();
		List<Object> issues=query.list();
		
		for (Object object : issues) {
			
			Issue issue=(Issue)object;
			System.out.println(issue);
			issueList.add(issue);
		}
		
		return issueList;

	}


	public void createIssue(Issue issue,Long createdBy) {
		Session session=sessionFactory.getCurrentSession();
		int createdBy1=findMemberId(createdBy);
		ApplicationTeamMember applicationTeamMember=new ApplicationTeamMember();
		applicationTeamMember.setId(createdBy1);
		issue.setCreatedBy(applicationTeamMember);
		session.save(issue);
		
		
	}
	
	public int findMemberId(Long memberId){
		Session session=sessionFactory.getCurrentSession();
		
		Criteria criteria=session.createCriteria(ApplicationTeamMember.class);
		criteria.setProjection(Projections.projectionList()
			    .add(Projections.property("id"), "id"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationTeamMember.class));
		Criteria criteria1=criteria.createCriteria("member");
		criteria1.add(Restrictions.eq("memberId", memberId));
		
		System.out.println(criteria.uniqueResult());
		
		
		ApplicationTeamMember applicationTeamMember=(ApplicationTeamMember)criteria.uniqueResult();
		int id=applicationTeamMember.getId();
		return id ;
		
	}

	public Map<String, Object> getAllSelectFields(Project project, MemberForm member) {

		System.out.println("In DAO for all select fields "+project.getId()+" "+member.getMemberId());
		Session session=sessionFactory.getCurrentSession();
		
		Criteria criteria2 = session.createCriteria(Project.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("name"), "name")
					      .add(Projections.property("application"), "application"))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		criteria2.add(Restrictions.eq("id",project.getId()));
		
		Project project3 = (Project) criteria2.uniqueResult();
		
		Application application=new Application();
		
		application= project3.getApplication();
		System.out.println("Application  "+application);
		
		
		Criteria criteria = session.createCriteria(ApplicationIssuePriority.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("type"), "type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssuePriority.class));
		criteria.add(Restrictions.eq("application", application));
		
		List<ApplicationIssuePriority> applicationIssuePriority=criteria.list();
		System.out.println("ApplicationIssuePriority "+applicationIssuePriority);
		
		
		Criteria criteria3 = session.createCriteria(ApplicationIssueType.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("type"), "type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssueType.class));
		criteria3.add(Restrictions.eq("application", application));
		
		List<ApplicationIssueType> applicationIssueType=criteria3.list();
		System.out.println("ApplicationIssueType "+applicationIssueType);
		
		
		
		Criteria criteria4 = session.createCriteria(ApplicationEnvironment.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("environment"), "environment"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationEnvironment.class));
		criteria4.add(Restrictions.eq("application", application));
		
		List<ApplicationEnvironment> applicationEnvironment=criteria4.list();
		System.out.println("ApplicationEnvironment "+applicationEnvironment);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issuePriority", applicationIssuePriority);
		map.put("issueType", applicationIssueType);
		map.put("applicationEnvironment",applicationEnvironment);
		return map;
		


	}


	/*public int getIssueId(long memberId) {
		int id=0;
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Member where managerId=?");
		query.setParameter(0, memberId);
		List<Member> members = query.list();
		
		for (Member member : members) {
			id = member.getId();
			
		}
		return id;
	}
*/
}
