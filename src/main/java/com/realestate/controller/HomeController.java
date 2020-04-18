package com.realestate.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realestate.dao.ReUserJPADao;

@Controller
public class HomeController {
	@Autowired 
	ReUserJPADao userDao;
	
	@RequestMapping(path = "/home")
	public String userDashboard(Principal request) {
		return "Home";
	}
	
	@RequestMapping(path = "/login")
	public String loginPage() {
		
		return "login.jsp";
	}
	
	@RequestMapping(path= "/logout-success")
	public String logoutPage() {
		return "logout.jsp";
	}
}