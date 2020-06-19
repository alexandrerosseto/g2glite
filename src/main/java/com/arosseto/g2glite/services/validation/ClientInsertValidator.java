package com.arosseto.g2glite.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.arosseto.g2glite.dto.ClientNewDTO;
import com.arosseto.g2glite.entities.Client;
import com.arosseto.g2glite.entities.enums.ClientType;
import com.arosseto.g2glite.repositories.ClientRepository;
import com.arosseto.g2glite.resources.exceptions.FieldMessage;
import com.arosseto.g2glite.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {
	}
	
	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// Customization
		if (objDto.getClientType().equals(ClientType.Personal.getCode()) && (!BR.isValidCPF(objDto.getClientPersonalIdNumber()))) {
			list.add(new FieldMessage("clientPersonalIdNumber","CPF not valid"));
		}
		
		if (objDto.getClientType().equals(ClientType.Business.getCode()) && (!BR.isValidCNPJ(objDto.getClientPersonalIdNumber()))) {
			list.add(new FieldMessage("clientPersonalIdNumber","CNPJ not valid"));
		}
		
		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
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