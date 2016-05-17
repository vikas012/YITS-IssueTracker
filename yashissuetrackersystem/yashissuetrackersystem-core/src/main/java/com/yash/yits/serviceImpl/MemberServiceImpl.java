/**
 * 
 */
package com.yash.yits.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.MemberDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;
import com.yash.yits.domain.MemberType;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.LoginForm;


import com.yash.yits.form.UserForm;
import com.yash.yits.mapper.ApplicationTeamMemberMapper;
import com.yash.yits.mapper.ProjectMapper;
import com.yash.yits.service.MemberService;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.UserForm;
import com.yash.yits.service.MemberService;
import com.yash.yits.util.ContextAware;


/**
 * @author somesh.kumar
 *
 */




@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	
	@Autowired
	JavaMailSender javaMailSender;


	UserForm userForm=new UserForm();
	Member member=new Member();

	

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

	 public boolean addMember(MemberForm memberForm) {
			
		 	member.setMemberId(memberForm.getMemberId());
			member.setName(memberForm.getName());
			member.setEmail(memberForm.getEmail());
			member.setContact(memberForm.getContact());
			member.setManagerId(memberForm.getManagerId());
			member.setManagerName(memberForm.getManagerName());
			member.setManagerEmail(memberForm.getManagerEmail());
			member.setMemberType(memberForm.getMemberType());
			member.setCreatedDateTime(new Date());
			member.setLastModifiedDateTime(new Date());
			boolean result=memberDao.addMember(member);
			
			if(result==true)
			{
				ApplicationContext context=ContextAware.getApplicationContext();
				System.out.println("object of application context"+context);
				JavaMailSenderImpl javamailsender=(JavaMailSenderImpl) context.getBean("mailSender");
				
				System.out.println(javamailsender.getHost()+"  "+javamailsender.getPort());
				SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
				
				simpleMailMessage.setFrom(javamailsender.getUsername());
				simpleMailMessage.setTo(memberForm.getEmail());
				/*simpleMailMessage.setCc(memberForm.getManagerEmail());*/
				String subject="Successful Registration In IssueTracker Application!!!";
				simpleMailMessage.setSubject(subject);
				
				String message="Hi," 
								+ "You have been successfully successfully registered with Yash Issue Tracking System Application with email id."+memberForm.getEmail()+
	
				"Regards,"
				+"Team : Yash Issue Tracking Sytem.";
				
				simpleMailMessage.setText(message);
				
				javaMailSender.send(simpleMailMessage);
			}

			
			
			return result;
		}

	
	public List<MemberForm> showMembers() {

	List<Member> memberList=memberDao.showMembers();
		
	
	List<MemberForm> memberForms = new ArrayList<MemberForm>();
	for (Member member : memberList) {

		MemberForm memberForm = new MemberForm();
		
		memberForm.setMemberId(member.getMemberId());
		memberForm.setName(member.getName());
		memberForm.setEmail(member.getEmail());
		memberForm.setContact(member.getContact());
		memberForm.setIsActive(member.getIsActive());
		
		
		memberForms.add(memberForm);
	}
	
	System.out.println(memberForms);
	
	
	return memberForms;
	
		//return memberList;


	}

	
	
	/**
	 *blockUnblockMember method is used to block or unblock the member
	 * 
	 */
	public void blockUnblockMember(MemberForm memberForm) {

		Member member=new Member();
		member.setMemberId(memberForm.getMemberId());
		
		memberDao.blockUnblockMember(member);
		
	}
	
	/**
	 * This method is responsible for returning list of assigned issues
	 */
	public List<IssueForm> showAssignedIssue() {
		
		List<Issue> issues = memberDao.showAssignedIssue();
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		for(Issue issue:issues) {
			
			IssueForm issueForm = new IssueForm();
			
			//mapping
			issueForm.setAssignedUser(ApplicationTeamMemberMapper.domainForm(issue.getAssignedUser()));
			issueForm.setProject(ProjectMapper.domainForm(issue.getProject()));
			issueForm.setDescription(issue.getDescription());
			issueForm.setSummary(issue.getSummary());
			issueForm.setAffectedVersion(issue.getAffectedVersion());
			issueForm.setComponent(issue.getComponent());
			
			issueForms.add(issueForm);
		}
		return issueForms;
	}

	public List<IssueForm> searchAssignedIssue(String searchText) {
		
		List<Issue> issues = memberDao.searchAssignedIssue(searchText);
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		for(Issue issue:issues) {
			
			IssueForm issueForm = new IssueForm();
			
			//mapping
			issueForm.setAssignedUser(ApplicationTeamMemberMapper.domainForm(issue.getAssignedUser()));
			issueForm.setProject(ProjectMapper.domainForm(issue.getProject()));
			issueForm.setDescription(issue.getDescription());
			issueForm.setSummary(issue.getSummary());
			issueForm.setAffectedVersion(issue.getAffectedVersion());
			issueForm.setComponent(issue.getComponent());
			issueForms.add(issueForm);
		}
		return issueForms;
	}

	public void deleteMember(MemberForm memberForm) {
		
		Member member=new Member();
		member.setMemberId(memberForm.getMemberId());
		memberDao.deleteMember(member);
		
		
	}
	
public List<String> memberType() {
		
		List<MemberType> memberTypes=memberDao.memberType();
		
		List<String> memberTypesList=new ArrayList<String>();
		for (MemberType memberType : memberTypes) {
			 
			String type=memberType.getMemberType();
			memberTypesList.add(type);
			}
		return memberTypesList;
	
			
	}

	public List<MemberForm> searchMembers(String search) {
		
		List<Member> members = memberDao.searchMembers(search);
		List<MemberForm> memberForms = new ArrayList<MemberForm>();
		for (Member member : members) {
			MemberType memberType = new MemberType();
			memberType.setId(member.getMemberType().getId());
			MemberForm memberForm = new MemberForm();
			memberForm.setMemberId(member.getMemberId());
			memberForm.setMemberType(memberType);
			memberForm.setName(member.getName());
			memberForm.setEmail(member.getEmail());
			memberForm.setContact(member.getContact());
			memberForm.setManagerName(member.getManagerName());
			memberForm.setManagerEmail(member.getManagerEmail());
			memberForm.setIsActive(member.getIsActive());
			memberForms.add(memberForm);
		}
		return memberForms;
	}

	public List<MemberForm> searchMemberType(int memberId) {
		List<Member> memberTypeList = memberDao.searchMemberType(memberId);
		List<MemberForm> memberForms = new ArrayList<MemberForm>();
		for (Member member : memberTypeList) {

			MemberType memberType = new MemberType();
			memberType.setId(member.getMemberType().getId());
			MemberForm memberForm = new MemberForm();
			memberForm.setMemberId(member.getMemberId());
			memberForm.setName(member.getName());
			memberForm.setEmail(member.getEmail());
			memberForm.setContact(member.getContact());
			memberForm.setManagerName(member.getManagerName());
			memberForm.setManagerEmail(member.getManagerEmail());
			memberForm.setIsActive(member.getIsActive());
			memberForm.setMemberType(memberType);
			memberForms.add(memberForm);
		}
		return memberForms;
	}

}
