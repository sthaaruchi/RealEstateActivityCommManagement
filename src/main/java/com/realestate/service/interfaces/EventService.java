package com.realestate.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.realestate.model.ReEvent;

/**
 * 
 * @author shruchi
 * Interface class defining all the methods for services related to events
 */

public interface EventService {
	
	void save(ReEvent event);

	List<ReEvent> getCurrentEvents();
	
	Optional<ReEvent> getEventById(long id);
	
	void updateEvent(ReEvent event);
	
	void deleteEvent(long id);

}
