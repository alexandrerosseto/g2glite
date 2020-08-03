package com.arosseto.g2glite.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.dto.CityDTO;
import com.arosseto.g2glite.entities.City;
import com.arosseto.g2glite.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;
	
	public List<City> findByState(Long stateId) {
		return repo.findCity(stateId);
	}
	
	public List<CityDTO> findCityByState(Long stateId) {
		List<City> list = findByState(stateId);
		List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return listDto;
	}
}
