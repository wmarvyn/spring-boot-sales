package com.wmarvyn.sales.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmarvyn.sales.domain.Categoria;
import com.wmarvyn.sales.domain.Cliente;
import com.wmarvyn.sales.repositores.ClienteRepository;

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
