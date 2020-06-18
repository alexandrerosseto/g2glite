package com.arosseto.g2glite.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.arosseto.g2glite.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Required field")
	@Length(min=5, max=150, message="Size must be between 5 and 150 characters")
	private String name;
	
	@NotEmpty(message="Required field")
	@Email(message="Invalid email")
	private String email;
	
	@NotEmpty(message="Required field")
	private String clientPersonalIdNumber;
	private Integer clientType;
	
	
	@NotEmpty(message="Required field")
	private String street;
	@NotEmpty(message="Required field")
	private String number;
	private String observation;
	private String address;
	@NotEmpty(message="Required field")
	private String postal;
	
	
	@NotEmpty(message="Required field")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Long cityId;
	
	public ClientNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClientPersonalIdNumber() {
		return clientPersonalIdNumber;
	}

	public void setClientPersonalIdNumber(String clientPersonalIdNumber) {
		this.clientPersonalIdNumber = clientPersonalIdNumber;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
