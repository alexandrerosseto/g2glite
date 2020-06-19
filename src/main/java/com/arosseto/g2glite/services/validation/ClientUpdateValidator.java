package com.arosseto.g2glite.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.arosseto.g2glite.dto.ClientDTO;
import com.arosseto.g2glite.entities.Client;
import com.arosseto.g2glite.repositories.ClientRepository;
import com.arosseto.g2glite.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}
	
	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		// Customization
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email registered already"));
		}
		
		// Based on Spring framework
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}