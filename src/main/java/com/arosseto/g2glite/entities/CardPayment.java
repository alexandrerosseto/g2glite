package com.arosseto.g2glite.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.arosseto.g2glite.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "tb_cardpayment")
@JsonTypeName("CardPayment")
public class CardPayment extends Payment implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer installments;
	
	public CardPayment() {
	}

	public CardPayment(Long id, Instant moment, Order order, PaymentStatus paymentStatus, Integer installments) {
		super(id, moment, order, paymentStatus);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
}
