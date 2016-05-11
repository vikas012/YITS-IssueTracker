package com.yash.yits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is a IssueController. This object will communicate with front-end.
 * This will be responsible for managing flow related to issues.
 */

@Controller
public class IssueController {
	
	
	@RequestMapping(value="/showCreateIssueForm")
	public String getCreateIssueForm()
	{
		return "redirect:/static/UserCreateIssueForm.html";
	}
}
