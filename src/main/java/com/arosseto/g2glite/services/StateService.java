package com.arosseto.g2glite.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.dto.StateDTO;
import com.arosseto.g2glite.entities.State;
import com.arosseto.g2glite.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository repo;
	
	public List<State> findAllByOrderByName() {
		return repo.findAllByOrderByName();
	}
	
	public List<StateDTO> findAll() {
		List<State> list = findAllByOrderByName();
		List<StateDTO> listDto = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return listDto;
	}
		
}
