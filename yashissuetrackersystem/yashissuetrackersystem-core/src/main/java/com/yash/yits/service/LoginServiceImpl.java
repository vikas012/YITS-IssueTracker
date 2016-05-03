/**
 * 
 */
package com.yash.yits.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yits.dao.LoginDao;
import com.yash.yits.domain.User;
import com.yash.yits.form.LoginForm;
import com.yash.yits.form.UserForm;

/**
 * @author somesh.kumar
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	User domainUser= new User();
	UserForm userForm= new UserForm();
	
	/**
	 * CheckUser sets the value of SECURITY_AUTHENTICATION,SECURITY_PRINCIPAL,SECURITY_CREDENTIALS,INITIAL_CONTEXT_FACTORY,PROVIDER_URL
	 * required to connect to Ldap server
	 */
	public InitialDirContext checkUser(LoginForm loginForm) {
		
			
		    InitialDirContext ctx=null; 
		    /**
		     *yash  Ldap server URL
		     */
		 	String ldapAdServer="ldap://inidradc01.yash.com/";
		 	
			/**
			 * creating a empty hashtable as Key,Value
			 */
		 	Hashtable<String, Object> environmentHashTable = new    Hashtable<String, Object>();
	       
		 	/**
		 	 * Specifying the Authentication Mechanism("none","simple(Weak Authentication)","sasl_mech")
		 	 */
		 	environmentHashTable.put(Context.SECURITY_AUTHENTICATION, "simple");
	        
	        if( null!=loginForm.getUsername()) {
	        /**
	         * Specifies the name of the user/program doing the authentication 
	         */
	        	environmentHashTable.put(Context.SECURITY_PRINCIPAL, loginForm.getUsername());
	        }
	        if(null!=loginForm.getPassword()) {
	        /**
	         * Specifies the credentials of the user/program doing the authentication 
	         */
	        	environmentHashTable.put(Context.SECURITY_CREDENTIALS, loginForm.getPassword());
	        }
	        /**
	         * Specify LDAP service provider as the initial context
	         */
	        environmentHashTable.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        /**
	         * Ldap server URL
	         */
	       
	        environmentHashTable.put(Context.PROVIDER_URL, ldapAdServer);
	        
	    
	      try {
	    	  		/**
	    	  		 * These controls will be used as the request controls for any implicit LDAP "bind" operation performed by the context or contexts derived from the context. 
	    	  		 * These are called connection request controls. Use getConnectControls() to get a context's connection request controls.
	    	  		 */
				    ctx = new InitialLdapContext(environmentHashTable, null);
	    	  		return ctx;
			   } 
	      catch (NamingException e)
	      	{
					return ctx;
			}
	        		
	        
	}

	public UserForm fetchAttributes(InitialDirContext ctx, String name)throws NamingException {
		
		/** 
		 * return search result with given name
		 */
		SearchResult srLdapUser = findAccountByAccountName(ctx, "DC=yash,DC=com", name);

        /**
         * fetching attributes for a given name from search result
         */
		Attributes attributes=srLdapUser.getAttributes();
        
        if (attributes == null) 
        {
            System.out.println("No attributes");
         } 
        else{
        	/**
        	 * fetching the various attributes like name,manager,mailNickName etc from ldap server
        	 * 
        	 */
        	Object username=attributes.get("name").get();
        	Object managerName=attributes.get("manager").get();
        	Object userId=attributes.get("description").get();
        	Object userAlias=attributes.get("mailNickName").get();
        	Object userEmail=attributes.get("mail").get();
        	Object userOffice=attributes.get("physicalDeliveryOfficeName").get();
        	Object userMobile=attributes.get("mobile").get();
        	Object userTitle=attributes.get("title").get();
        	Object userDepartment=attributes.get("department").get();
        	
        	/**
        	 * fetching the common name of manager from manager details
        	 */
        	String manager=((String)managerName).substring(((String)managerName).indexOf("=")+1,((String)managerName).indexOf(",",3));
        	
        	/**
        	 *making the manager name like john deere to john.geere for searching purpose 
        	 */
        	
        	String[] managerArray=manager.split(" ");
        	String firstNamefirstLetterInCapital=managerArray[0].substring(0, 1).toLowerCase();
        	String lastNameFirstLetterInCapital=managerArray[1].substring(0,1).toLowerCase();
        	
        	String managerEmailName=firstNamefirstLetterInCapital+managerArray[0].substring(1)+"."+lastNameFirstLetterInCapital+managerArray[1].substring(1);
        	
        	/**
        	 * searching with above name(managername) in ldap 
        	 */
        	
        	
        	SearchResult managerLdapUser=findAccountByAccountName(ctx, "DC=yash,DC=com",managerEmailName);
        	
        	Attributes managerAttributes=managerLdapUser.getAttributes();
        	
        	 if (managerAttributes == null) 
             {
                 System.out.println("No attributes");
             } 
        	 
        	 else
        	 {
        		 Object managerId=managerAttributes.get("description").get();
        		 
        		 /**
        		  * seeting the value in domain user to store in database
        		  */
        		 domainUser.setUserName((String)username);
        		 domainUser.setUserManagerName(manager);
        		 domainUser.setUserId(Long.parseLong((String)userId));
        		 domainUser.setUserAliasName((String)userAlias);
        		 domainUser.setUserEmail((String)userEmail);
        		 domainUser.setUserOffice((String)userOffice);
        		 domainUser.setUserMobile((String)userMobile);
        		 domainUser.setUserJobTitle((String)userTitle);
        		 domainUser.setUserDepartment((String)userDepartment);
        		 domainUser.setUserManagerId(Long.parseLong((String)managerId));
             	
        		 /**
        		  * setting the value in user form to return to controller 
        		  */
        		 userForm.setUserName((String)username);
        		 userForm.setUserManagerName(manager);
        		 userForm.setUserId(Long.parseLong((String)userId));
        		 userForm.setUserAliasName((String)userAlias);
        		 userForm.setUserEmail((String)userEmail);
        		 userForm.setUserOffice((String)userOffice);
        		 userForm.setUserMobile((String)userMobile);
        		 userForm.setUserJobTitle((String)userTitle);
        		 userForm.setUserDepartment((String)userDepartment);
        		 userForm.setUserManagerId(Long.parseLong((String)managerId));
             	 /**
             	  * check for user in database ,if not persist ,insert into database
             	  */
             	 loginDao.checkForExistUser(domainUser);
             	 /**
             	  * returning user form 
             	  */
             	 return userForm;
             	 
        	
        }
		
        }
		return userForm;
	}
	  public SearchResult findAccountByAccountName(InitialDirContext intialDirContext, String ldapSearchBase, String name) throws NamingException {

		  	/**
		  	 * sAMAccountName is predefined name for your account in ldap
		  	 */
			String searchFilter = "sAMAccountName=" + name;
			/**
			 * This class encapsulates factors that determine scope of search and what gets returned as a result of the search.
			 */
			SearchControls searchControls = new SearchControls();
			/**
			 * Search the entire subtree rooted at the named object
			 */
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			/**
			 * intialDirContext search method returns a naming enumeration of result
			 */
			NamingEnumeration<SearchResult> results = intialDirContext.search(ldapSearchBase, searchFilter, searchControls);
         
			/**
			 * searching whether with same name two or more than two person exists
			 */
			SearchResult searchResult = null;
			 try {
				 	if(results.hasMoreElements()) 
				 	{
				 		searchResult = (SearchResult) results.nextElement();
				 		if(results.hasMoreElements()) 
				 		{
				 			System.err.println("Matched multiple users for the accountName: " + name);
				 			return null;
				 		}
				 	}
			
			 	}
			 	catch (Exception e)
			 	{
			 		System.out.println(results.toString());
			 		intialDirContext.close();
			 		e.printStackTrace();
			 	}
			return searchResult;
  }

}
