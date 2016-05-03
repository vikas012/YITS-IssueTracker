/**
 * 
 */
package com.yash.yits.dao;

import com.yash.yits.domain.User;

/**
 * @author somesh.kumar
 *
 */

public interface LoginDao {
	
	/**
	 * insert user in data base if not already persist 
	 * @param domainUser
	 */
	public void insertUser(User domainUser);
	/**
	 * check User in database 
	 * @param domainUser
	 */
	public void checkForExistUser(User domainUser);


}
