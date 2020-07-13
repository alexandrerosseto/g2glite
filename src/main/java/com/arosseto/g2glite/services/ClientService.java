package com.arosseto.g2glite.services;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.arosseto.g2glite.dto.ClientDTO;
import com.arosseto.g2glite.dto.ClientNewDTO;
import com.arosseto.g2glite.entities.Address;
import com.arosseto.g2glite.entities.City;
import com.arosseto.g2glite.entities.Client;
import com.arosseto.g2glite.entities.enums.ClientType;
import com.arosseto.g2glite.entities.enums.Profile;
import com.arosseto.g2glite.repositories.AddressRepository;
import com.arosseto.g2glite.repositories.CityRepository;
import com.arosseto.g2glite.repositories.ClientRepository;
import com.arosseto.g2glite.security.UserAuth;
import com.arosseto.g2glite.services.exceptions.AuthorizationException;
import com.arosseto.g2glite.services.exceptions.DatabaseException;
import com.arosseto.g2glite.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	S3Service s3Service;
	
	public List<Client> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		repo.save(obj);
		addressRepo.saveAll(obj.getAddresses());
		return obj;
	}
	
	public Client findById(Long id) {
		
		UserAuth user = UserService.authenticated();
		
		if (Objects.isNull(user) || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}
		
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
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
			throw new DatabaseException("Cancelled operation : It is not allowed to delete an entity with relationship");
		}
	}
	
	public Page<Client> findPerPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client clt = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getClientPersonalIdNumber(), ClientType.valueOf(objDTO.getClientType()), pe.encode(objDTO.getPassword()));
		
		City city = cityRepo.findById(objDTO.getCityId()).orElse(new City(objDTO.getCityId(), null, null));
			
		Address ad = new Address(null, objDTO.getStreet(), objDTO.getNumber(), objDTO.getObservation(), objDTO.getAddress(), objDTO.getPostal(), clt, city);
		clt.getAddresses().add(ad);
		clt.getPhone().add(objDTO.getPhone1());
		if (objDTO.getPhone2() != null) {
			clt.getPhone().add(objDTO.getPhone2());
		}
		if (objDTO.getPhone3() != null) {
			clt.getPhone().add(objDTO.getPhone3());
		}
		
		return clt;
	}
	
	// This method updates the only two possible fields according to my model and it keeps the other previous data
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile file) {
		return s3Service.uploadFile(file);
	}
}
