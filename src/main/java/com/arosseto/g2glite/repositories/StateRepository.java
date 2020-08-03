package com.arosseto.g2glite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arosseto.g2glite.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

	@Transactional(readOnly=true)
	public List<State> findAllByOrderByName();
}
