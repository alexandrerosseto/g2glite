package com.arosseto.g2glite.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractMailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		log.info("Email sending...");
		mailSender.send(msg);
		log.info("Email sent");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		log.info("HTML email sending...");
		javaMailSender.send(msg);
		log.info("Email sent");
	}
}
