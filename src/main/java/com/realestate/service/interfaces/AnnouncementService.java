package com.realestate.service.interfaces;

import java.util.List;
import java.util.Optional;
import com.realestate.model.ReAnnouncement;


public interface AnnouncementService {

	List<ReAnnouncement> getAllAnnouncements();

	void save(ReAnnouncement announcement);

	Optional<ReAnnouncement> getAllAnnouncementById(long id);

	void updateAnnouncement(ReAnnouncement announcement);

	void deleteAnnouncement(long id);

	List<ReAnnouncement> getAnnouncementsForResidents(long longValue);

	List<ReAnnouncement> getAnnouncementsForJuristic(long longValue, String role);

	void sendMailForAnnouncement(ReAnnouncement announcement);

	void sendScheduledEmail();

	//Added by Ruchi
	List<ReAnnouncement> getAnnouncementsForJoinedEvents(long longValue);

}
