package com.wmarvyn.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmarvyn.cursomc.domain.Categoria;
import com.wmarvyn.cursomc.domain.Cliente;
import com.wmarvyn.cursomc.repositores.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteServie {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
		}	
	}
