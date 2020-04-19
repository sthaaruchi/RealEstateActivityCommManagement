package com.realestate.service.interfaces;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.realestate.model.ReAnnouncement;
import com.realestate.model.ReBuilding;

public interface AnnouncementService {

	List<ReAnnouncement> getAllAnnouncements();

	void save(ReAnnouncement announcement);

	Optional<ReAnnouncement> getAllAnnouncementById(long id);

	void updateAnnouncement(ReAnnouncement announcement);

	void deleteAnnouncement(long id);

	List<ReAnnouncement> getAnnouncementsForResidents(long longValue);

	List<ReAnnouncement> getAnnouncementsForManager(long longValue);

	List<ReBuilding> getAllResponsibleBuildings(long longValue);

	void sendMailForAnnouncement(ReAnnouncement announcement);

}
