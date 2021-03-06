package com.wmarvyn.sales.resources;


import com.wmarvyn.sales.domain.Pedido;
import com.wmarvyn.sales.services.PedidoServie;
import com.wmarvyn.sales.services.exception.Objectnotfoundexception;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
@Api(value="API REST Periodos")
@CrossOrigin(origins = "*")
public class PedidoResources {


	@Autowired
	private PedidoServie Service;

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get order")
	public ResponseEntity<Pedido> find( @PathVariable Integer id) throws Objectnotfoundexception, ObjectNotFoundException {
		
		Pedido obj = Service.find(id);
		return ResponseEntity.ok().body(obj);
	}


	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Create order")
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) throws ObjectNotFoundException {
		obj = Service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}


}
