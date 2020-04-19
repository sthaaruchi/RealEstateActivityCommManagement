package com.realestate.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.realestate.dao.ReBuildingJPADao;
import com.realestate.dao.ReUserJPADao;
import com.realestate.model.ReAnnouncement;
import com.realestate.model.ReBuilding;
import com.realestate.model.ReUser;
import com.realestate.service.interfaces.AnnouncementService;

/**
 * 
 * @author Su
 * This controller class handles all the form actions for annocuncement page
 *
 */

@Controller
public class AnnouncementController {

	@Autowired
	AnnouncementService announcementService;

	@Autowired
	ReBuildingJPADao buildingDao;
	
	@Autowired 
	ReUserJPADao userDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

	@RequestMapping(path = "/showAnnouncements", method = RequestMethod.GET)
	public String showAnnouncementsPage(ModelMap model,Principal principal) {
		ReUser u = userDao.findByUsername(principal.getName());
		if (u.getRole().equalsIgnoreCase("ROLE_MANAGER") || u.getRole().equalsIgnoreCase("ROLE_TECHNICIAN")
				|| u.getRole().equalsIgnoreCase("ROLE_SECURITY")) {
			return "redirect:/showAnnouncementsForJuristics";
		}
		model.put("announcements", announcementService.getAnnouncementsForResidents(u.getUserId().longValue()));
		return "announcementList";
		//return "redirect:/announcementList";
	}
	
	@RequestMapping(path="/showAnnouncementsForJuristics", method = RequestMethod.GET)
	public String showEventsPageForJuristics(ModelMap model, Principal principal) {
		ReUser u = userDao.findByUsername(principal.getName());
		if (u.getRole().equalsIgnoreCase("ROLE_RESIDENT")) {
			return "error";
		}
		model.put("announcements", announcementService.getAnnouncementsForJuristic(u.getUserId().longValue(),u.getRole().substring(5)));
		model.put("role", u.getRole().toString());
	    return "AnnouncementListForJuristic";
	}

	@RequestMapping(path = "/addAnnouncement", method = RequestMethod.GET)
	public String showNewAnnouncementPage(Model model,Principal request) {
		ReUser user = userDao.findByUsername(request.getName());
		List<String> usergroup = new ArrayList<String>();
		if(user.getRole().equalsIgnoreCase("ROLE_MANAGER")) {
			usergroup.add("ALL");
			usergroup.add("MANAGER");
			usergroup.add("SECURITY");
			usergroup.add("TECHNICIAN");
			usergroup.add("RESIDENT");
		}
		else if(user.getRole().equalsIgnoreCase("ROLE_SECURITY")) {
			usergroup.add("SECURITY");
			usergroup.add("RESIDENT");
		}
		else if(user.getRole().equalsIgnoreCase("ROLE_TECHNICIAN")) {
			usergroup.add("TECHNICIAN");
			usergroup.add("RESIDENT");
		}
		ReAnnouncement announcement = new ReAnnouncement();
		List<ReBuilding> buildings = buildingDao.findAll();
		
		model.addAttribute("announcement", announcement);
		model.addAttribute("buildings", buildings);
		model.addAttribute("usergroups", usergroup);
		return "announcementAdd";
	}

	@RequestMapping(path = "/addAnnouncement", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid ReAnnouncement announcement, BindingResult result,Principal request) {
		if (result.hasErrors()) {
			return "announcementAdd";
		}
		ReUser u = userDao.findByUsername(request.getName());
		announcement.setEditableBy(u.getRole());
		announcement.setPublished(false);
		if(announcement.getUserGroup().equalsIgnoreCase("ALL")) {
			announcement.setUserGroup(null);
		}
		if(announcement.getPublishAnnouncementDate()==null) {
			announcement.setPublished(true);
			announcementService.save(announcement);
			announcementService.sendMailForAnnouncement(announcement);
		}
		else {
			announcementService.save(announcement);
		}
		
		return "redirect:/addAnnouncement";
	}
	
	@RequestMapping(value = "/viewAnnouncement", method = RequestMethod.GET)
    public String viewAnnouncementDetails(@RequestParam long id, ModelMap model, Principal principal) {
        ReAnnouncement announcement = announcementService.getAllAnnouncementById(id).get();
        model.put("announcement", announcement);

        ReUser u = userDao.findByUsername(principal.getName());
        model.put("user", u);
        return "viewAnnouncement";
    }
	
	@RequestMapping(value = "/updateAnnouncement", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, Model model,Principal request) {
		ReUser user = userDao.findByUsername(request.getName());
		List<String> usergroup = new ArrayList<String>();
		if(user.getRole().equalsIgnoreCase("ROLE_MANAGER")) {
			usergroup.add("ALL");
			usergroup.add("MANAGER");
			usergroup.add("SECURITY");
			usergroup.add("TECHNICIAN");
			usergroup.add("RESIDENT");
		}
		else if(user.getRole().equalsIgnoreCase("ROLE_SECURITY")) {
			usergroup.add("SECURITY");
			usergroup.add("RESIDENT");
		}
		else if(user.getRole().equalsIgnoreCase("ROLE_TECHNICIAN")) {
			usergroup.add("TECHNICIAN");
			usergroup.add("RESIDENT");
		}
        ReAnnouncement announcement = announcementService.getAllAnnouncementById(id).get();
        model.addAttribute("announcement", announcement);
        List<ReBuilding> buildings = buildingDao.findAll();
	    
	    model.addAttribute("buildings", buildings);
	    model.addAttribute("usergroups", usergroup);
        return "announcementAdd";
    }
	
	@RequestMapping(value = "/updateAnnouncement", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid ReAnnouncement announcement, BindingResult result,Principal request) {
        if (result.hasErrors()) {
            return "eventAdd";
        }
        announcement.setUserGroup(announcement.getUserGroup().toUpperCase());
		if(announcement.getUserGroup().equalsIgnoreCase("ROLE_ALL")) {
			announcement.setUserGroup(null);
		}
        ReUser u = userDao.findByUsername(request.getName());
		announcement.setEditableBy(u.getRole());
		announcementService.updateAnnouncement(announcement);
        return "redirect:/showAnnouncements";
    }
	
	@RequestMapping(value = "/deleteAnnouncement", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam long id) {
		announcementService.deleteAnnouncement(id);
        return "redirect:/showAnnouncements";
    }
}
