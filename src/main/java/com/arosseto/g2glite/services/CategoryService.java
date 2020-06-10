package com.arosseto.g2glite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.entities.Category;
import com.arosseto.g2glite.repositories.CategoryRepository;
import com.arosseto.g2glite.services.exceptions.DatabaseException;
import com.arosseto.g2glite.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public List<Category> findAll() {
		return repo.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		findById(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			repo.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
