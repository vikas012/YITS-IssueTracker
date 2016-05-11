package com.yash.yits.serviceImpl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;
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
