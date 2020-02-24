package com.wmarvyn.sales.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wmarvyn.sales.domain.Categoria;
import com.wmarvyn.sales.services.CategoriaServie;
import com.wmarvyn.sales.services.exception.Objectnotfoundexception;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

	@Autowired
	private CategoriaServie Service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find( @PathVariable Integer id) throws Objectnotfoundexception, ObjectNotFoundException {
		
		Categoria obj = Service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = Service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj,@PathVariable Integer id) throws ObjectNotFoundException {
		obj.setId(id);
		obj = Service.update(obj);
		return  ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id) throws Objectnotfoundexception, ObjectNotFoundException {
		Service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
