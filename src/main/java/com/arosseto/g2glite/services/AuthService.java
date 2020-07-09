package com.arosseto.g2glite.services;

import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.entities.Client;
import com.arosseto.g2glite.repositories.ClientRepository;
import com.arosseto.g2glite.services.exceptions.ResourceNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder enconder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rdm = new Random();
	
	 public void sendNewPassword(String email) {
		 
		 Client client = clientRepository.findByEmail(email);
		 if (Objects.isNull(client)) {
			 throw new ResourceNotFoundException("Email not found");
		 }
		 
		 String newPass = newPassword();
		 client.setPassword(enconder.encode(newPass));
		 clientRepository.save(client);
		 
		 emailService.sendNewPassword(client, newPass);
	 }

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rdm.nextInt(3);
		if (opt == 0) { //generate digits
			return (char) (rdm.nextInt(10) + 48);
		}
		else if (opt == 1) { // generate capital letter
			return (char) (rdm.nextInt(26) + 65);
		}
		else { //generate lower case
			return (char) (rdm.nextInt(26) + 97);
		}
	}
}
