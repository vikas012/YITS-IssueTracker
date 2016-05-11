/**
 * 
 */
package com.yash.yits.serviceImpl;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker.git
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.MemberDao;
import com.yash.yits.domain.Member;
import com.yash.yits.form.LdapUser;
<<<<<<< HEAD
import com.yash.yits.form.MemberForm;
=======
import com.yash.yits.form.LoginForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.UserForm;
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker.git
import com.yash.yits.service.MemberService;
<<<<<<< HEAD

=======
import com.yash.yits.util.ContextAware;
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker.git

/**
 * @author somesh.kumar
 *
 */



@Transactional
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
<<<<<<< HEAD
	
	@Autowired
	private MemberDao memberDao;
=======
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	JavaMailSender javaMailSender;
	
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker.git

	
	UserForm userForm=new UserForm();
	Member member=new Member();

	public InitialDirContext checkUser(LoginForm loginForm) {
		
		 InitialDirContext intialDirContext=null; 
		    
		 String ldapAdServer="ldap://inidradc01.yash.com/";
		 	
		 Hashtable<String, Object> environmentHashTable = new    Hashtable<String, Object>();
	       
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
	    	  		System.out.println("check intial Dir context"+intialDirContext);
	    	  		return intialDirContext;
			   } 
	      catch (NamingException e)
	      	{
					return intialDirContext;
			}
		
	}

	public UserForm fetchAttributes(InitialDirContext intialDirContext, String name)throws NamingException {
		
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
        	Object userEmail=attributes.get("mail").get();
        	Object userMobile=attributes.get("mobile").get();
        	
        	
        	String manager=((String)managerName).substring(((String)managerName).indexOf("=")+1,((String)managerName).indexOf(",",3));
        	
        	String[] managerArray=manager.split(" ");
        	String firstNamefirstLetterInCapital=managerArray[0].substring(0, 1).toLowerCase();
        	String lastNameFirstLetterInCapital=managerArray[1].substring(0,1).toLowerCase();
        	
        	String managerEmailName=firstNamefirstLetterInCapital+managerArray[0].substring(1)+"."+lastNameFirstLetterInCapital+managerArray[1].substring(1);

        	SearchResult managerLdapUser=findAccountByAccountName(intialDirContext, "DC=yash,DC=com",managerEmailName);
        	
        	Attributes managerAttributes=managerLdapUser.getAttributes();
        	
       	 	if (managerAttributes == null) 
            {
                System.out.println("No attributes");
            } 
       	 
       	 	else
       	 	{
       	 		
       	 	 Object managerLdapId=managerAttributes.get("description").get();
       	 	 Object managerLdapName=managerAttributes.get("name").get();
       	 	 Object managerLdapEmail=managerAttributes.get("mail").get();
       	 	 
       	 	 userForm.setUserName(((String)username));
        	 userForm.setUserManagerName(manager);
        	 userForm.setUserId(Long.parseLong(((String)userId)));
        	 userForm.setUserEmail((String)userEmail);
        	 userForm.setUserMobile((String)userMobile);
        	 userForm.setUserManagerId(Long.parseLong(((String)managerLdapId)));
        	 userForm.setUserManagerEmail((String)managerLdapEmail);
        	 
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

	public Member addMember(MemberForm memberForm) {
		
		member.setMemberId(memberForm.getMemberId());
		member.setName(memberForm.getName());
		member.setEmail(memberForm.getEmail());
		member.setContact(memberForm.getContact());
		member.setManagerId(memberForm.getManagerId());
		member.setManagerName(memberForm.getManagerName());
		member.setManagerEmail(memberForm.getManagerEmail());
		memberDao.addMember(member);
		
		
		ApplicationContext context=ContextAware.getApplicationContext();
		System.out.println("object of application context"+context);
		JavaMailSenderImpl javamailsender=(JavaMailSenderImpl) context.getBean("mailSender");
		
		System.out.println(javamailsender.getHost()+"  "+javamailsender.getPort());
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		
		simpleMailMessage.setFrom(javamailsender.getUsername());
		simpleMailMessage.setTo(memberForm.getEmail());
		simpleMailMessage.setCc(memberForm.getManagerEmail());
		String subject="Successful Registration In IssueTracker Application!!!";
		simpleMailMessage.setSubject(subject);
		
		String message="Hello,you have been successful registered with IssueTracker Application with email id "+memberForm.getEmail();
		simpleMailMessage.setText(message);
		
		javaMailSender.send(simpleMailMessage);

		
		
		return member;
	}

	public List<Member> showMembers() {
<<<<<<< HEAD
	List<Member> memberList=memberDao.showMembers();
		
		return memberList;
=======
		
		return null;
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker.git
	}

	public List<Member> searchMembers(String search) {
		
		return null;
	}

	public List<Member> deleteMember(int memberId) {
		
		return null;
	}
	
	

}
