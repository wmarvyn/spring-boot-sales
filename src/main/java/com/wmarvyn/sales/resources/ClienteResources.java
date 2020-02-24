package com.wmarvyn.sales.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wmarvyn.sales.domain.Cliente;
import com.wmarvyn.sales.services.ClienteServie;
import com.wmarvyn.sales.services.exception.Objectnotfoundexception;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClienteServie Service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find( @PathVariable Integer id) throws Objectnotfoundexception, ObjectNotFoundException {
		
		Cliente obj = Service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
