package com.yash.yits.serviceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationEnvironment;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.ApplicationIssueStatus;
import com.yash.yits.domain.ApplicationIssueType;
import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Attachment;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;
import com.yash.yits.domain.Project;
import com.yash.yits.form.ApplicationEnvironmentForm;
import com.yash.yits.form.ApplicationForm;
import com.yash.yits.form.ApplicationIssuePriorityForm;
import com.yash.yits.form.ApplicationIssueStatusForm;
import com.yash.yits.form.ApplicationIssueTypeForm;
import com.yash.yits.form.ApplicationTeamMemberForm;
import com.yash.yits.form.AttachmentForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.service.IssueService;

@Service
@Transactional
public class IssueServiceImpl implements IssueService{

	@Autowired
	private IssueDao issueDao;
	

	public List<IssueForm> showIssuesList(long memberId) {
		List<Issue> issuesList = issueDao.showIssuesList(memberId);
		List<IssueForm> issueFormList = new ArrayList<IssueForm>();
		
		for (Issue issue : issuesList) {
			IssueForm issueForm = new IssueForm();
			
			issueForm.setId(issue.getId());
			issueForm.setSummary(issue.getSummary());
			//issueForm.setDueDate(issue.getDueDate());
			issueForm.setTaskProgressUpdate(issue.getTaskProgressUpdate());

			ApplicationIssueTypeForm applicationIssueTypeForm = new ApplicationIssueTypeForm();
			applicationIssueTypeForm.setType(issue.getApplicationIssueType().getType());
			issueForm.setApplicationIssueType(applicationIssueTypeForm);
			
			ApplicationIssuePriorityForm applicationIssuePriorityForm = new ApplicationIssuePriorityForm();
			applicationIssuePriorityForm.setType(issue.getApplicationIssuePriority().getType());
			issueForm.setApplicationIssuePriority(applicationIssuePriorityForm);
			
			ApplicationTeamMemberForm applicationTeamMemberForm =new ApplicationTeamMemberForm();
            MemberForm member=new MemberForm();
            member.setName(issue.getIssueOwner().getMember().getName());
            applicationTeamMemberForm.setMember(member);
            issueForm.setIssueOwner(applicationTeamMemberForm);
            
            issueForm.setOriginalEstimate(issue.getOriginalEstimate());
            issueForm.setRemainingEstimate(issue.getRemainingEstimate());
            
            ApplicationIssueStatusForm applicationIssueStatusForm = new ApplicationIssueStatusForm();
            applicationIssueStatusForm.setStatus(issue.getApplicationIssueStatus().getStatus());
            issueForm.setApplicationIssueStatus(applicationIssueStatusForm);
            
            issueFormList.add(issueForm);
		}
		
	
		return issueFormList;
	}
	

	public List<IssueForm> getUnassignedIssues() {

		List<Issue> issueList=issueDao.getUnassignedIssues();
		List<IssueForm> unassignedIssueList=new ArrayList<IssueForm>();
		
		for (Issue issue: issueList) {
			
			IssueForm issueForm=new IssueForm();
			
			ApplicationIssueTypeForm applicationIssueType=new ApplicationIssueTypeForm();
			applicationIssueType.setType(issue.getApplicationIssueType().getType());
			issueForm.setApplicationIssueType(applicationIssueType);
			
			issueForm.setSummary(issue.getSummary());
			issueForm.setId(issue.getId());
			
			ApplicationIssuePriorityForm applicationIssuePriority=new ApplicationIssuePriorityForm();
			applicationIssuePriority.setType(issue.getApplicationIssuePriority().getType());
			issueForm.setApplicationIssuePriority(applicationIssuePriority);
			
			ProjectForm projectForm=new ProjectForm();
			projectForm.setName(issue.getProject().getName());
			issueForm.setProject(projectForm);
			
			unassignedIssueList.add(issueForm);
			
		}

		return unassignedIssueList;
	}


	public List<IssueForm> getDefaultIssues() {
		
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	    
	    Date currentTime = localCalendar.getTime();

		Calendar cal = Calendar.getInstance();
		Date dateBefore=new Date();
		cal.setTime(currentTime);
		
			cal.add(Calendar.DATE, -15);
			dateBefore = cal.getTime();
		
		Date dateAfter = new Date();
		
		List<Issue> issues = issueDao.getDefaultIssues(dateBefore,dateAfter);
		
		
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		for (Issue issue : issues) {

			
			IssueForm issueForm = new IssueForm();
			
			issueForm.setId(issue.getId());
			issueForm.setCloseDate(issue.getCloseDate());
			issueForm.setCreatedDateTime(issue.getCreatedDateTime());
			//issueForm.setDueDate(issue.getDueDate());
			issueForm.setSummary(issue.getSummary());

			ProjectForm projectForm=new ProjectForm();
			projectForm.setName(issue.getProject().getName());
			issueForm.setProject(projectForm);
			
			ApplicationIssuePriorityForm applicationIssuePriorityForm=new ApplicationIssuePriorityForm();
			applicationIssuePriorityForm.setType(issue.getApplicationIssuePriority().getType());
			issueForm.setApplicationIssuePriority(applicationIssuePriorityForm);
			
			ApplicationIssueStatusForm applicationIssueStatusForm=new ApplicationIssueStatusForm();
			applicationIssueStatusForm.setStatus(issue.getApplicationIssueStatus().getStatus());
			issueForm.setApplicationIssueStatus(applicationIssueStatusForm);

			ApplicationIssueTypeForm applicationIssueTypeForm=new ApplicationIssueTypeForm();
			applicationIssueTypeForm.setType(issue.getApplicationIssueType().getType());
			issueForm.setApplicationIssueType(applicationIssueTypeForm);
			
			issueForms.add(issueForm);
		}
		
		return issueForms;
	}

	
	public List<ProjectForm> getProjectNames() {
		List<ProjectForm> projectForms= new ArrayList<ProjectForm>();
		List<Project> projects = issueDao.getProjectNames();
		System.out.println(projects);
		Iterator<Project>  iterator = projects.iterator();
		while (iterator.hasNext()) {
			Project project = (Project) iterator.next();
			ProjectForm projectForm = new ProjectForm();
			projectForm.setId(project.getId());
			projectForm.setName(project.getName());
			projectForms.add(projectForm);
		}
		return projectForms;
	}


	public int createIssue(IssueForm issueForm,Long createdBy,Long issueOwnerMemberId) {
		Project project=new Project();
		project.setId(issueForm.getProject().getId());
		
		
		ApplicationEnvironment applicationEnvironment=new ApplicationEnvironment();
		applicationEnvironment.setId(issueForm.getApplicationEnvironment().getId());
		
		ApplicationIssuePriority applicationIssuePriority=new ApplicationIssuePriority();
		applicationIssuePriority.setId(issueForm.getApplicationIssuePriority().getId());
		
		ApplicationIssueType applicationIssueType=new ApplicationIssueType();
		applicationIssueType.setId(issueForm.getApplicationIssueType().getId());
		
		ApplicationIssueStatus applicationIssueStatus=new ApplicationIssueStatus();
		applicationIssueStatus.setId(1);
		
		Issue issue=new Issue();
		issue.setAffectedVersion(issueForm.getAffectedVersion());
		issue.setComponent(issueForm.getComponent());
		issue.setDescription(issueForm.getDescription());
		issue.setSummary(issueForm.getSummary());
		issue.setProject(project);
		issue.setApplicationIssuePriority(applicationIssuePriority);
		issue.setApplicationEnvironment(applicationEnvironment);
		issue.setApplicationIssueType(applicationIssueType);
		issue.setApplicationIssueStatus(applicationIssueStatus);

		issue.setTaskProgressUpdate("Not Started");
		issue.setCreatedDateTime(new Date());
		issue.setIsActive(1);
		
		int issueId=issueDao.createIssue(issue,createdBy,issueOwnerMemberId);
		return issueId;

}
		public Map<String, Object> getAllSelectFields(ProjectForm projectForm, MemberForm member) {

			Project project = new Project();
			project.setId(projectForm.getId());
			
	
			return issueDao.getAllSelectFields(project,member);


		}


	public List<ApplicationForm> getApplicationNames() {
		
		List<ApplicationForm> applicationForms= new ArrayList<ApplicationForm>();
		List<Application> applications = issueDao.getApplicationNames();
		System.out.println("Service getApplication names "+applications);
		Iterator<Application>  iterator = applications.iterator();
		while (iterator.hasNext()) {
			Application application = (Application) iterator.next();
			ApplicationForm applicationForm = new ApplicationForm();
			applicationForm.setId(application.getId());
			applicationForm.setName(application.getName());
			applicationForms.add(applicationForm);
		}
		System.out.println("in service application"+applicationForms);
		return applicationForms;
	}
	
	
	public List<ApplicationIssueTypeForm> getDefaultIssueTypes(int applicationId) {
		
		List<ApplicationIssueType> issueTypes=issueDao.getDefaultIssueTypes(applicationId);
	
		List<ApplicationIssueTypeForm> applicationIssueTypeForms=new ArrayList<ApplicationIssueTypeForm>();
		
		for (ApplicationIssueType applicationIssueType : issueTypes) {
			ApplicationIssueTypeForm applicationIssueTypeForm=new ApplicationIssueTypeForm();
			applicationIssueTypeForm.setId(applicationIssueType.getId());
			applicationIssueTypeForm.setType(applicationIssueType.getType());
			applicationIssueTypeForms.add(applicationIssueTypeForm);
		}
		
	
		
		return applicationIssueTypeForms;
	}

	
	public List<ApplicationIssuePriorityForm> getDefaultIssuePriorities(int applicationId) {
		
		List<ApplicationIssuePriority> issuePriorities=issueDao.getDefaultIssuePriorities(applicationId);
		List<ApplicationIssuePriorityForm> applicationIssuePriorityForms=new ArrayList<ApplicationIssuePriorityForm>();
		for (ApplicationIssuePriority applicationIssuePriority : issuePriorities) {
			ApplicationIssuePriorityForm applicationIssuePriorityForm=new ApplicationIssuePriorityForm();
			applicationIssuePriorityForm.setId(applicationIssuePriority.getId());
			applicationIssuePriorityForm.setType(applicationIssuePriority.getType());
			applicationIssuePriorityForms.add(applicationIssuePriorityForm);
		}
	
		return applicationIssuePriorityForms;
	}
	
	public List<ProjectForm> getDefaultProjectNames(int applicationId) {
		
		List<Project> issuePriorities=issueDao.getDefaultProjectNames(applicationId);
		
		List<ProjectForm> projectForms=new ArrayList<ProjectForm>();
		for (Project project : issuePriorities) {
			ProjectForm projectForm=new ProjectForm();
			projectForm.setId(project.getId());
			projectForm.setName(project.getName());
			projectForms.add(projectForm);
			
		}
	
		return projectForms;
	}


	public List<IssueForm> getFilteredIssue(int issuepriorityId1, int issuetypeId1, int projectnameId) {
		List<Issue> issues=issueDao.getFilteredIssue(issuepriorityId1, issuetypeId1, projectnameId);
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		
			for (Issue issue : issues) {


			
			IssueForm issueForm = new IssueForm();
			
			issueForm.setId(issue.getId());
			issueForm.setCloseDate(issue.getCloseDate());
			issueForm.setCreatedDateTime(issue.getCreatedDateTime());
			//issueForm.setDueDate(issue.getDueDate());
			issueForm.setSummary(issue.getSummary());

			ProjectForm projectForm=new ProjectForm();
			projectForm.setName(issue.getProject().getName());
			issueForm.setProject(projectForm);
			
			ApplicationIssuePriorityForm applicationIssuePriorityForm=new ApplicationIssuePriorityForm();
			applicationIssuePriorityForm.setType(issue.getApplicationIssuePriority().getType());
			issueForm.setApplicationIssuePriority(applicationIssuePriorityForm);
			
			ApplicationIssueStatusForm applicationIssueStatusForm=new ApplicationIssueStatusForm();
			applicationIssueStatusForm.setStatus(issue.getApplicationIssueStatus().getStatus());
			issueForm.setApplicationIssueStatus(applicationIssueStatusForm);

			ApplicationIssueTypeForm applicationIssueTypeForm=new ApplicationIssueTypeForm();
			applicationIssueTypeForm.setType(issue.getApplicationIssueType().getType());
			issueForm.setApplicationIssueType(applicationIssueTypeForm);
			
			issueForms.add(issueForm);
		}
		
		return issueForms;
		
	
	}




	public int managerCreateIssue(IssueForm issueForm, Long createdBy, Long issueOwnerMemberId) throws ParseException {
		Project project=new Project();
		project.setId(issueForm.getProject().getId());
		int issueId=0;
		Application application = new Application();
		
		//application.setId(1);
		
		ApplicationEnvironment applicationEnvironment=new ApplicationEnvironment();
		applicationEnvironment.setId(issueForm.getApplicationEnvironment().getId());
		
		
		
		ApplicationIssuePriority applicationIssuePriority=new ApplicationIssuePriority();
		applicationIssuePriority.setId(issueForm.getApplicationIssuePriority().getId());
		
		
		ApplicationIssueType applicationIssueType=new ApplicationIssueType();
		applicationIssueType.setId(issueForm.getApplicationIssueType().getId());
		
		
		ApplicationIssueStatus applicationIssueStatus=new ApplicationIssueStatus();
		applicationIssueStatus.setId(1);
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
		
		String newDate = issueForm.getDueDate();
		System.out.println(" Date  in service ::"+newDate);
		Date date = formatter.parse(newDate);
		
		Issue issue=new Issue();
		issue.setDueDate(date);
		issue.setAffectedVersion(issueForm.getAffectedVersion());
		issue.setComponent(issueForm.getComponent());
		issue.setDescription(issueForm.getDescription());
		issue.setSummary(issueForm.getSummary());
		issue.setProject(project);
		issue.setApplicationIssuePriority(applicationIssuePriority);
		issue.setApplicationEnvironment(applicationEnvironment);
		issue.setApplicationIssueType(applicationIssueType);
		issue.setApplicationIssueStatus(applicationIssueStatus);
		issue.setOriginalEstimate(issueForm.getOriginalEstimate());
		issue.setTaskProgressUpdate("Not Started");
		issue.setCreatedDateTime(new Date());
		issue.setIsActive(1);
		
		issueId=issueDao.managerCreateIssue(issue, createdBy, issueOwnerMemberId);
		return issueId;
	}



	/**
	 * File upload method--Takes file from controller and passes it to dao layer
	 */
	public String saveFile(Attachment file) {
		file.setCreatedDateTime(new Date());
		file.setLastModifiedDateTime(new Date());
		return issueDao.saveFile(file);
		 
	}

	public List<Issue> getConversationList(long createdBy) {
		List<Issue> chats=issueDao.getConversationList(createdBy);
		return chats;
	}


	public AttachmentForm getAttachment(int id) {
		Attachment attachment=issueDao.getAttachment(id);
		System.out.println(attachment.getName());
		System.out.println(attachment.getLabel());
		AttachmentForm attachmentForm=new AttachmentForm();
			attachmentForm.setFile(attachment.getFile());
			attachmentForm.setLabel(attachment.getLabel());
			attachmentForm.setName(attachmentForm.getName());
		System.out.println(attachmentForm.getName());
		System.out.println(attachmentForm.getLabel());
		return attachmentForm;
	}


	public Map<String, Object> showIssueDetails(int id) {

		Issue issue =issueDao.showIssueDetails(id);
		
		
		IssueForm issueForm = new IssueForm();
		
		issueForm.setAffectedVersion(issue.getAffectedVersion());
		issueForm.setComponent(issue.getComponent());
		issueForm.setDescription(issue.getDescription());
		issueForm.setOriginalEstimate(issue.getOriginalEstimate());
		issueForm.setRemainingEstimate(issue.getRemainingEstimate());
		issueForm.setTaskProgressUpdate(issue.getTaskProgressUpdate());
		issueForm.setCloseDate(issue.getCloseDate());
		issueForm.setCreatedDateTime(issue.getCreatedDateTime());
		
		ApplicationIssueTypeForm applicationIssueTypeForm = new ApplicationIssueTypeForm();
		applicationIssueTypeForm.setType(issue.getApplicationIssueType().getType());
		issueForm.setApplicationIssueType(applicationIssueTypeForm);
		
		ApplicationIssuePriorityForm applicationIssuePriorityForm = new ApplicationIssuePriorityForm();
		applicationIssuePriorityForm.setType(issue.getApplicationIssuePriority().getType());
		issueForm.setApplicationIssuePriority(applicationIssuePriorityForm);
		
		ApplicationTeamMemberForm applicationTeamMemberForm =new ApplicationTeamMemberForm();
		MemberForm member=new MemberForm();
		member.setName(issue.getIssueOwner().getMember().getName());
		applicationTeamMemberForm.setMember(member);
		issueForm.setIssueOwner(applicationTeamMemberForm);
		
		ApplicationTeamMemberForm applicationTeamMemberForm2=new ApplicationTeamMemberForm();
		MemberForm createdBy=new MemberForm();
		createdBy.setName(issue.getCreatedBy().getMember().getName());
		applicationTeamMemberForm2.setMember(createdBy);
		issueForm.setCreatedBy(applicationTeamMemberForm2);
		
		ApplicationTeamMemberForm applicationTeamMemberForm3=new ApplicationTeamMemberForm();
		MemberForm lastModifiedBy = new MemberForm();
		//member.setName(issue.getLastModifiedBy().getMember().getName());
		applicationTeamMemberForm3.setMember(lastModifiedBy);
		issueForm.setLastModifiedBy(applicationTeamMemberForm3);
		
		List<Attachment> listOfIssueForm= issue.getAttachments();
		
		List<AttachmentForm> listOfAttachmentForm = new ArrayList<AttachmentForm>();
			
		for (Attachment attachment : listOfIssueForm) {
			AttachmentForm attachmentForm = new AttachmentForm();
			System.out.println("----"+attachment.getId());
				attachmentForm.setFile( attachment.getFile());
				attachmentForm.setId(attachment.getId());
				attachmentForm.setLabel(attachment.getLabel());
				attachmentForm.setName(attachment.getName());
				listOfAttachmentForm.add(attachmentForm);
			}
			
	
		System.out.println("asjhdjkashdugtja"+listOfAttachmentForm);
		issueForm.setAttachmentForms(listOfAttachmentForm);
		System.out.println(issueForm.getAttachmentForms());
		
		Map<String,Object> issueDetails = new HashMap<String, Object>();
		issueDetails.put("listOfAttachment", listOfAttachmentForm);
		issueDetails.put("issueobject", issueForm);
		
	
		return issueDetails;

	}
	
	public IssueForm fetchIssueDetails(int fetchId) {

		Issue fetchedIssue = issueDao.fetchIssueDetails(fetchId);
		IssueForm fetchedIssueForm = new IssueForm();

		ProjectForm projectForm = new ProjectForm();
		projectForm.setName(fetchedIssue.getProject().getName());
		fetchedIssueForm.setProject(projectForm);

		ApplicationIssuePriorityForm applicationIssuePriorityForm = new ApplicationIssuePriorityForm();
		applicationIssuePriorityForm.setType(fetchedIssue.getApplicationIssuePriority().getType());
		fetchedIssueForm.setApplicationIssuePriority(applicationIssuePriorityForm);

		ApplicationIssueTypeForm applicationIssueTypeForm = new ApplicationIssueTypeForm();
		applicationIssueTypeForm.setType(fetchedIssue.getApplicationIssueType().getType());
		fetchedIssueForm.setApplicationIssueType(applicationIssueTypeForm);

		fetchedIssueForm.setAffectedVersion(fetchedIssue.getAffectedVersion());

		fetchedIssueForm.setComponent(fetchedIssue.getComponent());

		fetchedIssueForm.setSummary(fetchedIssue.getSummary());

		fetchedIssueForm.setDescription(fetchedIssue.getDescription());

		ApplicationEnvironmentForm applicationEnvironmentForm = new ApplicationEnvironmentForm();
		applicationEnvironmentForm.setEnvironment(fetchedIssue.getApplicationEnvironment().getEnvironment());
		fetchedIssueForm.setApplicationEnvironment(applicationEnvironmentForm);

		ApplicationTeamMemberForm applicationTeamMemberForm = new ApplicationTeamMemberForm();
		MemberForm member = new MemberForm();
		member.setName(fetchedIssue.getIssueOwner().getMember().getName());
		applicationTeamMemberForm.setMember(member);
		fetchedIssueForm.setIssueOwner(applicationTeamMemberForm);

		ApplicationTeamMemberForm applicationTeamMemberForm1 = new ApplicationTeamMemberForm();
		MemberForm member1 = new MemberForm();
		member1.setName(fetchedIssue.getCreatedBy().getMember().getName());
		applicationTeamMemberForm1.setMember(member1);
		fetchedIssueForm.setCreatedBy(applicationTeamMemberForm1);

		return fetchedIssueForm;
	}

	public List<MemberForm> getMemberList() {

		List<Member> members = issueDao.getMemberList();
		List<MemberForm> memberForms = new ArrayList<MemberForm>();

		for (Member member : members) {

			MemberForm memberForm = new MemberForm();
			memberForm.setName(member.getName());
			memberForm.setId(member.getId());
			memberForms.add(memberForm);
		}
		return memberForms;
	}

	public void assignIssue(IssueForm issueForm, int fetchId) throws ParseException {

		Issue issue = new Issue();
		issue.setId(issueForm.getId());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");

		String newDate = issueForm.getDueDate();
		Date date = formatter.parse(newDate);
		issue.setDueDate(date);
		
		issue.setOriginalEstimate(issueForm.getOriginalEstimate());
		issue.setRemainingEstimate(issueForm.getRemainingEstimate());

		ApplicationTeamMemberForm applicationTeamMemberForm = issueForm.getAssignedUser();

		Member member = new Member();
		member.setId(issueForm.getAssignedUser().getMember().getId());
		ApplicationTeamMember applicationTeamMember = new ApplicationTeamMember();
		applicationTeamMember.setMember(member);
		issue.setAssignedUser(applicationTeamMember);

		issueDao.assignIssue(issue, fetchId);

	}

	
}
