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












import com.yash.yits.form.LdapUser;
import com.yash.yits.service.MemberService;

/**
 * @author somesh.kumar
 *
 */
@Service
public class MemberServiceImpl implements MemberService {

	public InitialDirContext checkUser(LdapUser ldapUser) {
		
		 InitialDirContext intialDirContext=null; 
		    
		 String ldapAdServer="ldap://inidradc01.yash.com/";
		 	
		 Hashtable<String, Object> environmentHashTable = new    Hashtable<String, Object>();
	       
		 environmentHashTable.put(Context.SECURITY_AUTHENTICATION, "simple");
	        
	      if( null!=ldapUser.getLdapName()) {
	        	
	        	environmentHashTable.put(Context.SECURITY_PRINCIPAL, ldapUser.getLdapName());
	        }
	        if(null!=ldapUser.getLdapEmail()) {
	        	environmentHashTable.put(Context.SECURITY_CREDENTIALS, ldapUser.getLdapEmail());
	        }
	        environmentHashTable.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        
	        environmentHashTable.put(Context.PROVIDER_URL, ldapAdServer);
	        
	    
	      try {
	    	  		intialDirContext = new InitialLdapContext(environmentHashTable, null);
	    	  		System.out.println("check intial Dir context"+intialDirContext);
	    	  		return intialDirContext;
			   } 
	      catch (NamingException e)
	      	{
					return intialDirContext;
			}
		
	}

	public void fetchAttributes(InitialDirContext intialDirContext, String name)throws NamingException {
		
		SearchResult searchLdapUser = findAccountByAccountName(intialDirContext, "DC=yash,DC=com", name);

        Attributes attributes=searchLdapUser.getAttributes();
        
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
		
        
		
		
	}

	 public SearchResult findAccountByAccountName(InitialDirContext intialDirContext, String ldapSearchBase, String name) throws NamingException {

	       
			String searchFilter = "sAMAccountName=" + name;

			SearchControls searchControls = new SearchControls();
			
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			NamingEnumeration<SearchResult> results = intialDirContext.search(ldapSearchBase, searchFilter, searchControls);
        
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
