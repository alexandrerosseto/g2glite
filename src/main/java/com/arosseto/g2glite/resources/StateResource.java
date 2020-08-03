package com.arosseto.g2glite.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arosseto.g2glite.dto.CityDTO;
import com.arosseto.g2glite.dto.StateDTO;
import com.arosseto.g2glite.services.CityService;
import com.arosseto.g2glite.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {

	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> findAll() {
		List<StateDTO> listDto = service.findAll();
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value="/{stateId}/cities")
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Long stateId) {
		List<CityDTO> listDto = cityService.findCityByState(stateId);
		return ResponseEntity.ok().body(listDto);
	}
}
