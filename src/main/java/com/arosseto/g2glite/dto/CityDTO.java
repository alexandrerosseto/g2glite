package com.arosseto.g2glite.dto;

import java.io.Serializable;

import com.arosseto.g2glite.entities.City;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public CityDTO() {
	}

	public CityDTO(City obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}