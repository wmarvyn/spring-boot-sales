package com.wmarvyn.sales.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmarvyn.sales.domain.Categoria;
import com.wmarvyn.sales.repositores.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaServie {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
		}

		public Categoria insert (Categoria obj){
		obj.setId((null));
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		find(obj.getId());
	return repo.save(obj);
	}

		public void delete(Integer id) throws ObjectNotFoundException {
			find(id);
			//System.out.println("Passando pelo delete");
			repo.deleteById(id);
		}
}
