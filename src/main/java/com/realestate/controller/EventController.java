package com.realestate.controller;


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
import com.realestate.model.ReBuilding;
import com.realestate.model.ReEvent;
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
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        // Date - dd/MM/yyyy
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
	public String showEventsPage(ModelMap model) {
        model.put("events", eventService.getCurrentEvents());
	     
	    return "eventList";
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
	
	
}
