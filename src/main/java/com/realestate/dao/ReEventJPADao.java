package com.realestate.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.realestate.model.ReEvent;

/**
 * 
 * @author shruchi
 * JPA Repository class that has all the methods interacting to events table
 */
public interface ReEventJPADao extends JpaRepository<ReEvent, Long> {
	
	@Query(value = "SELECT * FROM re_event e join event_building eb ON e.event_id = eb.event_id\r\n" + 
			"JOIN re_building rb\r\n" + 
			"ON eb.building_id = rb.building_id\r\n" + 
			"JOIN re_residents rr\r\n" + 
			"ON rr.live_in_building_id = eb.building_id\r\n" + 
			"WHERE e.event_date >= NOW() and rr.resident_id = ? ", 
			  nativeQuery = true)
	List<ReEvent> getCurrentEventsForResidents(long id);

	@Query(value = "select * from re_event where event_id in ( select e.event_id from re_event e join event_building eb\r\n" + 
			"on e.event_id = eb.event_id\r\n" + 
			"join re_building b\r\n" + 
			"on eb.building_id = b.building_id\r\n" + 
			"join responsible_for rf\r\n" + 
			"on eb.building_id = rf.building_id\r\n" + 
			"join re_juristics rj\r\n" + 
			"on rj.juristic_id = rf.juristic_id\r\n" + 
			"join re_users ru\r\n" + 
			"on ru.user_id = rf.juristic_id\r\n" + 
			"where e.event_date >= NOW() and ru.user_id = ?)", nativeQuery = true)
	List<ReEvent> getCurrentEventsForJuristics(long userId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT into event_joined values (?, ?)", nativeQuery = true)
	void joinEvent(long eventId, long userId);

}
