package com.arosseto.g2glite.services;

import org.springframework.mail.SimpleMailMessage;

import com.arosseto.g2glite.entities.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order order);
	
	void sendEmail(SimpleMailMessage msg);
}
