package com.arosseto.g2glite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arosseto.g2glite.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

	@Transactional(readOnly=true)
	@Query("Select obj FROM City obj WHERE obj.state.id = :stateId ORDER BY obj.name")
	public List<City> findCity(@Param("stateId") Long state_id);
}
