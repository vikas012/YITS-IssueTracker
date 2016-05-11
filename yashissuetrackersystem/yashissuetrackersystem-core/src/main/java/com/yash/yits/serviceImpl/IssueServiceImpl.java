package com.yash.yits.serviceImpl;


<<<<<<< HEAD
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

=======
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
=======
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;
<<<<<<< HEAD
import com.yash.yits.domain.Issue;
import com.yash.yits.form.IssueForm;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;

=======
import com.yash.yits.domain.Project;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker
import com.yash.yits.service.IssueService;

@Service
@Transactional
public class IssueServiceImpl implements IssueService{
	
<<<<<<< HEAD
=======
	@Autowired
	private IssueDao issueDao;
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker
	
	public List<Issue> getUnassignedIssues() {
		List unassignedIssueList=issueDao.getUnassignedIssues();
		return unassignedIssueList;
	}
<<<<<<< HEAD

	@Autowired
	private IssueDao issueDao;
	
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

	
=======
>>>>>>> branch 'devl' of https://github.com/vikas012/YITS-IssueTracker
	
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
