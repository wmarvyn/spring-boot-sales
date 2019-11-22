package com.wmarvyn.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wmarvyn.cursomc.domain.ItemPedido;

@Repository
public interface itemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
