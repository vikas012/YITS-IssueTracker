package com.yash.yits.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.yits.dao.MemberDao;
import com.yash.yits.domain.Member;
import com.yash.yits.form.*;

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
		
		
		/*Query query1 = session.createQuery("select name ,email,contact from Member");
		
		List<> name =query1.list();
		
		for(String lists : name){
			
			System.out.println(lists);
		}*/
		
		
		/*Criteria criteria = session.createCriteria(Member.class);*/
		 Criteria  criteria  =session.createCriteria(Member.class);
		 
		   criteria.setProjection(Projections.projectionList().add(
		     Projections.property("name")).add(Projections.property("email"))); 

		
		List<Member> allMembers = criteria.list();
		System.out.println(allMembers);
		for (Member member : allMembers) {
			System.out.println(member);
		}
		
		/*List<MemberForm> memberList = new ArrayList<MemberForm>();
		
		System.out.println(allMembers.size());
		for (Member member : allMembers) {
			MemberForm memberForm =new MemberForm();
			
			memberForm.setId(member.getId());
			memberForm.setName(member.getName());	
			memberForm.setEmail(member.getEmail());
			memberForm.setContact(member.getContact());
			
			memberList.add(memberForm);
			
		}*/
		/*List<MemberForm> memberList = new ArrayList<MemberForm>();
		Iterator<Member> iterator = allMembers.iterator();
		
		while (iterator.hasNext()) {
			Member member = (Member) iterator.next();
			
			MemberForm memberForm =new MemberForm();
				memberForm.setId(member.getId());
				memberForm.setName(member.getName());	
				memberForm.setEmail(member.getEmail());
				memberForm.setContact(member.getContact());
				
				memberList.add(memberForm);
		}
		*/
		return   allMembers ;
		
		

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
