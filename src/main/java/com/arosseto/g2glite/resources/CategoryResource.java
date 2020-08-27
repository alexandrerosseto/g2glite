package com.arosseto.g2glite.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arosseto.g2glite.dto.CategoryDTO;
import com.arosseto.g2glite.entities.Category;
import com.arosseto.g2glite.services.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/categories")
@Api(value="/categories",  tags="categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@ApiOperation(value = "Retrieve all categories", response = Category.class, responseContainer = "List")
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> listObj = service.findAll();
		List<CategoryDTO> listDto = listObj.stream().map(o -> new CategoryDTO(o)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Retrieve all categories per page", response = Category.class, responseContainer = "List")
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Category> pageObj = service.findPerPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDto = pageObj.map(obj -> new CategoryDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Find by ID", response = Category.class)
	@GetMapping(value="/{id}")
	public ResponseEntity<Category> findById(
			@ApiParam(value = "Category ID", required = true, example = "1") @PathVariable Long id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Insert a new category", response = Category.class)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDTO) {
		Category obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Update a category", response = Category.class)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDTO, @PathVariable Long id) {
		Category obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Remove a category", response = Category.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Impossible to delete a category that has associated products"),
			@ApiResponse(code = 404, message = "ID not found") })
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
