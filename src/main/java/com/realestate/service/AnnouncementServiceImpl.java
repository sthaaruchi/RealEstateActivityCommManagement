package com.realestate.service;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.realestate.dao.ReAnnouncementJPADao;
import com.realestate.dao.ReUserJPADao;
import com.realestate.model.ReAnnouncement;
import com.realestate.model.ReUser;
import com.realestate.service.interfaces.AnnouncementService;
import com.realestate.service.interfaces.EmailService;

/**
 * 
 * @author Su
 * Service implementation class for all the services related to announcement
 */

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	private Logger logger = Logger.getLogger(AnnouncementServiceImpl.class);
	
	@Autowired
	ReUserJPADao userDao;

	@Autowired
	ReAnnouncementJPADao annnouncementDao;
	
	@Autowired
	private EmailService emailService;

	@Override
	public List<ReAnnouncement> getAllAnnouncements() {
		return annnouncementDao.findAll();
	}

	@Override
	public void save(ReAnnouncement announcement) {

		annnouncementDao.save(announcement);

	}

	@Override
	public Optional<ReAnnouncement> getAllAnnouncementById(long id) {
		return annnouncementDao.findById(id);
	}

	@Override
	public void updateAnnouncement(ReAnnouncement announcement) {
		annnouncementDao.save(announcement);

	}

	@Override
	public void deleteAnnouncement(long id) {
		Optional<ReAnnouncement> announcement = annnouncementDao.findById(id);
		if (announcement.isPresent()) {
			annnouncementDao.delete(announcement.get());
		}

	}

	@Override
	public List<ReAnnouncement> getAnnouncementsForResidents(long userId) {
		return annnouncementDao.getAnnouncementsForResidents(userId);
	}

	@Override
	public void sendMailForAnnouncement(ReAnnouncement announcement) {
		List<ReUser> residents = userDao.findAllResidentsLiveIn(announcement.getAnnouncementId().longValue());
		List<ReUser> juristics = userDao.findAllJursiticsResponsibleFor(announcement.getAnnouncementId().longValue());
		String made_by=announcement.getEditableBy().substring(5);
		for (ReUser resident : residents) {
			SimpleMailMessage emailMsg = new SimpleMailMessage();
			emailMsg.setTo(resident.getEmail());
			emailMsg.setText(announcement.getDescription()+"\n Annocunced by "+made_by);
			emailMsg.setSubject("New Announcement:" +announcement.getTitle());
			emailMsg.setFrom("reacm-7a71d1@inbox.mailtrap.io");
			try {
				emailService.sendEmail(emailMsg);
			}
			catch(MailException ex) { 
				logger.warn("Email sending failed::" + ex.getMessage());
			}
		}
		for (ReUser jur : juristics) {
			SimpleMailMessage emailMsg = new SimpleMailMessage();
			emailMsg.setTo(jur.getEmail());
			emailMsg.setText(announcement.getDescription()+"\n Annocunced by "+made_by);
			emailMsg.setSubject("New Announcement:" +announcement.getTitle());
			emailMsg.setFrom("reacm-7a71d1@inbox.mailtrap.io");
			try {
				emailService.sendEmail(emailMsg);
			}
			catch(MailException ex) { 
				System.err.println(ex.getMessage());
			}
		}
	}
	
	@Override
	public void sendScheduledEmail() {
	
		List<ReAnnouncement> announcements = annnouncementDao.getAnnouncementsToEmail();
		for (ReAnnouncement announcement : announcements) {
			sendMailForAnnouncement(announcement);
		}	
		
	} 
	
	@Override
	public List<ReAnnouncement> getAnnouncementsForJuristic(long userId, String role) {
		return annnouncementDao.getAnnouncementsForJuristic(userId,role);
	}

	//Added by Ruchi
	@Override
	public List<ReAnnouncement> getAnnouncementsForJoinedEvents(long residentId) {
		return annnouncementDao.getAnnouncementsForJoinedEvents(residentId);
	}

}
