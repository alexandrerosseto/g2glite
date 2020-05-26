package com.arosseto.g2glite.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.arosseto.g2glite.entities.enums.PaymentStatus;

@Entity
@Table(name = "tb_invoicepayment")
public class InvoicePayment extends Payment  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Date paymentDueDate;
	private Date paymentDate;
	
	public InvoicePayment() {
	}

	public InvoicePayment(Long id, Instant moment, Order order, PaymentStatus paymentStatus, Date paymentDueDate, Date paymentDate) {
		super(id, moment, order, paymentStatus);
		this.paymentDate = paymentDate;
		this.paymentDueDate = paymentDueDate;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}
