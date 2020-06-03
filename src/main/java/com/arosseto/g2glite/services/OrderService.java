package com.arosseto.g2glite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.entities.Order;
import com.arosseto.g2glite.repositories.OrderRepository;
import com.arosseto.g2glite.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	public List<Order> findAll() {
		return repo.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
