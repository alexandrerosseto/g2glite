package com.arosseto.g2glite.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.arosseto.g2glite.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tb_invoicepayment")
@JsonTypeName("InvoicePayment")
public class InvoicePayment extends Payment  implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="GMT")
	private Date paymentDueDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="GMT")
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
