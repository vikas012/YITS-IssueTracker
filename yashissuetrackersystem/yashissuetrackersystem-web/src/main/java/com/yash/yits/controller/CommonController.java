package com.yash.yits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping(value="/userWelcome")
	public String userDashboard(){
		
		System.out.println("in common");
		return "UserDashboard";

		
		
		
	}
	
	@RequestMapping(value="/managerWelcome")
	public String managerDashboard(){
		return "ManagerDashboard";
	}
}
