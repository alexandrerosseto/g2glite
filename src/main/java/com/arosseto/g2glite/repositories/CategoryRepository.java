package com.arosseto.g2glite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arosseto.g2glite.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
