package com.arosseto.g2glite.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arosseto.g2glite.entities.Client;
import com.arosseto.g2glite.repositories.ClientRepository;
import com.arosseto.g2glite.security.UserAuth;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client clt = repo.findByEmail(email);
		
		if (Objects.isNull(clt)) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserAuth(clt.getId(), clt.getEmail(), clt.getPassword(), clt.getProfile());
	}
}
