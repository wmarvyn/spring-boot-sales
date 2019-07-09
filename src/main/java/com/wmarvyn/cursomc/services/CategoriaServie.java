package com.wmarvyn.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmarvyn.cursomc.domain.Categoria;
import com.wmarvyn.cursomc.repositores.CategoriaRspository;

@Service
public class CategoriaServie {

	@Autowired
	private CategoriaRspository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		}
	
}
