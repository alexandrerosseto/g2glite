package com.arosseto.g2glite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.entities.Category;
import com.arosseto.g2glite.entities.Product;
import com.arosseto.g2glite.repositories.CategoryRepository;
import com.arosseto.g2glite.repositories.ProductRepository;
import com.arosseto.g2glite.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Product findById(Long id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Page<Product> search(String name, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);	
	}
}
