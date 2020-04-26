package com.realestate.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realestate.dao.ReUserJPADao;


/**
 * 
 * @author Su
 * This controller class handles all the form actions for user login page
 *
 */

@Controller
public class HomeController {
	private Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired 
	ReUserJPADao userDao;
	
	@RequestMapping(path = "/home")
	public String userDashboard(Principal request) {
		logger.info("home page -> userDashboard()");
		return "Home";
	}
	
	@RequestMapping(path = "/login")
	public String loginPage() {
		
		return "login.jsp";
	}
	
	@RequestMapping(path= "/logout-success")
	public String logoutPage() {
		logger.info("successfully logged out -> logoutPage()");
		return "logout";
	}

	@RequestMapping(path="/chatroom")
	public String chatRoomHome() {
		return "ChatHomePage";
	}
}