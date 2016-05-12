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
import com.yash.yits.domain.Member;

/** This class interacts with database and provides the data for all member operations*/



@Repository
public class MemberDaoImpl implements MemberDao {




	


	 @Autowired
	 private SessionFactory sessionFactory;



	public Member addMember(Member member) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println(member);
		Criteria criteria = session.createCriteria(Member.class);
		criteria.add(Restrictions.eqOrIsNull("memberId",member.getMemberId()));
		List<Member> listOfMember=criteria.list();
		if(listOfMember.size()==1){
			
			System.out.println("User Already in database");
		}
		else{
				
			System.out.println("not in database");
			session.save(member);
		}
		return member;
	}

	public List<Member> showMembers() {
		System.out.println("dao members");
		
		Session session = sessionFactory.getCurrentSession();
		
		
		Criteria criteria =session.createCriteria(Member.class)
				.setProjection(Projections.projectionList()		
						.add(Projections.property("memberId"), "memberId")
					      .add(Projections.property("name"), "name")
					      .add(Projections.property("email"), "email")
							.add(Projections.property("contact"), "contact"))
					    .setResultTransformer(Transformers.aliasToBean(Member.class));
		
		
		List<Member> allMembers = criteria.list();
		
		
		System.out.println(allMembers);
		for (Member member : allMembers) {
			System.out.println(member.getEmail());
			System.out.println(member.getMemberId());
		}
		
		
		return   allMembers ;
	}

	public List<Member> searchMembers(String search) {
		System.out.println("in dao");
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("FROM Member where "
				+ "memberId=(Select memberId from Member where memberId LIKE '"+search+"%') OR "
				+ "name=(Select name from Member where name LIKE '"+search+"%') OR "
				+ "email=(Select email from Member where email LIKE '"+search+"%') OR "
				+ "contact=(Select contact from Member where contact LIKE '"+search+"%')");
		
		List<Member> issues=query.list();
		
		return issues;
	}

	public List<Member> deleteMember(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *blockUnblockMember method is used to block or unblock the member
	 * 
	 */
	public List<Member> blockUnblockMember(Member member) {
	
		
		if(member.getIsActive()==0){
			
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Member where memberId=?");
			Member member2=(Member) query.setLong(0, member.getMemberId());
			member2.setIsActive(1);
			session.saveOrUpdate(member2);
			List<Member> members=query.list();
			return members;
		}
		else{
			
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Member where memberId=?");
			Member member2=(Member) query.setLong(0, member.getMemberId());
			member2.setIsActive(0);
			session.saveOrUpdate(member2);
			
			List<Member> members=query.list();
			return members;
		}
	
	}

}
