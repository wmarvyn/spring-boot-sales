package com.wmarvyn.sales.resources;


import com.wmarvyn.sales.domain.Pedido;
import com.wmarvyn.sales.services.PedidoServie;
import com.wmarvyn.sales.services.exception.Objectnotfoundexception;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResources {

	@Autowired
	private PedidoServie Service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find( @PathVariable Integer id) throws Objectnotfoundexception, ObjectNotFoundException {
		
		Pedido obj = Service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
