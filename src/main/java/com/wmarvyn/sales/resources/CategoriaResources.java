package com.wmarvyn.sales.resources;

import com.wmarvyn.sales.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wmarvyn.sales.domain.Categoria;
import com.wmarvyn.sales.services.CategoriaServie;
import com.wmarvyn.sales.services.exception.Objectnotfoundexception;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
		Categoria obj = Service.fromDTO(objDto);
		obj = Service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO,@PathVariable Integer id) throws ObjectNotFoundException {
		Categoria obj = Service.fromDTO(objDTO);
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
	public ResponseEntity<List<CategoriaDTO>> find() throws Objectnotfoundexception, ObjectNotFoundException {
		List<Categoria> list = Service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> find(
			@RequestParam (value = "page", defaultValue = "0")Integer page,
			@RequestParam (value = "linesPerPage", defaultValue = "24")Integer lineInteger,
			@RequestParam (value = "oderBy", defaultValue = "nome")String orderBy,
			@RequestParam (value = "direction", defaultValue = "ASC")String direction)
				throws Objectnotfoundexception, ObjectNotFoundException {
		Page<Categoria> list = Service.finPage(page,lineInteger,orderBy,direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}


}
