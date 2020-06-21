package com.arosseto.g2glite.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arosseto.g2glite.entities.Category;
import com.arosseto.g2glite.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Transactional(readOnly=true)
	// The annotation @Query could be used instead of the JPA name standard using @Param("[fields name"]) on the method. The JPQL below is correct for this purposed
	//@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
}
