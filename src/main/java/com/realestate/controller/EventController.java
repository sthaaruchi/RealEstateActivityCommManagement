package com.realestate.controller;


import java.security.Principal;
import java.text.SimpleDateFormat;
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
import com.realestate.model.ReBuilding;
import com.realestate.model.ReEvent;
import com.realestate.model.ReUser;
import com.realestate.service.interfaces.EventService;

/**
 * 
 * @author shruchi
 * This controller class handles all the form actions for event page
 *
 */

@Controller
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	ReBuildingJPADao buildingDao;
	
	@Autowired 
	ReUserJPADao userDao;
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        // Date - dd/MM/yyyy
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }

	
	@RequestMapping(path="/addEvent", method = RequestMethod.GET)
	public String showNewEventPage(Model model) {
	    ReEvent event = new ReEvent();
	    model.addAttribute("event", event);
	    List<ReBuilding> buildings = buildingDao.findAll();
	    
	    model.addAttribute("allBuildings", buildings);
	    
	     
	    return "eventAdd";
	}
	
	@RequestMapping(path = "/addEvent", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid ReEvent event, BindingResult result) {

        if (result.hasErrors()) {
            return "eventAdd";
        }
        
        eventService.save(event);
        return "redirect:/addEvent";
    }
	
	@RequestMapping(path="/showEvents", method = RequestMethod.GET)
	public String showEventsPage(ModelMap model, Principal principal) {
		ReUser u = userDao.findByUsername(principal.getName());
		if (u.getRole().equalsIgnoreCase("ROLE_MANAGER") || u.getRole().equalsIgnoreCase("ROLE_TECHNICIAN")
				|| u.getRole().equalsIgnoreCase("ROLE_SECURITY")) {
			return "redirect:/showEventsForJuristics";
		}
        model.put("events", eventService.getCurrentEventsForResidents(u.getUserId().longValue()));  
	    return "eventList";
	}
	
	@RequestMapping(path="/showEventsForJuristics", method = RequestMethod.GET)
	public String showEventsPageForJuristics(ModelMap model, Principal principal) {
		ReUser u = userDao.findByUsername(principal.getName());
		if (u.getRole().equalsIgnoreCase("ROLE_RESIDENT")) {
			return "error";
		}
		
		model.put("events", eventService.getCurrentEventsForJuristics(u.getUserId().longValue()));
		model.put("role", u.getRole().toString());     
	    return "EventListForJuristics";
	}
	
	@RequestMapping(value = "/updateEvent", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
        ReEvent event = eventService.getEventById(id).get();
        model.put("event", event);
        List<ReBuilding> buildings = buildingDao.findAll();
	    
	    model.put("allBuildings", buildings);
        return "eventAdd";
    }
	
	@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid ReEvent event, BindingResult result) {

        if (result.hasErrors()) {
            return "eventAdd";
        }
        
        eventService.updateEvent(event);
        return "redirect:/showEvents";
    }
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam long id) {
        eventService.deleteEvent(id);
        return "redirect:/showEvents";
    }
	
	@RequestMapping(value = "/viewEvent", method = RequestMethod.GET)
    public String viewEventDetails(@RequestParam long id, ModelMap model, Principal principal) {
        ReEvent event = eventService.getEventById(id).get();
        model.put("event", event);
        
        ReUser u = userDao.findByUsername(principal.getName());
        model.put("user", u);
        model.put("username", u.getUsername().toString());
        return "ViewEvent";
    }
	
	@RequestMapping(value = "/joinEvent", method = RequestMethod.GET)
    public String joinEvent(@RequestParam long id, ModelMap model, Principal principal) {
        ReUser u = userDao.findByUsername(principal.getName());
        eventService.joinEvent(id, u.getUserId().longValue());
        
        return "redirect:/viewEvent?id="+id;
    }
	
	@RequestMapping(value = "/cancelJoinEvent", method = RequestMethod.GET)
    public String cancelJoinEvent(@RequestParam long id, ModelMap model, Principal principal) {
        ReUser u = userDao.findByUsername(principal.getName());
        eventService.cancelJoinEvent(id, u.getUserId().longValue());
        
        return "redirect:/viewEvent?id="+id;
    }
	
	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
    public String addComment(@RequestParam long eventId, @RequestParam String comment) {
		eventService.addComment(eventId, comment);
		return "redirect:/viewEvent?id="+eventId;
    }
	
	@RequestMapping(value = "/updateComment", method = RequestMethod.GET)
    public String updateComment(@RequestParam long eventId, @RequestParam long commentId, @RequestParam String comment) {
		eventService.updateComment(commentId, comment);
		return "redirect:/viewEvent?id="+eventId;
    }
	
	@RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public String deleteComment(@RequestParam long commentId, @RequestParam long eventId) {
		eventService.deleteComment(commentId);
		return "redirect:/viewEvent?id="+eventId;
    }
	
	
}
