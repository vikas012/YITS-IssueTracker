package com.yash.yits.dao.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import java.util.List;
import java.util.Set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Project;

import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationEnvironment;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.ApplicationIssueType;
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
	
	public List<Application> getApplicationNames() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Application.class)
				
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Application.class));
		List<Application> application= criteria.list();
		System.out.println("in dao application "+application);
		return application;
	}


	public List<Issue> getUnassignedIssues() {

		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Issue.class)
				.setProjection(Projections.projectionList()
				.add(Projections.property("id"),"id")
				.add(Projections.property("project"),"project")
				.add(Projections.property("applicationIssuePriority"),"applicationIssuePriority")
				.add( Projections.property("summary"), "summary")
				.add(Projections.property("applicationIssueType"),"applicationIssueType"))
				.add(Restrictions.isNull("assignedUser"))
				.setResultTransformer(Transformers.aliasToBean(Issue.class));
		List<Issue> unassignedIssueList=criteria.list();
		return unassignedIssueList;

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
			//	.add(Projections.property("issueOwner"),"issueOwner")
				.add(Projections.property("project"),"project")
				.add(Projections.property("applicationIssuePriority"),"applicationIssuePriority")
				.add(Projections.property("applicationIssueStatus"),"applicationIssueStatus")
			//	.add(Projections.property("createdBy"),"createdBy")
			//	.add(Projections.property("assignedUser"),"assignedUser")
				.add( Projections.property("summary"), "summary")
				.add(Projections.property("applicationIssueType"),"applicationIssueType"))
				.add(Restrictions.between("createdDateTime", date1, date2))
				.setResultTransformer(Transformers.aliasToBean(Issue.class));
				List<Issue> issues=criteria.list();
				
		//criteria.add(Restrictions.between("createdDateTime", date1, date2));
		
		//List<Issue> issues=criteria.list();
		
		/*Query query=session.createSQLQuery("SELECT * from ISSUE where created_date_time between '"+date1+"' AND '"+date2+"'");*/
		
		//List<Issue> issues=query.list();
		
		System.out.println(issues+"issues!!");
		
		return issues;

	}




	public void createIssue(Issue issue,Long createdBy,Long issueOwnerMemberId) {

		Session session=sessionFactory.getCurrentSession();
		int createdBy1=findMemberId(createdBy);
		ApplicationTeamMember applicationTeamMember=new ApplicationTeamMember();
		applicationTeamMember.setId(createdBy1);
		
		issue.setCreatedBy(applicationTeamMember);
		
		ApplicationTeamMember applicationTeamMember2 = new ApplicationTeamMember();
		applicationTeamMember2.setId(findMemberId(issueOwnerMemberId));
		
		issue.setIssueOwner(applicationTeamMember2);
		
		session.saveOrUpdate(issue);
		
		
	}
	
	public int findMemberId(Long memberId){
		Session session=sessionFactory.getCurrentSession();
		
		Criteria criteria=session.createCriteria(ApplicationTeamMember.class);
		criteria.setProjection(Projections.projectionList()
			    .add(Projections.property("id"), "id"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationTeamMember.class));
		Criteria criteria1=criteria.createCriteria("member");
		criteria1.add(Restrictions.eq("memberId", memberId));
		
		
		ApplicationTeamMember applicationTeamMember=(ApplicationTeamMember)criteria.uniqueResult();
		int id=applicationTeamMember.getId();
		
		System.out.println(" Application Team menber ID"+applicationTeamMember.getId());
		
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
		
		
		List<ApplicationIssuePriority> applicationIssuePriority = getApplicationIssuePriority(application);
		System.out.println("ApplicationIssuePriority "+applicationIssuePriority);
		
		
		
		List<ApplicationIssueType> applicationIssueType=getApplicationIssueType(application);
		System.out.println("ApplicationIssueType "+applicationIssueType);
		
		
		List<ApplicationEnvironment> applicationEnvironment = getApplicationEnvironment(application);
		System.out.println("ApplicationEnvironment "+applicationEnvironment);
		
		
		List<Member> members = getApplicationMembers(application);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationTeamMembers", members);
		map.put("issuePriority", applicationIssuePriority);
		map.put("issueType", applicationIssueType);
		map.put("applicationEnvironment",applicationEnvironment);
		return map;
		

	}
	
	public List<Member> getApplicationMembers(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria5 = session.createCriteria(Application.class);		
		criteria5.add(Restrictions.eq("id", application.getId()));
		List<Application> applications=criteria5.list();
		System.out.println("ApplicationTeamMember "+applications);
		
		
		
		List<ApplicationTeamMember> applicationTeamMembers = application.getApplicationTeamMembers();
		System.out.println("ApplicationTeam Member "+applicationTeamMembers);
		
		Iterator<ApplicationTeamMember> iterator = applicationTeamMembers.iterator();
		List<Member> members = new ArrayList<Member>();
		
		while (iterator.hasNext()) {
			ApplicationTeamMember applicationTeamMember = (ApplicationTeamMember) iterator.next();
			System.out.println(applicationTeamMember);
			members.add( applicationTeamMember.getMember());
			
		}
		
		System.out.println("Members member "+members);
		
		
		return members;
		
	}
	
	public List<ApplicationEnvironment> getApplicationEnvironment(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria4 = session.createCriteria(ApplicationEnvironment.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("environment"), "environment"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationEnvironment.class));
		criteria4.add(Restrictions.eq("application", application));
		
		List<ApplicationEnvironment> applicationEnvironment=criteria4.list();
		return applicationEnvironment;
		
	}
	
	
	public List<ApplicationIssueType> getApplicationIssueType(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria3 = session.createCriteria(ApplicationIssueType.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("type"), "type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssueType.class));
		criteria3.add(Restrictions.eq("application", application));
		
		List<ApplicationIssueType> applicationIssueType=criteria3.list();
		return applicationIssueType;
		
	}
	
	public List<ApplicationIssuePriority> getApplicationIssuePriority(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicationIssuePriority.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("type"), "type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssuePriority.class));
		criteria.add(Restrictions.eq("application", application));
		
		List<ApplicationIssuePriority> applicationIssuePriority=criteria.list();
		return applicationIssuePriority;
		
	}
	


	public List<ApplicationIssueType> getDefaultIssueTypes() {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(ApplicationIssueType.class)
				.setProjection(Projections.projectionList()
						 .add(Projections.property("id"), "id")
						.add(Projections.property("type"),"type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssueType.class));
		List<ApplicationIssueType> issueTypes=criteria.list();
		
		return issueTypes;
	}



	public List<ApplicationIssuePriority> getDefaultIssuePriorities() {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(ApplicationIssuePriority.class)
				.setProjection(Projections.projectionList()
						 .add(Projections.property("id"), "id")
						.add(Projections.property("type"),"type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssuePriority.class));
		List<ApplicationIssuePriority> issuePriorities=criteria.list();
		
		return issuePriorities;
	}

	public List<Project> getDefaultProjectNames() {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Project.class)
				.setProjection(Projections.projectionList()
						 .add(Projections.property("id"), "id")
						.add(Projections.property("name"),"name"))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		List<Project> projects=criteria.list();
		
		return projects;
	}

	
	
	
	
	
	
	
	public Issue fetchIssueDetails(int fetchId) {
		System.out.println("prajvi dao");
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Issue where id="+fetchId);
		Issue issue=(Issue)query.uniqueResult();
		return null;
	}
	

	

}
