package com.yash.yits.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yash.yits.form.IssueForm;
import com.yash.yits.service.IssueService;

/**This is a MemberController. This object will communicate with front-end 
 * This will be responsible for managing flow related to members.*/

@Controller
public class MemberController {


@RequestMapping(value="/showYashForm")
public String getCreateIssueForm(){
	
	return "Yash-NonYash";
}
	
	
	
}