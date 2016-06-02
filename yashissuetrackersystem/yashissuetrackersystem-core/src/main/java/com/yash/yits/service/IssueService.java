package com.yash.yits.service;

import java.text.ParseException;
import java.util.List;
import com.yash.yits.domain.Issue;

import java.util.Set;
import java.util.Map;

import com.yash.yits.form.ApplicationForm;
import com.yash.yits.form.ApplicationIssuePriorityForm;
import com.yash.yits.form.ApplicationIssueTypeForm;
import com.yash.yits.form.AttachmentForm;
import com.yash.yits.form.IssueForm;
import com.yash.yits.form.MemberForm;
import com.yash.yits.form.ProjectForm;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.Attachment;
import com.yash.yits.domain.Member;



public interface IssueService {


	public List<IssueForm> showIssuesList(long memberId);


	

	public int managerCreateIssue(IssueForm issueForm,Long createdBy,Long issueOwnerMemberId ,Attachment attachment)throws ParseException ;

	public List<IssueForm> getDefaultIssues();


	public List<IssueForm> getUnassignedIssues();

	
	public List<ProjectForm> getProjectNames();

	public Map<String, Object> getAllSelectFields(ApplicationForm applicationForm, MemberForm member);
	

	public int createIssue(IssueForm issueForm,Long createdBy,Long issueOwnerMemberId,Attachment attachment);
	
	public List<ApplicationForm> getApplicationNames();

	public List<ApplicationIssueTypeForm> getDefaultIssueTypes(int applicationId);

	public List<ApplicationIssuePriorityForm> getDefaultIssuePriorities(int applicationId);
	
	public List<ProjectForm> getDefaultProjectNames(int applicationId);
	
	public List<IssueForm> getFilteredIssue(int issuepriorityId1,int issuetypeId1,int projectnameId);



	public List<Issue> getConversationList(long createdBy);

	public IssueForm fetchIssueDetails(int fetchId);

	public List<MemberForm> getMemberList();

	public void assignIssue(IssueForm issueForm, int fetchId) throws ParseException;
	
	public AttachmentForm getAttachment(int id);



	public Map<String, Object> showIssueDetails(int id);
	
	public IssueForm fetchIssueDetailsConv(int id);



	public List<MemberForm> getMemberListConv();

	/**
	 * startTask received from controller by calling the IssueDaoImpl Object.
	 * @param issuestatusId
	 */
	public List<IssueForm> startTask(int issuestatusId,long memberId);
	/**
	 * 
	 * @param issuestatuslId
	 */
	public List<IssueForm> stopTask(int issuestatusId,long memberId);
	/**
	 * 
	 * @param issuestatuslId
	 */
	public List<IssueForm> pauseTask(int issuestatusId,String reason,long memberId);
	/**
	 * 
	 * @param issuestatusId
	 */
	public List<IssueForm> startTaskPending(int issuestatusId,long memberId);
	
	public List<IssueForm> updateIssueTaskProgress(String task,int id,long memberId);
	
}
