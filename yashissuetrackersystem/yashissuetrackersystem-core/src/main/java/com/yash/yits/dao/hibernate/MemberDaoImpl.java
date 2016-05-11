package com.yash.yits.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<Member> searchMembers(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Member> deleteMember(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

}
