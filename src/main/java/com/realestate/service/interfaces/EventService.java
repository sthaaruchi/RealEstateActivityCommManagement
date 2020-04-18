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

	List<ReEvent> getCurrentEventsForResidents(long id);
	
	Optional<ReEvent> getEventById(long id);
	
	void updateEvent(ReEvent event);
	
	void deleteEvent(long id);

	List<ReEvent> getCurrentEventsForJuristics(long userId);
	
	void joinEvent(long eventId, long userId);

	void cancelJoinEvent(long eventId, long userId);

	void addComment(long eventId, String comment);

	void updateComment(long commentId, String comment);

	void deleteComment(long commentId);

}
