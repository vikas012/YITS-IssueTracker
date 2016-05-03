package com.yash.yits.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.IssueAssignedStatus;
import com.yash.yits.domain.IssuePriority;
import com.yash.yits.domain.IssueStatus;
import com.yash.yits.domain.IssueType;
import com.yash.yits.domain.Project;
import com.yash.yits.domain.User;
import com.yash.yits.form.IssueAssignedStatusForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.IssuePriorityForm;
import com.yash.yits.form.IssueStatusForm;
import com.yash.yits.form.IssueTypeForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.form.UserForm;

/**
 * This is a IssueService. This object will communicate between controller and
 * dao. This will be responsible for transferring data by mapping from form to
 * domain object
 */
@Service
@Transactional
public class IssueServiceImpl implements IssueService {

	@Autowired
	IssueDao issueDao;
	

	@Autowired
	DozerBeanMapper mapper;

	public List<IssuePriorityForm> getPriorities() {

		List<IssuePriorityForm> issuePriorityForms = new ArrayList<IssuePriorityForm>();
		for (IssuePriority issuePriority : issueDao.getPriorities()) {

			issuePriorityForms.add(mapper.map(issuePriority, IssuePriorityForm.class));
		}
		return issuePriorityForms;
	}

	public List<ProjectForm> getProjects() {

		
		List<ProjectForm> projectForms = new ArrayList<ProjectForm>();
		for (Project project : issueDao.getProjects()) {

			projectForms.add(mapper.map(project, ProjectForm.class));
		}
		return projectForms;
	}

	public List<IssueTypeForm> getIssueType() {

		List<IssueTypeForm> issueTypeForms = new ArrayList<IssueTypeForm>();
		for (IssueType issueType : issueDao.getIssueType()) {

			issueTypeForms.add(mapper.map(issueType, IssueTypeForm.class));
		}
		return issueTypeForms;
	}

	public List<IssueAssignedStatusForm> getAssignedStatus() {

		
		List<IssueAssignedStatusForm> issueAssignedStatusForms = new ArrayList<IssueAssignedStatusForm>();
		for (IssueAssignedStatus issueAssignedStatus : issueDao.getAssignedStatus()) {

			issueAssignedStatusForms.add(mapper.map(issueAssignedStatus, IssueAssignedStatusForm.class));
		}
		return issueAssignedStatusForms;
	}

	public List<IssueStatusForm> getIssueStatus() {

	
		List<IssueStatusForm> issueStatusForms = new ArrayList<IssueStatusForm>();
		for (IssueStatus issueStatus : issueDao.getIssueStatus()) {

			issueStatusForms.add(mapper.map(issueStatus, IssueStatusForm.class));
		}
		return issueStatusForms;
	}

	public List<UserForm> getAssigneeList() {

		
		List<UserForm> userForm = new ArrayList<UserForm>();
		for (User user : issueDao.getAssigneeList()) {

			userForm.add(mapper.map(user, UserForm.class));
		}
		return userForm;
	}

	/** this method is responsible for creating issue */
	public int createIssue(IssueForm issueForm) {

		Issue issue = mapper.map(issueForm, Issue.class);

		// set Assigned status
		if (issue.getUser().getUserId() != null) {
			issue.getIssueAssignedStatus().setIssueassignmentStatusId(2);
		}
		int issueId = issueDao.createIssue(issue);
		return 1;
	}
	/**
	 *  getDefaultIssues method is responsible for getting default objects 
	 * @return IssueForm
	 *  */
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
			cal.add(Calendar.DATE, -1);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==3){
			cal.add(Calendar.DATE, -2);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==4){
			cal.add(Calendar.DATE, -3);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==5){
			cal.add(Calendar.DATE, -4);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==6){
			cal.add(Calendar.DATE, -5);
			dateBefore = cal.getTime();
		}
		else if(currentDayOfWeek==7){
			cal.add(Calendar.DATE, -6);
			dateBefore = cal.getTime();
		}
		else{
			cal.add(Calendar.DATE, 0);
			dateBefore = cal.getTime();
		}
		cal.add(Calendar.DATE, +7);
		Date dateAfter = cal.getTime();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String date1=df.format(dateBefore);
		
		String date2=df.format(dateAfter);
		
		
		List<Issue> issues = issueDao.getDefaultIssues(date1,date2);

		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		for (Issue issue : issues) {

			Mapper mapper = new DozerBeanMapper();
			IssueForm issueForm = mapper.map(issue, IssueForm.class);
			issueForms.add(issueForm);
		}

		return issueForms;
	}
	
	
	/**
	 *  search method is responsible for searching objects with respect to String 'searchText'
	 * @return IssueForm
	 *  */
	public List<IssueForm> search(String searchText) {

	
		List<Issue> issues = issueDao.search(searchText);
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		for (Issue issue : issues) {

			Mapper mapper = new DozerBeanMapper();
			IssueForm issueForm = mapper.map(issue, IssueForm.class);
			issueForms.add(issueForm);
		}

		return issueForms;
	}
}