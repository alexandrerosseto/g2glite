package com.arosseto.g2glite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arosseto.g2glite.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Transactional(readOnly=true)
	Client findByEmail(String email);
}
