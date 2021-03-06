package com.realestate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.dao.ReBuildingJPADao;
import com.realestate.dao.ReCommentJPADao;
import com.realestate.dao.ReEventJPADao;
import com.realestate.model.ReComment;
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
	
	@Autowired
	ReCommentJPADao commentDao;

	@Override
	public void save(ReEvent event) {
		
		dao.save(event);
		
	}

	@Override
	public List<ReEvent> getCurrentEventsForResidents(long id) {
		return dao.getCurrentEventsForResidents(id);
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

	@Override
	public List<ReEvent> getCurrentEventsForJuristics(long userId) {
		return dao.getCurrentEventsForJuristics(userId);
	}

	@Override
	public void joinEvent(long eventId, long userId) {
		dao.joinEvent(eventId, userId);
		
		
	}

	@Override
	public void cancelJoinEvent(long eventId, long userId) {
		dao.cancelJoinEvent(eventId, userId);
		
	}

	@Override
	public void addComment(long eventId, String comment) {
		ReComment eventComment = new ReComment();
		ReEvent event = dao.findById(eventId).get();
		eventComment.setComment(comment);
		eventComment.setEvent(event);
		commentDao.save(eventComment);
	
	}

	@Override
	public void updateComment(long commentId, String comment) {
		ReComment cmnt = commentDao.getOne(commentId);
		cmnt.setComment(comment);
		commentDao.save(cmnt);
		
	}

	@Override
	public void deleteComment(long commentId) {
		commentDao.deleteComment(commentId);
		
	}


}
