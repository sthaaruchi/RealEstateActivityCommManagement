package com.realestate.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.dao.ReBuildingJPADao;
import com.realestate.dao.ReEventJPADao;
import com.realestate.model.ReBuilding;
import com.realestate.model.ReEvent;
import com.realestate.service.interfaces.EventService;

/**
 * 
 * @author shruchi
 * Service implementation class for all the services related to events
 */

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	ReEventJPADao dao;
	@Autowired
	ReBuildingJPADao buildingdao;

	@Override
	public void save(ReEvent event) {
		
		ReBuilding building = new ReBuilding();
		building.setName("B1");
		buildingdao.save(building);
		
		Set<ReBuilding> build = new HashSet<>();
		build.add(buildingdao.getOne(1L));
		
		event.setBuildings(build);
		
		dao.save(event);
		
	}

	@Override
	public List<ReEvent> getCurrentEvents() {
		return dao.getCurrentEvents();
	}

	@Override
	public Optional<ReEvent> getEventById(long id) {
		return dao.findById(id);
	}

	@Override
	public void updateEvent(ReEvent event) {
		dao.save(event);
		
	}
	
	@Override
    public void deleteEvent(long id) {
        Optional < ReEvent > event = dao.findById(id);
        if (event.isPresent()) {
            dao.delete(event.get());
        }
    }


}
