package com.realestate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.realestate.model.ReAnnouncement;


/**
 * 
 * @author Su
 * JPA Repository class that has all the methods interacting to re_announcement table
 */

public interface ReAnnouncementJPADao extends JpaRepository<ReAnnouncement, Long> {

	@Query(value = "SELECT * FROM re_announcement where announcement_id in"
			+ "(SELECT a.announcement_id FROM re_announcement a JOIN announce_for af ON a.announcement_id = af.announcement_id"
			+ " JOIN re_resident r ON r.live_in_building_id =  af.building_id where r.resident_id=? and"
			+ " (a.publish_announcement_date<=NOW() or a.publish_announcement_date is null)) and (user_group is null or user_group='RESIDENT')"
			+ " ORDER BY announcement_id DESC", nativeQuery = true)
	List<ReAnnouncement> getAnnouncementsForResidents(long userId);

	/*
	 * @Query(value="SELECT * from re_announcements where announcement_id in " +
	 * "((SELECT a.announcement_id FROM re_announcements  a JOIN announce_for af ON a.announcement_id = af.announcement_id"
	 * + " JOIN responsible_for as rf ON af.building_id = rf.building_id" +
	 * " JOIN re_users u ON rf.juristic_id = u.user_id where u.user_id=?)" +
	 * " or (SELECT a.announcement_id FROM re_announcements a ))ORDER BY announcement_id DESC"
	 * ,nativeQuery=true)
	 */
	/*
	 * @Query(value =
	 * "select * from re_event where event_id in ( select e.event_id from re_event e join event_building eb\r\n"
	 * + "on e.event_id = eb.event_id\r\n" + "join re_building b\r\n" +
	 * "on eb.building_id = b.building_id\r\n" + "join responsible_for rf\r\n" +
	 * "on eb.building_id = rf.building_id\r\n" + "join re_juristics rj\r\n" +
	 * "on rj.juristic_id = rf.juristic_id\r\n" + "join re_users ru\r\n" +
	 * "on ru.user_id = rf.juristic_id\r\n" +
	 * "where e.event_date >= NOW() and ru.user_id = ?)", nativeQuery = true)
	 */
	@Query(value = "SELECT * from re_announcement where announcement_id in "
			+ "(SELECT a.announcement_id FROM re_announcement a JOIN announce_for af ON a.announcement_id = af.announcement_id"
			+ " JOIN responsible_for as rf ON af.building_id = rf.building_id"
			+ " JOIN re_user u ON rf.juristic_id = u.user_id where u.user_id=?) or user_group=? or user_group is null"
			+ " ORDER BY announcement_id DESC", nativeQuery = true)
	List<ReAnnouncement> getAnnouncementsForJuristic(long juristicId,String role);

	/**
	 * 
	 * @author Ruchi
	 * JPA Repository class that has all the methods interacting to re_announcement table
	 */
	
	@Query(value="SELECT * from re_announcements where publish_announcement_date BETWEEN TIMESTAMP 'today' AND TIMESTAMP 'tomorrow';", nativeQuery = true)
	List<ReAnnouncement> getAnnouncementsToEmail();
	
	//Added by Ruchi
	@Query(value="SELECT * from re_announcement ann "
			+ "JOIN event_joined ej ON ann.event_event_id = ej.event_id "
			+ "JOIN re_resident rr ON ej.user_id = rr.resident_id "
			+ "WHERE rr.resident_id = ?", nativeQuery = true)
	List<ReAnnouncement> getAnnouncementsForJoinedEvents(long residentId);
}
