package com.wmarvyn.cursomc.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wmarvyn.cursomc.domain.Cidades;

@Repository
public interface CidadeRepository extends JpaRepository<Cidades, Integer>{

}
