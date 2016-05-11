/**
 * 
 */
package com.yash.yits.serviceImpl;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.stereotype.Service;

import com.yash.yits.form.LoginForm;
import com.yash.yits.form.UserForm;
import com.yash.yits.service.LoginService;

/**
 * @author somesh.kumar
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	UserForm userForm= new UserForm(); 

	public InitialDirContext checkUser(LoginForm loginForm) {
		
		    InitialDirContext intialDirContext=null; 
		    
		 	String ldapAdServer="ldap://inidradc01.yash.com/";
		 	
			Hashtable<String, Object> environmentHashTable = new Hashtable<String, Object>();
	       
			environmentHashTable.put(Context.SECURITY_AUTHENTICATION, "simple");
	        
	        if( null!=loginForm.getUsername()) {
	        	
	        	environmentHashTable.put(Context.SECURITY_PRINCIPAL, loginForm.getUsername());
	        }
	        if(null!=loginForm.getPassword()) {
	        	environmentHashTable.put(Context.SECURITY_CREDENTIALS, loginForm.getPassword());
	        }
	        environmentHashTable.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        
	        environmentHashTable.put(Context.PROVIDER_URL, ldapAdServer);
	        
	    
	      try {
	    	  		intialDirContext = new InitialLdapContext(environmentHashTable, null);
	    	  		return intialDirContext;
			   } 
	      catch (NamingException e)
	      	{
					return intialDirContext;
			}
	}

	
	 public  UserForm fetchAttributes(InitialDirContext ctx,String name) throws NamingException
	    {
	    	
	    	SearchResult srLdapUser = findAccountByAccountName(ctx, "DC=yash,DC=com", name);

	        Attributes attributes=srLdapUser.getAttributes();
	        
	        if (attributes == null) 
	        {
	            System.out.println("No attributes");
	         } 
	        else 
	        {
	        
	        	Object username=attributes.get("name").get();
	        	Object managerName=attributes.get("manager").get();
	        	Object userId=attributes.get("description").get();
	        	Object userAlias=attributes.get("mailNickName").get();
	        	Object userEmail=attributes.get("mail").get();
	        	Object userOffice=attributes.get("physicalDeliveryOfficeName").get();
	        	Object userMobile=attributes.get("mobile").get();
	        	Object userTitle=attributes.get("title").get();
	        	Object userDepartment=attributes.get("department").get();
	        	
	        	String manager=((String)managerName).substring(((String)managerName).indexOf("=")+1,((String)managerName).indexOf(",",3));
	        	
	        	String[] managerArray=manager.split(" ");
	        	String firstNamefirstLetterInCapital=managerArray[0].substring(0, 1).toLowerCase();
	        	String lastNameFirstLetterInCapital=managerArray[1].substring(0,1).toLowerCase();
	        	
	        	String managerEmailName=firstNamefirstLetterInCapital+managerArray[0].substring(1)+"."+lastNameFirstLetterInCapital+managerArray[1].substring(1);
	        	
	        	SearchResult managerLdapUser=findAccountByAccountName(ctx, "DC=yash,DC=com",managerEmailName);
	        	
	        	Attributes managerAttributes=managerLdapUser.getAttributes();
	        	
	        	 if (managerAttributes == null) 
	             {
	                 System.out.println("No attributes");
	             } 
	        	 
	        	 else
	        	 {
	        		 Object managerId=managerAttributes.get("description").get();
	             	
	             	 userForm.setUserName(((String)username));
	             	 userForm.setUserManagerName(manager);
	             	 userForm.setUserId(Long.parseLong(((String)userId)));
	             	 userForm.setUserAliasName((String)userAlias);
	             	 userForm.setUserEmail((String)userEmail);
	             	 userForm.setUserOffice((String)userOffice);
	             	 userForm.setUserMobile((String)userMobile);
	             	 userForm.setUserJobTitle((String)userTitle);
	             	 userForm.setUserDepartment((String)userDepartment);
	             	 userForm.setUserManagerId(Long.parseLong(((String)managerId)));
	             	 return userForm;
	             	 
	             	 
	        	 }
	        	 
	        	
	        	
	        	
	        	
	        	/*try
	            {
	            
	            	
	            	
	            	for (NamingEnumeration ae = attributes.getAll(); ae.hasMore();) 
		              {
		            	  
		            	  Attribute attribute = (Attribute) ae.next();
		            	  attributes.get("mobile");
		            	  System.out.println("attribute: " +attribute.getID());
		
		                for (NamingEnumeration e = attribute.getAll(); e.hasMore(); 
		                System.out.println("value: " + e.next()));
		              }
		              
	              
	            }
	            catch (NamingException e) 
	            {
	              e.printStackTrace();
	            }*/
	          }
			return userForm;
	        
	    	
	    	
	    }
	    
	    
	    
		   public SearchResult findAccountByAccountName(InitialDirContext ctx, String ldapSearchBase, String name) throws NamingException {

		       
					String searchFilter = "sAMAccountName=" + name;

					SearchControls searchControls = new SearchControls();
					
					searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

					NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);
		           
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
					 		ctx.close();
					 		e.printStackTrace();
					 	}
					return searchResult;
		    }

}
