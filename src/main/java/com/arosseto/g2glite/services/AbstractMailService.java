package com.arosseto.g2glite.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.arosseto.g2glite.entities.Order;

public abstract class AbstractMailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Order obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order Confirmed! Order number: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
