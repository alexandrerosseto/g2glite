package com.arosseto.g2glite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arosseto.g2glite.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
