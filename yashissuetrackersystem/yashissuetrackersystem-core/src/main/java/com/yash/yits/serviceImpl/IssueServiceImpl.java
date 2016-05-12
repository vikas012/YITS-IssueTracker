package com.yash.yits.serviceImpl;



import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;

import com.yash.yits.domain.Issue;
import com.yash.yits.form.IssueForm;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;


import com.yash.yits.domain.Project;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.service.IssueService;

@Service
@Transactional
public class IssueServiceImpl implements IssueService{
	


	@Autowired
	private IssueDao issueDao;

	
	public List<Issue> getUnassignedIssues() {
		List unassignedIssueList=issueDao.getUnassignedIssues();
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
		
		System.out.println(date1+"-----------"+date2);
		
		try {
			Date date3=df.parse(date1);
			Date date4=df.parse(date2);
			
			System.out.println(date3+"------"+date4);
			
		
		
		
		List<Issue> issues = issueDao.getDefaultIssues(date3,date4);
		
		
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		for (Issue issue : issues) {

			
			IssueForm issueForm = new IssueForm();
			
			try {
				BeanUtils.copyProperties(issueForm, issue);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			issueForms.add(issueForm);
		}
		
		System.out.println(issueForms);
		
		return issueForms;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
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
	
}
