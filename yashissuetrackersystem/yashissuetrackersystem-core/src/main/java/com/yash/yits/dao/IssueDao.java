package com.yash.yits.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yash.yits.domain.Application;
import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationIssuePriority;
import com.yash.yits.domain.ApplicationIssueType;
import com.yash.yits.domain.Attachment;
import com.yash.yits.domain.Issue;
import com.yash.yits.domain.Member;
import com.yash.yits.domain.Project;
import com.yash.yits.form.MemberForm;



public interface IssueDao {

	public List<Issue> getDefaultIssues(Date date3, Date date4);

	public List<Project> getProjectNames();

	public List<Issue> getUnassignedIssues();

	public int managerCreateIssue(Issue issue,Long createdBy,Long issueOwnerMemberId);

	List<Issue> showIssuesList(long memberId);

	public int createIssue(Issue issue,Long createdBy,Long issueOwnerMemberId);

	public List<ApplicationIssueType> getDefaultIssueTypes(int applicationId);

	public Map<String, Object> getAllSelectFields(Project project, MemberForm member);

	public List<Application> getApplicationNames();

	public List<ApplicationIssuePriority> getDefaultIssuePriorities(int applicationId);
	
	public List<Project> getDefaultProjectNames(int applicationId);

	
	public List<Issue> getFilteredIssue(int issuepriorityId1,int issuetypeId1,int projectnameId);
	
	public String saveFile(Attachment file);

	
	public List<Issue> getConversationList(long createdBy);


	public Issue showIssueDetails(int id);

	public Attachment getAttachment(int id);


	public Issue fetchIssueDetails(int fetchId);

	public List<Member> getMemberList();

	public void assignIssue(Issue issue, int fetchId);
	public Issue fetchIssueDetailsConv(int id);

	public List<Member> getMemberListConv();
	
	/**
	 * 
	 * @param id
	 */
	public List<Issue> startTask(int id,long memberId);

	/**
	 * 
	 * @param id
	 */
	public List<Issue> stopTask(int id,long memberId);

	/**
	 * 
	 * @param id
	 */
	public List<Issue> pauseTask(int id,String reason,long memberId);
	
	/**
	 * 
	 * @param id
	 */
	public List<Issue> startTaskPending(int id,long memberId);
	
	
	public void updateIssueTaskProgress(String task,int id);
	
}
