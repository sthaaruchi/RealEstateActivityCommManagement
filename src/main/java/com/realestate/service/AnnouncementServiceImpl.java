package com.realestate.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.realestate.dao.ReAnnouncementJPADao;
import com.realestate.dao.ReUserJPADao;
import com.realestate.model.ReAnnouncement;
import com.realestate.model.ReBuilding;
import com.realestate.model.ReUser;
import com.realestate.service.interfaces.AnnouncementService;
import com.realestate.service.interfaces.EmailService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
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
	public List<ReAnnouncement> getAnnouncementsForManager(long userId) {
		return annnouncementDao.getAnnouncementsForManager(userId);
	}

	@Override
	public List<ReBuilding> getAllResponsibleBuildings(long juristicId) {
		// TODO Auto-generated method stub
		return annnouncementDao.getAllResponsibleBuildings(juristicId);
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
				System.err.println(ex.getMessage());
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

}
