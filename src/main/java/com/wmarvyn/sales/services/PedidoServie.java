package com.wmarvyn.sales.services;

import com.wmarvyn.sales.domain.Pedido;
import com.wmarvyn.sales.repositores.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoServie {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
		}	
	}
