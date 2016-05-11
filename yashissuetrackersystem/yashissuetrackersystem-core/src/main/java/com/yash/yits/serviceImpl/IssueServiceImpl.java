package com.yash.yits.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yits.dao.IssueDao;
import com.yash.yits.domain.Issue;
import com.yash.yits.form.IssueForm;
import com.yash.yits.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService{

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
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String date1=df.format(dateBefore);
		
		String date2=df.format(dateAfter);
		
		
		List<Issue> issues = issueDao.getDefaultIssues(date1,date2);
		
		System.out.println("Service: "+issues);
		
		List<IssueForm> issueForms = new ArrayList<IssueForm>();
		/*for (Issue issue : issues) {

			Mapper mapper = new DozerBeanMapper();
			IssueForm issueForm = mapper.map(issue, IssueForm.class);
			issueForms.add(issueForm);
		}*/

		return issueForms;
		
	}

	
	
	
	
}
