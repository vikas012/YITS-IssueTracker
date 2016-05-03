/**
 * 
 */
package com.yash.yits.dao.hibernate;


import java.util.List;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.yits.dao.LoginDao;
import com.yash.yits.domain.User;

/**
 * @author somesh.kumar
 *
 */
@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Inserts(Persist) User into database
	 */
	public void insertUser(User domainUser) {
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.save(domainUser);
		transaction.commit();
		session.close();
	}

	/**
	 * check whether User is already in data base or not 
	 */
	public void checkForExistUser(User domainUser) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Criteria criteria=session.createCriteria(User.class);
		
		criteria.add(Restrictions.eqOrIsNull("userName", domainUser.getUserName()));
		
		List<User> FetchUserList=criteria.list();
		
		if(FetchUserList.size()==1){
			
			System.out.println("User is alredy store in database");
			transaction.commit();
			session.close();
		}
		else
		{
			insertUser(domainUser);
			transaction.commit();
			session.close();
			
		}
		
	}


	

}
