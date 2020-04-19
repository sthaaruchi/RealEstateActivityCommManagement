package com.realestate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.realestate.model.ReUser;

public interface ReUserJPADao extends JpaRepository<ReUser, Long>{
	
	/*
	 * @Query(
	 * value="SELECT * FROM user JOIN subscribing_list ON user.id = subscribing_list.uid WHERE subscribing_list.lid=:lid"
	 * ,nativeQuery=true) List<User> findUsersByList(int lid);
	 */
	
	ReUser findByUsername(String username);

	@Query(value = "SELECT * FROM re_users where user_id in"
			+ "(SELECT r.resident_id FROM re_residents r JOIN announce_for af ON r.live_in_building_id = af.building_id"
			+ " JOIN re_announcements a ON a.announcement_id =  af.announcement_id where a.announcement_id=?) and role='ROLE_RESIDENT'",
			nativeQuery = true)
	List<ReUser> findAllResidentsLiveIn(long announcementId);
	
	@Query(value = "SELECT * FROM re_users where user_id in"
			+ "(SELECT j.juristic_id FROM re_juristics j JOIN responsible_for rf ON j.juristic_id = rf.juristic_id"
			+ " JOIN announce_for af ON af.building_id = rf.building_id"
			+ " JOIN re_announcements a ON a.announcement_id =  af.announcement_id where a.announcement_id=?) and role!='ROLE_RESIDENT'",
			nativeQuery = true)
	List<ReUser> findAllJursiticsResponsibleFor(long announcementId);

}
