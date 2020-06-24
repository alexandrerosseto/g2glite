package com.arosseto.g2glite.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractMailService {

	private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		log.info("Simulating email sending...");
		log.info(msg.toString());
		log.info("Email sent");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		log.info("Simulating HTML email sending...");
		log.info(msg.toString());
		log.info("Email sent");
	}
}
