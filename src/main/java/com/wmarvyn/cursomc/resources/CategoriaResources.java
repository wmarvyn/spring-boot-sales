package com.wmarvyn.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wmarvyn.cursomc.domain.Categoria;
import com.wmarvyn.cursomc.services.CategoriaServie;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

	@Autowired
	private CategoriaServie Service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find( @PathVariable Integer id) {
		
		Categoria obj = Service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
