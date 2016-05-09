/**
 * 
 */
package com.yash.yits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author somesh.kumar
 *
 */
@Controller
public class LoginController {
	
	
	/**
	 * @return login page
	 */
	
	@RequestMapping(value="/loginForm")
	public String getLoginForm()
	{
		
		return "/Login.html";
	}
	
}
