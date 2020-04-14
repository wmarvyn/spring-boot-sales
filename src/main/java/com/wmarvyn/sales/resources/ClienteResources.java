package com.wmarvyn.sales.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.wmarvyn.sales.domain.Categoria;
import com.wmarvyn.sales.dto.CategoriaDTO;
import com.wmarvyn.sales.dto.ClienteNewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wmarvyn.sales.domain.Cliente;
import com.wmarvyn.sales.dto.ClienteDTO;
import com.wmarvyn.sales.services.ClienteServie;
import com.wmarvyn.sales.services.exception.Objectnotfoundexception;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) throws IllegalAccessException {
		Cliente obj = Service.fromDTO(objDto);
		obj = Service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO,@PathVariable Integer id) throws ObjectNotFoundException {
		Cliente obj = Service.fromDTO(objDTO);
		obj.setId(id);
		obj = Service.update(obj);
		return  ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id) throws Objectnotfoundexception, ObjectNotFoundException {
		Service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> find() throws Objectnotfoundexception, ObjectNotFoundException {
		List<Cliente> list = Service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> find(
			@RequestParam (value = "page", defaultValue = "0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue = "24")Integer lineInteger,
			@RequestParam (value = "oderBy", defaultValue = "nome")String orderBy,
			@RequestParam (value = "direction", defaultValue = "ASC")String direction)
				throws Objectnotfoundexception, ObjectNotFoundException {
		Page<Cliente> list = Service.finPage(page,lineInteger,orderBy,direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	

}
