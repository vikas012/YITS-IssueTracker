package com.yash.yits.dao.hibernate;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationEnvironment;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.ApplicationIssueStatus;
import com.yash.yits.domain.ApplicationIssueType;
import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Attachment;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.IssuePauseReason;
import com.yash.yits.domain.Member;
import com.yash.yits.domain.Project;
import com.yash.yits.form.MemberForm;

@Repository
public class IssueDaoImpl implements IssueDao {

	@Autowired
	SessionFactory sessionFactory;
	public List<Issue> showIssuesList(long memberId) {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT * FROM Issue WHERE ASSIGNED_USER=(SELECT member_Id FROM application_team_member WHERE member_id=(SELECT Id FROM member WHERE member_Id="+memberId+"))");
		
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
				
				ApplicationIssueStatus applicationIssueStatus = new ApplicationIssueStatus();
				int statusId = (Integer)object[16];
				Query query4 = session.createQuery("from ApplicationIssueStatus where id=?");
				query4.setParameter(0, statusId);
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
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Project.class)

		.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
				.add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		List<Project> projects = criteria.list();
		System.out.println("in dao " + projects);
		return projects;
	}

	public List<Application> getApplicationNames() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Application.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Application.class));
		List<Application> application = criteria.list();
		System.out.println("in dao application " + application);
		return application;
	}

	public List<Issue> getUnassignedIssues() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Issue.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("project"), "project")
						.add(Projections.property("applicationIssuePriority"), "applicationIssuePriority")
						.add(Projections.property("summary"), "summary")
						.add(Projections.property("applicationIssueType"), "applicationIssueType"))
				.add(Restrictions.isNull("assignedUser")).setResultTransformer(Transformers.aliasToBean(Issue.class));
		List<Issue> unassignedIssueList = criteria.list();
		return unassignedIssueList;

	}

	public List<Issue> getDefaultIssues(Date date1, Date date2) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Issue.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("closeDate"), "closeDate")
						.add(Projections.property("createdDateTime"), "createdDateTime")
						.add(Projections.property("dueDate"), "dueDate").add(Projections.property("project"), "project")
						.add(Projections.property("applicationIssuePriority"), "applicationIssuePriority")
						.add(Projections.property("applicationIssueStatus"), "applicationIssueStatus")
						.add(Projections.property("summary"), "summary")
						.add(Projections.property("applicationIssueType"), "applicationIssueType"))
				.add(Restrictions.between("createdDateTime", date1, date2))
				.setResultTransformer(Transformers.aliasToBean(Issue.class));
		List<Issue> issues = criteria.list();
		return issues;

	}

	public int createIssue(Issue issue,Long createdBy,Long issueOwnerMemberId,Attachment attachment) {

		Session session=sessionFactory.getCurrentSession();
		
		ApplicationTeamMember applicationTeamMember=session.load(ApplicationTeamMember.class,findMemberId(createdBy).getId());

		//applicationTeamMember.setId(createdBy1);

		issue.setCreatedBy(applicationTeamMember);

		ApplicationTeamMember applicationTeamMember2 =session.load(ApplicationTeamMember.class, findMemberId(issueOwnerMemberId).getId());
		//applicationTeamMember2.setId(findMemberId(issueOwnerMemberId));

		issue.setIssueOwner(applicationTeamMember2);

		
		int issueId=0;
		issueId=(Integer) session.save(issue);
		
		
		//Attachment attachment=session.get(Attachment.class,attachmentId);
		if(attachment!=null){
		Issue issue2=session.get(Issue.class,issueId);
		attachment.setIssue(issue2);
		session.saveOrUpdate(attachment);
		}
		
		return issueId;
		

	}

	public ApplicationTeamMember findMemberId(Long memberId) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(ApplicationTeamMember.class);
		criteria.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
				.add(Projections.property("member"), "member")
				.add(Projections.property("application"), "application"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationTeamMember.class));
		Criteria criteria1 = criteria.createCriteria("member");
		criteria1.add(Restrictions.eq("memberId", memberId));

		ApplicationTeamMember applicationTeamMember = (ApplicationTeamMember) criteria.uniqueResult();
		int id = applicationTeamMember.getId();

		System.out.println(" Application Team menber ID" + applicationTeamMember.getId());

		return applicationTeamMember;

	}
	
	
	public Map<String, Object> getAllSelectFields(Application application, MemberForm member) {

		System.out.println("In DAO for all select fields "+application.getId()+" "+member.getMemberId());
		Session session=sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Project.class)
				.setProjection(Projections.projectionList()
					      .add(Projections.property("id"), "id")
					      .add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		criteria.add(Restrictions.eq("application", application));
		
		List<Project> projects=criteria.list();
		
		
		
		
		List<ApplicationIssuePriority> applicationIssuePriority = getApplicationIssuePriority(application);
		System.out.println("ApplicationIssuePriority "+applicationIssuePriority);
		
		
		
		List<ApplicationIssueType> applicationIssueType=getApplicationIssueType(application);
		System.out.println("ApplicationIssueType "+applicationIssueType);
		
		
		List<ApplicationEnvironment> applicationEnvironment = getApplicationEnvironment(application);
		System.out.println("ApplicationEnvironment "+applicationEnvironment);
		
		
		List<Member> members = getApplicationMembers(application);
		
		List<Member> allMembers = getAllMembers();
		
		System.out.println("Select all Members "+allMembers);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projects", projects);
		map.put("applicationTeamMembers", members);
		map.put("allMembers", allMembers);
		map.put("issuePriority", applicationIssuePriority);
		map.put("issueType", applicationIssueType);
		map.put("applicationEnvironment",applicationEnvironment);
		return map;
		

	}

	public List<Member> getApplicationMembers(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria5 = session.createCriteria(ApplicationTeamMember.class);
		Criteria criteria=criteria5.createCriteria("application");
		criteria.add(Restrictions.eq("id", application.getId()));
		List<ApplicationTeamMember> applicationTeamMembers = criteria5.list();
		
/*		List<ApplicationTeamMember> applicationTeamMembers = application.getApplicationTeamMembers();
		System.out.println("ApplicationTeamMembers "+applicationTeamMembers);*/
		
		Iterator<ApplicationTeamMember> iterator = applicationTeamMembers.iterator();
		Set<Member> members = new HashSet<Member>();
		
		while (iterator.hasNext()) {
			ApplicationTeamMember applicationTeamMember = (ApplicationTeamMember) iterator.next();
			System.out.println(applicationTeamMember);
			members.add(applicationTeamMember.getMember());
			
		}
		
		List<Member> members2 = new ArrayList<Member>();
		members2.addAll(members);


		return members2;

	}

	public List<ApplicationEnvironment> getApplicationEnvironment(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria4 = session.createCriteria(ApplicationEnvironment.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("environment"), "environment"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationEnvironment.class));
		criteria4.add(Restrictions.eq("application", application));

		List<ApplicationEnvironment> applicationEnvironment = criteria4.list();
		return applicationEnvironment;

	}

	public List<ApplicationIssueType> getApplicationIssueType(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria3 = session.createCriteria(ApplicationIssueType.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("type"), "type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssueType.class));
		criteria3.add(Restrictions.eq("application", application));

		List<ApplicationIssueType> applicationIssueType = criteria3.list();
		return applicationIssueType;

	}

	public List<ApplicationIssuePriority> getApplicationIssuePriority(Application application) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicationIssuePriority.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("type"), "type"))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssuePriority.class));
		criteria.add(Restrictions.eq("application", application));

		List<ApplicationIssuePriority> applicationIssuePriority = criteria.list();
		return applicationIssuePriority;

	}

	public List<ApplicationIssueType> getDefaultIssueTypes(int applicationId) {
		Application application = new Application();
		application.setId(applicationId);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicationIssueType.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("type"), "type"))
				.add(Restrictions.eq("application", application))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssueType.class));
		List<ApplicationIssueType> issueTypes = criteria.list();

		return issueTypes;
	}

	public List<ApplicationIssuePriority> getDefaultIssuePriorities(int applicationId) {

		Application application = new Application();
		application.setId(applicationId);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ApplicationIssuePriority.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("type"), "type"))
				.add(Restrictions.eq("application", application))
				.setResultTransformer(Transformers.aliasToBean(ApplicationIssuePriority.class));

		List<ApplicationIssuePriority> issuePriorities = criteria.list();

		return issuePriorities;
	}

	public List<Project> getDefaultProjectNames(int applicationId) {
		Application application = new Application();
		application.setId(applicationId);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Project.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("name"), "name"))
				.add(Restrictions.eq("application", application))
				.setResultTransformer(Transformers.aliasToBean(Project.class));
		List<Project> projects = criteria.list();

		return projects;
	}

	public List<Issue> searchIssueByType(int type) {

		Session session = sessionFactory.getCurrentSession();

		ApplicationIssueType applicationIssueType = new ApplicationIssueType();
		applicationIssueType.setId(type);

		Criteria criteria = session.createCriteria(Issue.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("closeDate"), "closeDate")
						.add(Projections.property("createdDateTime"), "createdDateTime")
						.add(Projections.property("dueDate"), "dueDate").add(Projections.property("project"), "project")
						.add(Projections.property("applicationIssuePriority"), "applicationIssuePriority")
						.add(Projections.property("applicationIssueStatus"), "applicationIssueStatus")
						.add(Projections.property("summary"), "summary")
						.add(Projections.property("applicationIssueType"), "applicationIssueType"))
				.add(Restrictions.eq("applicationIssueType", applicationIssueType))
				.setResultTransformer(Transformers.aliasToBean(Issue.class));
		List<Issue> issues = criteria.list();

		return issues;
	}

	public List<Issue> getFilteredIssue(int issuepriorityId, int issuetypeId, int projectnameId) {
		Session session = sessionFactory.getCurrentSession();

		Criteria crit = session.createCriteria(Issue.class);

		if (issuetypeId != 0) {
			Criteria issueTypeCrit = crit.createCriteria("applicationIssueType");
			issueTypeCrit.add(Restrictions.eq("id", issuetypeId));
		}
		if (projectnameId != 0) {
			Criteria projectCrit = crit.createCriteria("project");
			projectCrit.add(Restrictions.eq("id", projectnameId));
		}
		if (issuepriorityId != 0) {
			Criteria issuePriorityCrit = crit.createCriteria("applicationIssuePriority");
			issuePriorityCrit.add(Restrictions.eq("id", issuepriorityId));
		}

		List<Issue> issues = crit.list();
		return issues;
	}



	public List<Member> getAllMembers() {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Member.class);
				
		List<Member> members=criteria.list();
		
		return members;
		

	}


	public List<Issue> getConversationList(long createdBy) {
		//String query="SELECT * FROM Issue WHERE OWNER=(SELECT member_Id FROM application_team_member WHERE member_id=(SELECT Id FROM member WHERE member_Id="+1004686+"));";
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT * FROM Issue WHERE Assigned_User=(SELECT member_Id FROM application_team_member WHERE member_id=(SELECT Id FROM member WHERE member_Id="+createdBy+"))");
		//query.setParameter(0, createdBy);
		 
		
		Iterator iterator=query.list().iterator();
		List<Issue> listOfIssues=new ArrayList<Issue>();
		List<Member> listOfMember=new ArrayList<Member>();
		Member member3=null;
		while(iterator.hasNext()){
		 
		Object[] object=(Object[])iterator.next();
		Issue issue=new Issue();
		 
		issue.setId((Integer)object[0]);
		 
		issue.setSummary((String)object[1]);
		issue.setCreatedDateTime((Date)object[22]);
		issue.setDueDate((Date)object[2]);
		System.out.println("----DUE DATE-----"+issue.getDueDate());
		System.out.println("=====dao==="+issue.getCreatedDateTime());
		
		Member member=new Member();
		int memberId1=(Integer)object[8];
		Query query3=session.createSQLQuery("Select member_Id  from member where Id=(Select member_Id from application_team_member where id="+memberId1 +")");
		 
		Iterator iterator2=query3.list().iterator();
		 
		while(iterator2.hasNext()){
		 
		BigInteger bigInteger=(BigInteger)iterator2.next();
		 
		System.out.println("-------hello---------"+bigInteger.longValue());
		 
		member.setMemberId(bigInteger.longValue());
		
		 
		Query query4=session.createSQLQuery("Select Name from member where id IN (Select member_Id from application_team_member where id IN (Select CREATED_BY from Issue where Assigned_User = (Select id  from member where member_Id="+createdBy+")))");
		
		Iterator iterator4=query4.list().iterator();
		while(iterator4.hasNext()){
			String createdUserName=(String)iterator4.next();
			member.setName(createdUserName);
		}
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
		listOfIssues.add(issue);
		 
		}
		 
		return listOfIssues;
	}

	public Issue showIssueDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Issue.class);
		criteria.add(Restrictions.eq("id", id));

		Issue issues = (Issue) criteria.uniqueResult();

		return issues;
	}

	public Attachment getAttachment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Attachment.class);
		criteria.add(Restrictions.eq("id", id));

		Attachment attachment = (Attachment) criteria.uniqueResult();
		return attachment;
	}

	public Issue fetchIssueDetails(int fetchId) {


		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Issue where id=" + fetchId);
		Issue issue = (Issue) query.uniqueResult();
		return issue;

	}

public int managerCreateIssue(Issue issue, Long createdBy, Long assignee,Attachment attachment) {
		
		int issueId =0;
		Session session=sessionFactory.getCurrentSession();
		try {
		//int createdBy1=findMemberId(createdBy);
		
		
		ApplicationTeamMember applicationTeamMember=session.load(ApplicationTeamMember.class, findMemberId(createdBy).getId());
		//applicationTeamMember.setId(createdBy1);
		issue.setCreatedBy(applicationTeamMember);
		
		issue.setIssueOwner(applicationTeamMember);
		
		ApplicationTeamMember applicationTeamMember3 =session.load(ApplicationTeamMember.class, findMemberId(assignee).getId());

		issue.setAssignedUser(applicationTeamMember3);
		issueId=(Integer) session.save(issue);
	
		
		if(attachment!=null){
		
		Issue issue2=session.get(Issue.class, issueId);
		attachment.setIssue(issue2);
		session.saveOrUpdate(attachment);
		}
		
		
		
		
		} catch (Exception e) {
			System.out.println("Exception "+e);
		}
		
		
		return issueId;
	}



	public List<Member> getMemberList() {

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Member.class)
				.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
						.add(Projections.property("memberId"), "memberId")
						.add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Member.class));
		List<Member> members = criteria.list();
		return members;
	}

	public void assignIssue(Issue issue,Long memberId) {

		Session session = sessionFactory.getCurrentSession();
		System.out.println(issue.getOriginalEstimate());
		System.out.println(issue.getRemainingEstimate());
		System.out.println(issue.getAssignedUser());
		int id=issue.getId();
		Issue issue2 = session.get(Issue.class,id);
		issue2.setOriginalEstimate(issue.getOriginalEstimate());
		issue2.setRemainingEstimate(issue.getRemainingEstimate());
		System.out.println("AssignIssue MemberID "+memberId);
		ApplicationTeamMember applicationTeamMember=session.load(ApplicationTeamMember.class, findMemberId(memberId).getId());
		issue2.setAssignedUser(applicationTeamMember);
		issue2.setDueDate(issue.getDueDate());
		session.update(issue2);
	}
	
	public Issue fetchIssueDetailsConv(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Issue where id="+id);
		Issue issue=(Issue)query.uniqueResult();
		return issue;
	}


	public List<Member> getMemberListConv() {
		

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Member.class)
			.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
			.add(Projections.property("name"), "name"))
			.setResultTransformer(Transformers.aliasToBean(Member.class));
			List<Member> members = criteria.list();
			return members;
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
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker
	}



	

	/*
	 * public int getIssueId(long memberId) { int id=0;
	 * 
	 * Query query = sessionFactory.getCurrentSession().createQuery(
	 * "from Member where managerId=?"); query.setParameter(0, memberId);
	 * List<Member> members = query.list();
	 * 
	 * for (Member member : members) { id = member.getId();
	 * 
	 * } return id; }
	 */
	
	/**
	 * Method to execute on a fixed schedule, based on a Spring cron expression.
	 * Run every 1 hour when task is started by member.
	 * automatically updates the remaining estimate of each task assigned.
	 * this will be executed asynchronously.
	 * This method enables task scheduling, which automatically updates IssueRemainingestimate and status of issue assigned.
	 * Without it, nothing gets scheduled. 
	 */
	/*Run every 1 hour  */ 
	@Async // this will be executed asynchronously.
	@Scheduled(cron="0 0/60 * * * ?")
	public void scheduleTask() throws ParseException{
	
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from Issue");
		List<Issue> issues = query.list();
		

		/* this is current date*/
		//Date date=new Date(); 
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
		String currentddate = format2.format(calendar.getTime());			//current date
		Date currentddateparsed = format2.parse(currentddate);		//current date after parsing
		
		
		for(Issue issue:issues){
			
			Date issueDuedate=issue.getDueDate();
			
			
		
			long issueRemainingestimate=issueDuedate.getTime()-currentddateparsed.getTime();
			
			//long diffInHour = issueRemainingestimate / (60 * 60 * 1000);
			long days = TimeUnit.MILLISECONDS.toDays(issueRemainingestimate);
			
			int issueRemainingestimateFinal=(int) days;
			
			issueRemainingestimateFinal=issueRemainingestimateFinal+1;
			
			
			
			int currentStatusId=issue.getApplicationIssueStatus().getId();
			
		
			if(issueRemainingestimateFinal>=0 && currentStatusId==2){
				//issue.setIssueRemainingestimate(issueRemainingestimateFinal);
				String oldRemainingEstimate=issue.getRemainingEstimate();
				SimpleDateFormat format4 = new SimpleDateFormat("HH:mm:ss"); 
				Date date1 = format4.parse(oldRemainingEstimate);
				Date date2 = format4.parse("01:00:00");
				
				long remainder = date1.getTime() - date2.getTime();
				String remainder1=DurationFormatUtils.formatDuration(remainder, "HH:mm:ss");
				
				issue.setRemainingEstimate(remainder1);
				
				session.update(issue);
				//sessionFactory.evict(Issue.class,issue.getid());
				session.flush();
				session.clear();

				if(remainder1.equals("00:00:00")){
					int statusId=issue.getApplicationIssueStatus().getId();
					statusId=3;
					ApplicationIssueStatus issueStatus=new ApplicationIssueStatus();
					issueStatus.setId(statusId);
					issue.setApplicationIssueStatus(issueStatus);
					//session1.clear();
					session.update(issue);

					
				}

			}
			
			if(issueRemainingestimateFinal==0){
				int statusId=issue.getApplicationIssueStatus().getId();
				statusId=3;
				ApplicationIssueStatus issueStatus=new ApplicationIssueStatus();
				issueStatus.setId(statusId);
				issue.setApplicationIssueStatus(issueStatus);
				//session1.clear();
				session.update(issue);

				
			}
			}
		transaction.commit();
	}

	/**
	 * Method to update status of assigned issue when it is started.
	 * It returns updated list of Issue on User console.
	 * @param id
	 */
	public List<Issue> startTask(int id,long memberId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Issue where id=?");
		query.setInteger(0, id).list();
		Issue issue2=(Issue)query.uniqueResult();
		int statusId=issue2.getApplicationIssueStatus().getId();
		statusId=2;
		ApplicationIssueStatus issueStatus=new ApplicationIssueStatus();
		issueStatus.setId(statusId);
		issue2.setApplicationIssueStatus(issueStatus);
		session.clear();
		session.update(issue2);

		List<Issue> issues1=showIssuesList(memberId);
		return issues1;

	}

	/**
	 * Method to update status of assigned issue when it is started but not completed within given Duedate.
	 * It returns updated list of Issue on User console. 
	 * @param id
	 */
	public List<Issue> startTaskPending(int id,long memberId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Issue where id=?");
		query.setInteger(0, id).list();
		Issue issue2=(Issue)query.uniqueResult();
		int statusId=issue2.getApplicationIssueStatus().getId();
		statusId=3;
		ApplicationIssueStatus issueStatus=new ApplicationIssueStatus();
		issueStatus.setId(statusId);
		issue2.setApplicationIssueStatus(issueStatus);
		session.clear();
		session.update(issue2);

		List<Issue> issues1=showIssuesList(memberId);
		
		return issues1;

	}

	/**
	 * Method to update status of assigned issue when it is completed and stopped.
	 * It returns updated list of Issue on User console. 
	 * @param id
	 */
	public List<Issue> stopTask(int id,long memberId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Issue where id=?");
		query.setInteger(0, id).list();
		Issue issue2=(Issue)query.uniqueResult();
		int statusId=issue2.getApplicationIssueStatus().getId();
		statusId=4;
		ApplicationIssueStatus issueStatus=new ApplicationIssueStatus();
		issueStatus.setId(statusId);
		issue2.setApplicationIssueStatus(issueStatus);
		session.clear();
		session.update(issue2);

		List<Issue> issues1=showIssuesList(memberId);
		
		return issues1;
	}

	/**
	 * Method to change status of assigned issue when it is paused. In case of pause,
	 * reason should be mentioned and updated.
	 * It returns updated list of Issue on User console. 
	 * @param id
	 * @param reason
	 */
	public List<Issue> pauseTask(int id,String reason,long memberId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Issue where id=?");
		query.setInteger(0, id).list();
		Issue issue2=(Issue)query.uniqueResult();
		int statusId=issue2.getApplicationIssueStatus().getId();
		statusId=5;
		ApplicationIssueStatus issueStatus=new ApplicationIssueStatus();
		issueStatus.setId(statusId);
		issue2.setApplicationIssueStatus(issueStatus);
		session.clear();
		session.update(issue2);

		IssuePauseReason issuePauseReason=new IssuePauseReason();
		issue2.setId(id);
		issuePauseReason.setIssue(issue2);
		issuePauseReason.setReason(reason);
		session.save(issuePauseReason);
		
		List<Issue> issues1=showIssuesList(memberId);
		
		return issues1;
	}
	
	
	
	public void updateIssueTaskProgress(String task,int id){
		
		Session session = sessionFactory.getCurrentSession();

	Issue issue2 = session.get(Issue.class, id);
		issue2.setTaskProgressUpdate(task);

		/*
		Issue issue=new Issue();
		issue.setId(id);
		issue.setTaskProgressUpdate(task);
		session.update(issue);*/
		
		
	}
	
	

}
