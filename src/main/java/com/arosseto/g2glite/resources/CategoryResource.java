package com.arosseto.g2glite.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arosseto.g2glite.entities.Category;

@RestController
@RequestMapping(value="/categorias")
public class CategoryResource {

	@GetMapping
	public List<Category> listar() {
		
		Category cat1 = new Category(1, "Informática");
		Category cat2 = new Category(2, "Escritório");
		
		List<Category> lista = new ArrayList<>(Arrays.asList(cat1, cat2));
		
		return lista;
	}
}
