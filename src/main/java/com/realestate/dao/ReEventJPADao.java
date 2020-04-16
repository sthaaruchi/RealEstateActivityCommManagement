package com.realestate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.realestate.model.ReEvent;

/**
 * 
 * @author shruchi
 * JPA Repository class that has all the methods interacting to events table
 */
public interface ReEventJPADao extends JpaRepository<ReEvent, Long> {
	
	@Query(value = "SELECT * FROM re_event event WHERE event.event_date >= NOW() ", 
			  nativeQuery = true)
	List<ReEvent> getCurrentEvents();

}
