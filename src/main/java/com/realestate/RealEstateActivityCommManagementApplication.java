package com.realestate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.realestate.service.interfaces.AnnouncementService;

@SpringBootApplication
@EnableScheduling
public class RealEstateActivityCommManagementApplication {
	
	@Autowired
	AnnouncementService announcementService;
	
	private Logger logger = Logger.getLogger(RealEstateActivityCommManagementApplication.class);
	
	static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(RealEstateActivityCommManagementApplication.class, args);
	}
	
	@Scheduled(cron = "0 0 12 * * ?")
	//@Scheduled(cron = "* */5 * * * *")
	public void run() {

		AnnouncementService announcementService = applicationContext.getBean(AnnouncementService.class);
	    logger.info("Cron job started...Sending emails...");
	    
	    announcementService.sendScheduledEmail();
	    
	    logger.info("Emails Sent");
	}

}
