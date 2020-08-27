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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arosseto.g2glite.dto.ClientDTO;
import com.arosseto.g2glite.dto.ClientNewDTO;
import com.arosseto.g2glite.entities.Client;
import com.arosseto.g2glite.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/clients")
@Api(value="/clients",  tags="clients")
public class ClientResource {

	@Autowired
	private ClientService service;
	
	@ApiOperation(value = "Find client by ID")
	@GetMapping(value="/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Find client by email")
	@GetMapping(value="/email")
	public ResponseEntity<Client> find(@RequestParam(value="value") String email) {
		Client obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Retrieve all clients")
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> listObj = service.findAll();
		List<ClientDTO> listDto = listObj.stream().map(o -> new ClientDTO(o)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Retrieve all clients per page")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="name") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Client> pageObj = service.findPerPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDto = pageObj.map(obj -> new ClientDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Insert a new client")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO) {
		Client obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Update a client")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDTO, @PathVariable Long id) {
		Client obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Delete a client")
	@PreAuthorize("hasAnyRole('ADMIN')")	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Update the authenticated client's picture")
	@PostMapping(value="/picture")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
