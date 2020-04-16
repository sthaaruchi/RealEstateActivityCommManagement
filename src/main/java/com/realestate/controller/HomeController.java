package com.realestate.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realestate.dao.ReUserJPADao;
import com.realestate.model.ReUser;

@Controller
public class HomeController {
	@Autowired 
	ReUserJPADao userDao;
	
	@RequestMapping(path = "/home")
	public String userDashboard(Principal request) {
		System.out.println("in here");
		ReUser u = userDao.findByUsername(request.getName());
		if (u.getRole().equalsIgnoreCase("ROLE_RESIDENT")) {
			System.out.println("RESIDENT role");
			return "ResidentHomePage";
//			return "ResidentHomePage.jsp";
			}
//		else
			return "eventAdd";
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