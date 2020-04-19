package com.realestate.service.interfaces;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

	void sendEmail(SimpleMailMessage emailMsg);

}
