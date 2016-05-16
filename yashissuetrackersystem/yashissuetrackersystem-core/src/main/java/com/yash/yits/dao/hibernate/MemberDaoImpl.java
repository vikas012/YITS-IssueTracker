package com.yash.yits.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.yits.dao.MemberDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;
import com.yash.yits.domain.MemberType;

/** This class interacts with database and provides the data for all member operations*/



@Repository
public class MemberDaoImpl implements MemberDao {

	 @Autowired
	 private SessionFactory sessionFactory;

	 public boolean addMember(Member member) {
			Session session=sessionFactory.getCurrentSession();
			System.out.println(member);
			Criteria criteria = session.createCriteria(Member.class);
			criteria.add(Restrictions.eqOrIsNull("memberId",member.getMemberId()));
			List<Member> listOfMember=criteria.list();
			if(listOfMember.size()==1){
				
				System.out.println("User Already in database");
				return false;
			}
			else{
					
				System.out.println("not in database");
				session.save(member);
				return true;
			}
			
		}

	 /**
		 * This method shows member list.
		 */
	public List<Member> showMembers() {
		System.out.println("dao members");
		
		Session session = sessionFactory.getCurrentSession();
		
		
		
		Criteria criteria =session.createCriteria(Member.class)
				.add(Restrictions.ne("memberType.id", 3))
				.setProjection(Projections.projectionList()		
						.add(Projections.property("memberId"), "memberId")
					      .add(Projections.property("name"), "name")
					      .add(Projections.property("email"), "email")
							.add(Projections.property("contact"), "contact")
							.add(Projections.property("isActive"),"isActive"))
					    .setResultTransformer(Transformers.aliasToBean(Member.class));
		
		
		List<Member> allMembers = criteria.list();
		
		
	/*	System.out.println(allMembers);
		for (Member member : allMembers) {
			System.out.println(member.getEmail());
			System.out.println(member.getMemberId());
		}
		*/
		
		return   allMembers ;
	}

	public List<Member> searchMembers(String search) {
		System.out.println("in dao");
		Session session=sessionFactory.getCurrentSession();
		
		String selectQuery="FROM Member where name LIKE '%"+search+"%' OR email LIKE '"+search+"%' OR managerName LIKE '%"+search+"%' OR memberId LIKE '%"+search+"%'";
		Query query=session.createQuery(selectQuery);
		List<Member> members=query.list();
		for(Member membersList:members){
			
			System.out.println(membersList.getContact());
			
		}

		return members;
	}

	

	/**
	 *blockUnblockMember method is used to block or unblock the member
	 * 
	 */
	public void blockUnblockMember(Member member) {
	
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Member where memberId=?");
		Member member1=(Member) query.setLong(0, member.getMemberId()).uniqueResult();

		if(member1.getIsActive()==0){
			
			member1.setIsActive(1);
			session.saveOrUpdate(member1);
			
		}
		else{
			
			member1.setIsActive(0);
			session.saveOrUpdate(member1);
			
		}	
	}

	/**
	 * This method retrieves list of assigned issues
	 */
	public List<Issue> showAssignedIssue() {
		
		Session session= sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Issue.class);
		criteria.add(Restrictions.isNotNull("assignedUser"));
		criteria.add(Restrictions.isNotNull("closeDate"));
		
		List<Issue> issues = criteria.list();
		
		return issues;
	}

	/**
	 * This method retrieves list of assigned issues on the basic of searched field
	 */
	public List<Issue> searchAssignedIssue(String searchText) {
		
		searchText="%"+searchText+"%";
		Session session= sessionFactory.getCurrentSession();
		
		// Search for projects
		Criteria criteria = session.createCriteria(Issue.class,"issue");
		criteria.createAlias("issue.project", "p")
				.add(Restrictions.like("p.name", searchText));	 
		criteria.add(Restrictions.isNotNull("assignedUser"));
		criteria.add(Restrictions.isNotNull("closeDate"));
		List<Issue> issues = criteria.list();
		
		if(issues.size()!=0)
			return issues;
		
		//search for members
		System.out.println(searchText);
		Criteria criteria1 = session.createCriteria(Issue.class,"issue");
		criteria1.createAlias("issue.assignedUser", "atm")
				.createAlias("atm.member", "m")
				.add(Restrictions.like("m.name", searchText));
		criteria1.add(Restrictions.isNotNull("closeDate"));
		criteria1.add(Restrictions.isNotNull("assignedUser"));
		issues = criteria1.list();
		return issues;
	}

	/**
	 * This method delete member.
	 */
	public void deleteMember(Member member) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Member where memberId=?");
		Member member1=(Member) query.setLong(0, member.getMemberId()).uniqueResult();
		MemberType memberType=new MemberType();
		memberType.setId(3);
		member1.setMemberType(memberType);
		session.saveOrUpdate(member1);
		
		
	}
	public List<MemberType> memberType() {

		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(MemberType.class)
		
		.setProjection(Projections.projectionList()
				.add(Projections.property("memberType"),"memberType").add(Projections.property("id"),"id"))
				.setResultTransformer(Transformers.aliasToBean(MemberType.class));
				List<MemberType> memberTypes=criteria.list();
				 
		
		for(MemberType membersTypeList:memberTypes){
			
			System.out.println(membersTypeList.getMemberType());
			System.out.println(membersTypeList.getId());
		}
		return memberTypes;
		
	}

	public List<Member> searchMemberType(int memberId) {
		
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Member.class)
				
				.setProjection(Projections.projectionList()
				.add(Projections.property("memberId"),"memberId")
				.add(Projections.property("name"),"name")
				.add(Projections.property("email"),"email")
				.add(Projections.property("contact"),"contact")
				.add(Projections.property("managerName"),"managerName")
				.add(Projections.property("managerEmail"),"managerEmail")
				.add(Projections.property("memberType"),"memberType"))
				.add(Restrictions.eq("memberType.id", memberId))
				.setResultTransformer(Transformers.aliasToBean(Member.class));
				List<Member> members=criteria.list();
				for(Member membersList:members){
					
					System.out.println(membersList.getEmail());
					System.out.println("------------------member type id----------"+membersList.getMemberType().getId());
				}
			
		return members;
	}
}
