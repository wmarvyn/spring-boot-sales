package com.wmarvyn.sales.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wmarvyn.sales.domain.ItemPedido;

@Repository
public interface itemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
