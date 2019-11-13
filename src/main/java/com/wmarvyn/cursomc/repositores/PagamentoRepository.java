package com.wmarvyn.cursomc.repositores;

import com.wmarvyn.cursomc.domain.Pagamento;
import com.wmarvyn.cursomc.domain.Categoria;
;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
