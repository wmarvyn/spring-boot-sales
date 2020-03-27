package com.wmarvyn.sales.services;

import java.util.List;
import java.util.Optional;

import com.wmarvyn.sales.services.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wmarvyn.sales.domain.Categoria;
import com.wmarvyn.sales.repositores.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.data.domain.Page;

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
			try {
				repo.deleteById(id);
			}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos associados");
			}
		}

		public List<Categoria> findAll(){
		return repo.findAll();
		}

		public Page<Categoria> finPage(Integer page, Integer lineInteger, String orderBy, String direction){

			PageRequest pageRequest = new PageRequest(page,lineInteger, Sort.Direction.fromString(direction),orderBy);

			return  repo.findAll(pageRequest);
		}
}
