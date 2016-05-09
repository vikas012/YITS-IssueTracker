package com.yash.yits.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.yits.dao.IssueDao;
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

	public List<IssuePriorityForm> getPriorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProjectForm> getProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IssueTypeForm> getIssueType() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IssueAssignedStatusForm> getAssignedStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IssueStatusForm> getIssueStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserForm> getAssigneeList() {
		// TODO Auto-generated method stub
		return null;
	}

	public int createIssue(IssueForm issueForm) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<IssueForm> getDefaultIssues() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IssueForm> search(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}



	
}