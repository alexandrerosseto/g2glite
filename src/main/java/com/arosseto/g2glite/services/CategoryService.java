package com.arosseto.g2glite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.dto.CategoryDTO;
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
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
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
	
	public Page<Category> findPerPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getName());
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
