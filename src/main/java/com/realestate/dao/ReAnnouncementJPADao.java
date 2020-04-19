package com.realestate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.realestate.model.ReAnnouncement;
import com.realestate.model.ReBuilding;


public interface ReAnnouncementJPADao extends JpaRepository<ReAnnouncement, Long> {
	
	@Query(value = "SELECT * FROM re_announcements where announcement_id in"
			+ "(SELECT a.announcement_id FROM re_announcements a JOIN announce_for af ON a.announcement_id = af.announcement_id"
			+ " JOIN re_residents r ON r.live_in_building_id =  af.building_id where r.resident_id=? and"
			+ " (a.publish_announcement_date<=NOW() or a.publish_announcement_date is null))"
			+ " ORDER BY announcement_id DESC",
			nativeQuery = true)
	List<ReAnnouncement> getAnnouncementsForResidents(long userId);
	
	@Query(value="SELECT * from re_announcements where announcement_id in "
			+ "(SELECT a.announcement_id FROM re_announcements  a JOIN announce_for af ON a.announcement_id = af.announcement_id"
			+ " JOIN responsible_for as rf ON af.building_id = rf.building_id"
			+ " JOIN re_users u ON rf.juristic_id = u.user_id where u.user_id=?) ORDER BY announcement_id DESC",nativeQuery=true)
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
	List<ReAnnouncement> getAnnouncementsForManager(long userId);
	
	@Query(value="SELECT * FROM re_building b JOIN responsible_for as rf ON b.building_id = rf.building_id"
			+ " WHERE rf.juristic_id=?", nativeQuery= true)
	List<ReBuilding> getAllResponsibleBuildings(long juristicId);
	
	
	@Query(value="SELECT * from re_announcements where publish_announcement_date BETWEEN TIMESTAMP 'today' AND TIMESTAMP 'tomorrow';", nativeQuery = true)
	List<ReAnnouncement> getAnnouncementsToEmail();
}
