package com.arosseto.g2glite.services;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.arosseto.g2glite.entities.InvoicePayment;

@Service
public class InvoiceService {

	public void fillInvoicePayment(InvoicePayment ip, Instant orderInstant) {
		/**
		 * This code must be replaced by third-party invoice Webservice
		 */
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(orderInstant));
		cal.add(Calendar.DAY_OF_MONTH, 7);
		ip.setPaymentDueDate(cal.getTime());
	}
}
