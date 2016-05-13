package com.yash.yits.serviceImpl;



import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.dao.IssueDao;
import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.ApplicationEnvironment;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.ApplicationIssueStatus;
import com.yash.yits.domain.ApplicationIssueType;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Project;
import com.yash.yits.form.ApplicationIssuePriorityForm;
import com.yash.yits.form.ApplicationIssueTypeForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.service.IssueService;

@Service
@Transactional
public class IssueServiceImpl implements IssueService{
	


	@Autowired
	private IssueDao issueDao;

	
	public List<IssueForm> getUnassignedIssues() {
		System.out.println("prajvi service");
		List<Issue> issueList=issueDao.getUnassignedIssues();
		List<IssueForm> unassignedIssueList=new ArrayList<IssueForm>();
		
		for (Issue issue: issueList) {
			
			IssueForm issueForm=new IssueForm();
			
			ApplicationIssueTypeForm applicationIssueType=new ApplicationIssueTypeForm();
			applicationIssueType.setType(issue.getApplicationIssueType().getType());
			issueForm.setApplicationIssueType(applicationIssueType);
			
			issueForm.setSummary(issue.getSummary());
			
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
	    int currentDay = localCalendar.get(Calendar.DATE);
	    int currentDayOfWeek = localCalendar.get(Calendar.DAY_OF_WEEK);
	    Date date=new Date();
		
		
		Calendar cal = Calendar.getInstance();
		Date dateBefore=new Date();
		cal.setTime(currentTime);
		if(currentDayOfWeek==2){
			cal.add(Calendar.DATE, -8);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==3){
			cal.add(Calendar.DATE, -9);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==4){
			cal.add(Calendar.DATE, -10);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==5){
			cal.add(Calendar.DATE, -11);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==6){
			cal.add(Calendar.DATE, -12);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==7){
			cal.add(Calendar.DATE, -13);
			dateBefore = cal.getTime();
		}
		else{
			cal.add(Calendar.DATE, -7);
			dateBefore = cal.getTime();
		}
		cal.add(Calendar.DATE, +14);
		Date dateAfter = cal.getTime();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String date1=df.format(dateBefore);
		
		String date2=df.format(dateAfter);
		
		
		
		Timestamp beforeTimestamp=new Timestamp(dateBefore.getTime());
		
		Timestamp afterTimestamp=new Timestamp(dateAfter.getTime());
		
		beforeTimestamp= Timestamp.valueOf(date1);
		afterTimestamp= Timestamp.valueOf(date2);
		
		System.out.println(beforeTimestamp+ "----"+afterTimestamp);
		
		List<Issue> issues = issueDao.getDefaultIssues(beforeTimestamp,afterTimestamp);
		
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		for (Issue issue : issues) {

			
			IssueForm issueForm = new IssueForm();
			
			
		}
		
		System.out.println(issueForms);
		
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
		System.out.println("in service "+projectForms);
		return projectForms;
	}


	public void createIssue(IssueForm issueForm,Long createdBy) {
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
		
		
		issueDao.createIssue(issue,createdBy);
}

	

	public Map<String, Object> getAllSelectFields(ProjectForm projectForm, MemberForm member) {

		Project project = new Project();
		project.setId(projectForm.getId());
		

		return issueDao.getAllSelectFields(project,member);

	}
	
}
